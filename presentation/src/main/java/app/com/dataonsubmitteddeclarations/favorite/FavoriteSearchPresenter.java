package app.com.dataonsubmitteddeclarations.favorite;

import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;
import javax.inject.Named;

import app.com.dataonsubmitteddeclarations.base.BaseSearchPresenter;
import app.com.dataonsubmitteddeclarations.di.InjectHelper;
import app.com.dataonsubmitteddeclarations.search.SearchPresenterContract;
import app.com.domain.interactors.FavoriteInteractor;
import app.com.domain.interactors.FetchPersonsContract;

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
    }

    @Override
    protected FetchPersonsContract getFetchPersonsContract() {
        return fetchPersonsInteractor;
    }

    @Override
    protected FavoriteInteractor getFavoriteInteractor() {
        return favoriteInteractor;
    }
}
