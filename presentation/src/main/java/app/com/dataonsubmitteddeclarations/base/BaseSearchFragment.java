package app.com.dataonsubmitteddeclarations.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import app.com.dataonsubmitteddeclarations.R;
import app.com.dataonsubmitteddeclarations.favorite.FavoriteDialogFragment;
import app.com.dataonsubmitteddeclarations.managers.Router;
import app.com.dataonsubmitteddeclarations.managers.RouterData;
import app.com.dataonsubmitteddeclarations.search.SearchContract;
import app.com.dataonsubmitteddeclarations.search.SearchPresenterContract;
import app.com.dataonsubmitteddeclarations.search.adapter.PersonAdapter;
import app.com.dataonsubmitteddeclarations.search.adapter.listeners.TouchFavoriteListener;
import app.com.dataonsubmitteddeclarations.search.adapter.listeners.TouchPdfIconListener;
import app.com.dataonsubmitteddeclarations.utils.ViewUtils;
import app.com.domain.models.PersonModel;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import timber.log.Timber;

public abstract class BaseSearchFragment extends BaseFragment implements SearchContract,
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

    protected PersonAdapter personAdapter;
    protected Unbinder unbinder;
    private PersonModel favoritePersonModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
    }

    @Override
    public void onResume() {
        super.onResume();
        enableLifeSearch();
        ViewUtils.hideKeyboardFrom(getContext(), getView());
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @OnClick(R.id.iv_clear_text)
    public void clearEditText() {
        edtSearch.setText("");
        getPresenter().dropCurrentQuery();
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
    public void renderPersonItems(final List<PersonModel> personModelList) {
        personAdapter.setData(personModelList);
    }

    @Override
    public void showFavoriteProgress(final PersonModel personModel) {
        findItemAndSetupProgressBarState(personModel, SHOW_FAVORITE_PROGRESS_BAR);
    }

    @Override
    public void hideFavoriteProgressAndUpdateUi(final PersonModel personModel) {
        findItemAndSetupProgressBarState(personModel, !SHOW_FAVORITE_PROGRESS_BAR);
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
    public void touchPdfIcon(PersonModel personModel, int position) {
        if (getManager() == null) return;
        final RouterData data = RouterData.RouterDataBuilder
                .builder()
                .setFragmentManager(getManager())
                .setPersonModel(personModel)
                .build();
        router.openPdfFragment(data);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Router.FAVORITE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            final Bundle args = data.getExtras();
            if (args != null) {
                favoritePersonModel = (PersonModel) args.get(FavoriteDialogFragment.SEND_FAVORITE_MODEL);
                if (favoritePersonModel == null) return;
                favoritePersonModel.setDraftComment(true);
                getPresenter().favoriteRequest(favoritePersonModel);
            }
        }
    }

    private void initRecyclerView() {
        if (recyclerView != null) {
            personAdapter = new PersonAdapter(getContext());
            personAdapter.setTouchPdfIconListener(this);
            personAdapter.setTouchFavoriteListener(this);
            recyclerView.setAdapter(personAdapter);
        }
    }

    protected void enableLifeSearch() {
        Flowable<String> textViewFlowable =
                RxTextView
                        .textChanges(edtSearch)
                        .debounce(500, TimeUnit.MILLISECONDS)
                        .filter(charSequence -> charSequence.length() > 3)
                        .map(CharSequence::toString)
                        .distinctUntilChanged()
                        .toFlowable(BackpressureStrategy.LATEST);

        getPresenter().lifeSearchByInputText(textViewFlowable);
    }

    private void findItemAndSetupProgressBarState(final PersonModel personModel, boolean visibilityState) {
        if (personModel == null) return;
        final int itemPosition = personModel.getPositionInAdapter();
        if (itemPosition >= 0) {
            personModel.setProgressBarVisibilityState(visibilityState);
            personAdapter.notifyItemChanged(itemPosition);
            removeItemFromFavoriteList(personModel);
        } else {
            Timber.e("Wrong item position");
        }
    }

    private FragmentManager getManager() {
        return getActivity().getSupportFragmentManager();
    }

    protected abstract SearchPresenterContract getPresenter();

    protected void removeItemFromFavoriteList(final PersonModel personModel) {
        // realization in child classes
    }
}
