package app.com.dataonsubmitteddeclarations.favorite;

import com.arellomobile.mvp.InjectViewState;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import app.com.dataonsubmitteddeclarations.base.BaseSearchPresenter;
import app.com.dataonsubmitteddeclarations.di.InjectHelper;
import app.com.dataonsubmitteddeclarations.search.SearchPresenterContract;
import app.com.domain.interactors.FetchPersonsContract;
import app.com.domain.interactors.FetchPersonsInteractor;
import app.com.domain.models.PersonModel;
import io.reactivex.Flowable;
import timber.log.Timber;

@InjectViewState
public class FavoriteSearchPresenter extends BaseSearchPresenter implements SearchPresenterContract {

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
}
