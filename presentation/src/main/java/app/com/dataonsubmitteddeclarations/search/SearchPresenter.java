package app.com.dataonsubmitteddeclarations.search;


import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import app.com.dataonsubmitteddeclarations.base.BasePresenter;
import app.com.dataonsubmitteddeclarations.di.InjectHelper;
import app.com.domain.base.BaseSubscriber;
import app.com.domain.interactors.PersonsInteractor;
import app.com.domain.models.PersonsModel;
import timber.log.Timber;

@InjectViewState
public class SearchPresenter extends BasePresenter<SearchContract> {

    private static final String TAG = SearchPresenter.class.getSimpleName();

    @Inject
    PersonsInteractor personsInteractor;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        InjectHelper.getMainAppComponent().inject(this);
    }

    public void fetchPersonsDataByName(final String personName) {
        if (personsInteractor == null) return;
        startRequest();
        disposableManager.addDisposable(personsInteractor
                .fetchPersonsByName(personName)
                .subscribeWith(new BaseSubscriber<PersonsModel>() {
                    @Override
                    public void onNext(PersonsModel personsModel) {
                        successResponse(personsModel);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Timber.e(t, "fetchPersonsDataByName ");
                        showNoDataView();
                    }
                }));
    }

    private void startRequest() {
        getViewState().hideNoDataView();
        getViewState().hideList();
        getViewState().showProgress();
    }

    private void successResponse(PersonsModel personsModel) {
        if (personsModel == null || personsModel.getItems().isEmpty()) {
            showNoDataView();
            return;
        }
        if (!personsModel.getItems().isEmpty()) {
            showList();
            getViewState().renderPersonsData(personsModel);
        }
    }

    public void showNoDataView() {
        getViewState().hideProgress();
        getViewState().hideList();
        getViewState().showNoDataView();
    }

    public void showList() {
        getViewState().hideNoDataView();
        getViewState().hideProgress();
        getViewState().showList();
    }
}
