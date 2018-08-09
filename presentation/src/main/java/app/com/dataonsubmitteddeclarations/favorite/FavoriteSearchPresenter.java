package app.com.dataonsubmitteddeclarations.favorite;

import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;
import javax.inject.Named;

import app.com.dataonsubmitteddeclarations.base.BaseSearchPresenter;
import app.com.dataonsubmitteddeclarations.di.InjectHelper;
import app.com.dataonsubmitteddeclarations.search.SearchPresenterContract;
import app.com.domain.interactors.FavoriteInteractor;
import app.com.domain.interactors.FetchPersonsContract;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

@InjectViewState
public class FavoriteSearchPresenter extends BaseSearchPresenter implements SearchPresenterContract {

    @Inject
    FavoriteInteractor favoriteInteractor;
    @Inject
    @Named("favorite")
    FetchPersonsContract fetchPersonsInteractor;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        InjectHelper.getMainAppComponent().inject(this);
        getFavoritePersonsFromDatabase();
    }

    @Override
    protected FetchPersonsContract getFetchPersonsContract() {
        return fetchPersonsInteractor;
    }

    @Override
    protected FavoriteInteractor getFavoriteInteractor() {
        return favoriteInteractor;
    }

    private void getFavoritePersonsFromDatabase() {
        Disposable disposable = getPersonList("")
                .subscribe(
                        this::successFetchPersonsResponse,
                        error -> {
                            Timber.e(error, "Get favorite persons from database");
                            showNoDataView();
                        });
        disposableManager.addDisposable(disposable);
    }
}
