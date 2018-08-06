package app.com.dataonsubmitteddeclarations.search;


import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;

import java.util.List;

import javax.inject.Inject;

import app.com.dataonsubmitteddeclarations.base.BasePresenter;
import app.com.dataonsubmitteddeclarations.di.InjectHelper;
import app.com.domain.interactors.FavoriteInteractor;
import app.com.domain.interactors.PersonsInteractor;
import app.com.domain.interfaces.ThreadContract;
import app.com.domain.models.PersonModel;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

@InjectViewState
public class SearchPresenter extends BasePresenter<SearchContract> {

    @Inject
    PersonsInteractor personsInteractor;
    @Inject
    FavoriteInteractor favoriteInteractor;
    @Inject
    ThreadContract threadContract;

    private String currQuery = "";

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        InjectHelper.getMainAppComponent().inject(this);
    }

    public void lifeSearchByInputText(final Flowable<String> textViewFlowable) {
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

    private Flowable<List<PersonModel>> getPersonList(String tmpQuery) {
        return personsInteractor
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

    private void successResponse(List<PersonModel> personModelList) {
        if (personModelList.isEmpty()) {
            showNoDataView();
            return;
        }
        showList();
        getViewState().renderPersonItems(personModelList);
    }

    private void showList() {
        getViewState().hideNoDataView();
        getViewState().hideProgress();
        getViewState().showList();
    }

    private void showNoDataView() {
        getViewState().hideProgress();
        getViewState().hideList();
        getViewState().showNoDataView();
    }

    public void dropCurrentQuery() {
        currQuery = "";
    }

    @SuppressLint("TimberArgCount")
    public void favoriteRequest(final PersonModel personModel) {
        getViewState().showFavoriteProgress(personModel);
        final Disposable disposable = favoriteInteractor
                .favoriteRequest(personModel)
                .subscribe(
                        this::hideFavoriteProgressBar,
                        error -> {
                            hideFavoriteProgressBar(personModel);
                            Timber.e(error, "Favorite request error");
                        }
                );
        disposableManager.addDisposable(disposable);
    }

    private void hideFavoriteProgressBar(final PersonModel personModel) {
        getViewState().hideFavoriteProgress(personModel);
    }

    private boolean isEqualQuery(final String tmpQuery) {
        return !currQuery.equals(tmpQuery);
    }
}

































