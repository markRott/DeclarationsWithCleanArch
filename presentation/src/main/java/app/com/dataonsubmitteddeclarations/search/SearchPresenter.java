package app.com.dataonsubmitteddeclarations.search;


import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import app.com.dataonsubmitteddeclarations.base.BasePresenter;
import app.com.dataonsubmitteddeclarations.di.InjectHelper;
import app.com.domain.base.BaseSubscriber;
import app.com.domain.interactors.FavoriteInteractor;
import app.com.domain.interactors.PersonsInteractor;
import app.com.domain.models.PersonModel;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

@InjectViewState
public class SearchPresenter extends BasePresenter<SearchContract> {

    private static final String TAG = SearchPresenter.class.getSimpleName();

    @Inject
    PersonsInteractor personsInteractor;

    @Inject
    FavoriteInteractor favoriteInteractor;

    private String currQuery = "";

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        InjectHelper.getMainAppComponent().inject(this);
    }

//    public void fetchPersonsDataByName(final String personName) {
//        if (personsInteractor == null) return;
//        startRequest();
//        disposableManager.addDisposable(personsInteractor
//                .fetchPersonsByName(personName)
//                .subscribeWith(new BaseSubscriber<PersonsModel>() {
//                    @Override
//                    public void onNext(PersonsModel personsModel) {
//                        successResponse(personsModel);
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//                        Timber.e(t, "fetchPersonsDataByName ");
//                        showNoDataView();
//                    }
//                }));
//    }

    private void startRequest() {
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
        getViewState().renderPersonsData(personModelList);
    }

    public void showNoDataView() {
        getViewState().hideProgress();
        getViewState().hideList();
        getViewState().showNoDataView();
    }

    private void showList() {
        getViewState().hideNoDataView();
        getViewState().hideProgress();
        getViewState().showList();
    }

    public void liveSearch(final Flowable<String> queryFlowable) {
        disposableManager.addDisposable(queryFlowable
                .filter(tmpQuery -> !currQuery.equals(tmpQuery))
                .switchMap(tmpQuery -> {
                    currQuery = tmpQuery;
                    return personsInteractor.fetchPersonsByName(tmpQuery);
                })
                .subscribeWith(new BaseSubscriber<List<PersonModel>>() {
                    @Override
                    public void onNext(List<PersonModel> modelList) {
                        Timber.d(Arrays.toString(modelList.toArray()));
                        successResponse(modelList);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Timber.e(t, "fetchPersonsDataByName ");
                        showNoDataView();
                    }
                }));
    }

    @SuppressLint("TimberArgCount")
    public void favoriteRequest(final PersonModel personModel) {
        getViewState().showFavoriteProgress(personModel);
        final Disposable disposable =
                favoriteInteractor
                        .favoriteRequest(personModel)
                        .delay(30, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                response -> hideFavoriteProgressBar(response),
                                error -> hideFavoriteProgressBar(personModel)
                        );
        disposableManager.addDisposable(disposable);
    }

    private void hideFavoriteProgressBar(final PersonModel personModel) {
        getViewState().hideFavoriteProgress(personModel);
    }
}

































