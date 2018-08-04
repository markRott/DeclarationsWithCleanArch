package app.com.dataonsubmitteddeclarations.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import app.com.dataonsubmitteddeclarations.MainActivity;
import app.com.dataonsubmitteddeclarations.R;
import app.com.dataonsubmitteddeclarations.base.BaseFragment;
import app.com.dataonsubmitteddeclarations.pdf.PdfViewerFragment;
import app.com.domain.models.PersonModel;
import app.com.domain.models.PersonsModel;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class SearchFragment extends BaseFragment implements SearchContract, TouchPdfIconListener<PersonModel> {

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
    private Disposable searchDisposable;
    private PersonAdapter personAdapter;

    public static SearchFragment newInstance() {
        final SearchFragment fragment = new SearchFragment();
        final Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRetainInstance(true);
        System.out.println("savedInstanceState = " + savedInstanceState);
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
    }

    private void initRecyclerView() {
        if (recyclerView != null) {
            personAdapter = new PersonAdapter();
            personAdapter.setTouchPdfIconListener(this);
            recyclerView.setAdapter(personAdapter);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        enableLiveSearch();
    }

    private void enableLiveSearch() {
        searchDisposable =
                RxTextView
                        .textChanges(edtSearch)
                        .skip(1)
                        .filter(name -> !name.toString().isEmpty())
                        .debounce(500, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(name -> {
                            Timber.d(name.toString());
                            searchPresenter.fetchPersonsDataByName(name.toString());
                        });

        disposableManager.addDisposable(searchDisposable);
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
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
        personAdapter.clear();
        recyclerView.setVisibility(View.GONE);
        recyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void renderPersonsData(PersonsModel personsModel) {
        personAdapter.setData(personsModel.getItems());
    }

    @Override
    public void touchPdfIcon(PersonModel personModel, int position) {
        if (personModel != null && getActivity() instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.openFragment(PdfViewerFragment.newInstance(personModel.getLinkPdf()));
        }
    }
}
