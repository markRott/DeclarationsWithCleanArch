package app.com.dataonsubmitteddeclarations.search;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;
import javax.inject.Named;

import app.com.dataonsubmitteddeclarations.base.BaseSearchPresenter;
import app.com.dataonsubmitteddeclarations.di.InjectHelper;
import app.com.domain.interactors.FavoriteInteractor;
import app.com.domain.interactors.FetchPersonsContract;
import app.com.domain.interactors.FetchPersonsInteractor;
import app.com.domain.models.PersonModel;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

@InjectViewState
public class SearchPresenter extends BaseSearchPresenter implements SearchPresenterContract {

    @Inject
    FavoriteInteractor favoriteInteractor;

    @Inject
    @Named("search")
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
}

































