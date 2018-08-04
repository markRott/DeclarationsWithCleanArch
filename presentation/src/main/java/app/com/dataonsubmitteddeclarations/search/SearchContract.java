package app.com.dataonsubmitteddeclarations.search;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import app.com.domain.models.PersonsModel;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface SearchContract extends MvpView {

    void showProgress();

    void hideProgress();

    void showNoDataView();

    void hideNoDataView();

    void showList();

    void hideList();

    void renderPersonsData(PersonsModel personsModel);
}
