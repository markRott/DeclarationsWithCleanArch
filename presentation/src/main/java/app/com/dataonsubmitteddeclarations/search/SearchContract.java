package app.com.dataonsubmitteddeclarations.search;

import com.arellomobile.mvp.MvpView;

import app.com.domain.models.PersonsModel;

public interface SearchContract extends MvpView{

    void showProgress();

    void hideProgress();

    void showNoDataView();

    void hideNoDataView();

    void renderPersonsData(PersonsModel personsModel);
}
