package app.com.dataonsubmitteddeclarations.search;

import com.arellomobile.mvp.InjectViewState;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import app.com.dataonsubmitteddeclarations.base.BaseSearchPresenter;
import app.com.dataonsubmitteddeclarations.di.InjectHelper;
import app.com.dataonsubmitteddeclarations.utils.RxBus;
import app.com.domain.interactors.FavoriteInteractor;
import app.com.domain.interactors.FetchPersonsContract;
import app.com.domain.models.PersonModel;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

@InjectViewState
public class SearchPresenter extends BaseSearchPresenter implements SearchPresenterContract {

    @Inject
    FavoriteInteractor favoriteInteractor;
    @Inject
    @Named("search")
    FetchPersonsContract fetchPersonsInteractor;
    @Inject
    @Named("favorite")
    FetchPersonsContract fetchPersonsFromDatabase;

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

    @Override
    public void lifeSearchByInputText(final Flowable<String> textViewFlowable) {
        Disposable disposable = getDataFromNetwork(textViewFlowable)
                .flatMap(personModelList -> {
                    successFetchPersonsResponse(personModelList);
                    return getDataFromDatabase();
                }).subscribe(
                        personModelList -> RxBus.getInstance().sendData(personModelList)
                        , error -> Timber.e(error, "Error get data from database"));
        disposableManager.addDisposable(disposable);
    }

    private Flowable<List<PersonModel>> getDataFromNetwork(final Flowable<String> textViewFlowable) {
        return textViewFlowable
                .filter(this::isEqualQuery)
                .observeOn(threadContract.ui())
                .switchMap(tmpQuery -> {
                    currQuery = tmpQuery;
                    startRequestUpdateUi();
                    return getPersonList(tmpQuery);
                });
    }

    private Flowable<List<PersonModel>> getDataFromDatabase() {
        return fetchPersonsFromDatabase.fetchPersonsByName("");
    }
}

































