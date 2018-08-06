package app.com.dataonsubmitteddeclarations.favorite;

import com.arellomobile.mvp.InjectViewState;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import app.com.dataonsubmitteddeclarations.base.BasePresenter;
import app.com.dataonsubmitteddeclarations.di.InjectHelper;
import app.com.dataonsubmitteddeclarations.search.SearchContract;
import app.com.dataonsubmitteddeclarations.search.SearchPresenterContract;
import app.com.domain.interactors.FetchPersonsInteractor;
import app.com.domain.interfaces.ThreadContract;
import app.com.domain.models.PersonModel;
import io.reactivex.Flowable;
import timber.log.Timber;

@InjectViewState
public class FavoriteSearchPresenter extends BasePresenter<SearchContract> implements SearchPresenterContract {

    @Inject
    ThreadContract threadContract;
    @Inject
    @Named("favorite")
    FetchPersonsInteractor fetchPersonsInteractor;

    private String currQuery = "";

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        InjectHelper.getMainAppComponent().inject(this);
    }

    @Override
    public void lifeSearchByInputText(Flowable<String> textViewFlowable) {
        disposableManager.addDisposable(textViewFlowable
                .filter(this::isEqualQuery)
                .observeOn(threadContract.ui())
                .switchMap(tmpQuery -> {
                    currQuery = tmpQuery;
                    startRequestUpdateUi();
                    return getPersonList(tmpQuery);
                })
                .subscribe(
                        this::successResponse,
                        error -> {
                            Timber.e(error, "lifeSearchByInputText Error fetch persons data by name ");
                            showNoDataView();
                        })
        );
    }

    @Override
    public void dropCurrentQuery() {
        currQuery = "";
    }

    private Flowable<List<PersonModel>> getPersonList(String tmpQuery) {
        return fetchPersonsInteractor
                .fetchPersonsByName(tmpQuery)
                .onErrorResumeNext(throwable -> {
                    showNoDataView();
                    Timber.e(throwable, "Fetch persons by name");
                    return Flowable.empty();
                });
    }

    private void startRequestUpdateUi() {
        getViewState().hideNoDataView();
        getViewState().hideList();
        getViewState().showProgress();
    }

    private void showNoDataView() {
        getViewState().hideProgress();
        getViewState().hideList();
        getViewState().showNoDataView();
    }

    private void successResponse(List<PersonModel> personModelList) {
        if (personModelList.isEmpty()) {
            showNoDataView();
            return;
        }
        showList();
        getViewState().renderPersonItems(personModelList);
    }

    private boolean isEqualQuery(final String tmpQuery) {
        return !currQuery.equals(tmpQuery);
    }

    private void showList() {
        getViewState().hideNoDataView();
        getViewState().hideProgress();
        getViewState().showList();
    }

}
