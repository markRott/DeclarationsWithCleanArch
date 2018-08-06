package app.com.dataonsubmitteddeclarations.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import app.com.dataonsubmitteddeclarations.R;
import app.com.dataonsubmitteddeclarations.base.BaseFragment;
import app.com.dataonsubmitteddeclarations.managers.Router;
import app.com.dataonsubmitteddeclarations.managers.RouterData;
import app.com.dataonsubmitteddeclarations.search.adapter.PersonAdapter;
import app.com.dataonsubmitteddeclarations.search.adapter.TouchFavoriteListener;
import app.com.dataonsubmitteddeclarations.search.adapter.TouchPdfIconListener;
import app.com.dataonsubmitteddeclarations.utils.ViewUtils;
import app.com.domain.models.PersonModel;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import timber.log.Timber;

public class SearchFragment extends BaseFragment implements SearchContract,
        TouchPdfIconListener<PersonModel>, TouchFavoriteListener<PersonModel> {

    private static final boolean SHOW_FAVORITE_PROGRESS_BAR = true;

    @BindView(R.id.edt_search)
    EditText edtSearch;
    @BindView(R.id.vg_empty_list)
    ViewGroup vgNoData;
    @BindView(R.id.prg_search)
    ProgressBar prgSearch;
    @BindView(R.id.rcv_search)
    RecyclerView recyclerView;

    @InjectPresenter
    SearchPresenter searchPresenter;

    private Unbinder unbinder;
    private PersonAdapter personAdapter;
    private PersonModel favoritePersonModel;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_search, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
    }

    private void initRecyclerView() {
        if (recyclerView != null) {
            personAdapter = new PersonAdapter();
            personAdapter.setTouchPdfIconListener(this);
            personAdapter.setTouchFavoriteListener(this);
            recyclerView.setAdapter(personAdapter);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        enableLifeSearch();
        ViewUtils.hideKeyboardFrom(getContext(), getView());
    }

    private void enableLifeSearch() {
        Flowable<String> textViewFlowable =
                RxTextView
                        .textChanges(edtSearch)
                        .debounce(500, TimeUnit.MILLISECONDS)
                        .filter(charSequence -> charSequence.length() > 3)
                        .map(CharSequence::toString)
                        .distinctUntilChanged()
                        .publish()
                        .autoConnect()
                        .toFlowable(BackpressureStrategy.LATEST);

        searchPresenter.lifeSearchByInputText(textViewFlowable);
    }

    @OnClick(R.id.iv_clear_text)
    public void clearEditText() {
        edtSearch.setText("");
        searchPresenter.showNoDataView();
    }

    @Override
    public void showProgress() {
        prgSearch.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        prgSearch.setVisibility(View.GONE);
    }

    @Override
    public void showNoDataView() {
        vgNoData.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoDataView() {
        vgNoData.setVisibility(View.GONE);
    }

    @Override
    public void showList() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideList() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void renderPersonItems(List<PersonModel> personModelList) {
        personAdapter.setData(personModelList);
    }

    @Override
    public void showFavoriteProgress(final PersonModel personModel) {
        findItemAndSetupProgressBarState(personModel, SHOW_FAVORITE_PROGRESS_BAR);
    }

    @Override
    public void hideFavoriteProgress(final PersonModel personModel) {
        findItemAndSetupProgressBarState(personModel, !SHOW_FAVORITE_PROGRESS_BAR);
    }

    private void findItemAndSetupProgressBarState(final PersonModel personModel, boolean visibilityState) {
        final int itemPosition = personModel.getPositionInAdapter();
        if (itemPosition >= 0) {
            personModel.setProgressBarVisibilityState(visibilityState);
            personAdapter.notifyItemChanged(itemPosition);
        } else {
            Timber.e("Wrong item position");
        }
    }

    @Override
    public void touchPdfIcon(PersonModel personModel, int position) {
        if (getManager() == null) return;
        final RouterData data = RouterData.RouterDataBuilder
                .builder()
                .setFragmentManager(getManager())
                .setPersonModel(personModel)
                .build();
        router.openPdfFragment(data);
    }

    private FragmentManager getManager() {
        return getActivity().getSupportFragmentManager();
    }

    @Override
    public void touchFavoriteIcon(PersonModel personModel, int position) {
        if (getManager() == null) return;
        final RouterData data = RouterData.RouterDataBuilder
                .builder()
                .setFragmentManager(getManager())
                .setPersonModel(personModel)
                .setFragment(this)
                .build();
        router.openFavoriteDialog(data);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Router.FAVORITE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            final Bundle args = data.getExtras();
            if (args != null) {
                favoritePersonModel = (PersonModel) args.get(FavoriteDialogFragment.SEND_FAVORITE_MODEL);
                searchPresenter.favoriteRequest(favoritePersonModel);
            }
        }
    }
}
