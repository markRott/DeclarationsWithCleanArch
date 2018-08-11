package app.com.dataonsubmitteddeclarations.base;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;

import app.com.dataonsubmitteddeclarations.search.SearchContract;
import app.com.dataonsubmitteddeclarations.search.SearchPresenterContract;
import app.com.dataonsubmitteddeclarations.utils.RxBus;
import app.com.domain.interactors.FavoriteInteractor;
import app.com.domain.interactors.FetchPersonsContract;
import app.com.domain.models.PersonModel;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public abstract class BaseSearchPresenter extends BasePresenter<SearchContract>
        implements SearchPresenterContract {

    protected String currQuery = "";

    public void dropCurrentQuery() {
        currQuery = "";
    }

    @Override
    public void lifeSearchByInputText(final Flowable<String> textViewFlowable) {
        Disposable searchDisposable = textViewFlowable
                .filter(this::isEqualQuery)
                .observeOn(threadContract.ui())
                .switchMap(tmpQuery -> {
                    currQuery = tmpQuery;
                    startRequestUpdateUi();
                    return getPersonList(tmpQuery);
                })
                .subscribe(
                        this::successFetchPersonsResponse,
                        error -> {
                            Timber.e(error, "lifeSearchByInputText Error fetch persons data by name ");
                            showNoDataView();
                        });
        disposableManager.addDisposable(searchDisposable);
    }

    protected boolean isEqualQuery(final String tmpQuery) {
        return !currQuery.equals(tmpQuery);
    }

    protected void startRequestUpdateUi() {
        getViewState().hideNoDataView();
        getViewState().hideList();
        getViewState().showProgress();
    }

    protected Flowable<List<PersonModel>> getPersonList(String tmpQuery) {
        return getFetchPersonsContract()
                .fetchPersonsByName(tmpQuery)
                .onErrorResumeNext(throwable -> {
                    showNoDataView();
                    handleException(throwable);
                    Timber.e(throwable, "Error fetch persons by name");
                    return Flowable.empty();
                });
    }

    protected void successFetchPersonsResponse(final List<PersonModel> personModelList) {
        if (personModelList == null || personModelList.isEmpty()) {
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

    protected void showNoDataView() {
        getViewState().hideProgress();
        getViewState().hideList();
        getViewState().showNoDataView();
    }

    @Override
    public void favoriteRequest(final PersonModel personModel) {
        getViewState().showFavoriteProgress(personModel);
        final Disposable disposable = getFavoriteInteractor()
                .favoriteRequest(personModel)
                .subscribe(
                        (PersonModel responseModel) -> {
                            getViewState().hideFavoriteProgressAndUpdateUi(responseModel);
                            RxBus.getInstance().sendData(Collections.singletonList(responseModel));
                        },
                        error -> {
                            getViewState().hideFavoriteProgressAndUpdateUi(personModel);
                            Timber.e(error, "Favorite request error");
                        }
                );
        disposableManager.addDisposable(disposable);
    }

    private void handleException(Throwable throwable) {
        if (throwable instanceof UnknownHostException || throwable instanceof SocketTimeoutException) {
            getViewState().showNoInternetConnection();
        }
    }

    protected abstract FetchPersonsContract getFetchPersonsContract();

    protected abstract FavoriteInteractor getFavoriteInteractor();
}
