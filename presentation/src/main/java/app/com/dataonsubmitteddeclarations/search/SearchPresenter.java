package app.com.dataonsubmitteddeclarations.search;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import app.com.domain.base.BaseSubscriber;
import app.com.domain.interactors.PersonsInteractor;
import app.com.domain.models.PersonsModel;

@InjectViewState
public class SearchPresenter extends MvpPresenter<SearchContract> {

    private PersonsInteractor personsInteractor;

    public void fetchPersonsDataByName(final String personName) {
        if (personsInteractor == null) return;
        getViewState().hideNoDataView();
        getViewState().showProgress();
        personsInteractor
                .fetchPersonsByName(personName)
                .subscribeWith(new BaseSubscriber<PersonsModel>() {
                    @Override
                    public void onNext(PersonsModel personsModel) {
                        if (personsModel != null) {
                            getViewState().renderPersonsData(personsModel);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("fetchPersonsDataByName = " + t);
                    }
                });
    }
}
