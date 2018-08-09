package app.com.dataonsubmitteddeclarations.search;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import app.com.domain.models.PersonModel;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface SearchContract extends MvpView {

    void showProgress();

    void hideProgress();

    void showNoDataView();

    void hideNoDataView();

    void showList();

    void hideList();

    void renderPersonItems(final List<PersonModel> personModelList);

    void showFavoriteProgress(final PersonModel personModel);

    void hideFavoriteProgress(final PersonModel personModel);

     void checkFavoriteState(List<PersonModel> personModelList);
}
