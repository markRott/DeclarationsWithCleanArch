package app.com.dataonsubmitteddeclarations.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;

import app.com.dataonsubmitteddeclarations.base.BaseSearchFragment;
import app.com.dataonsubmitteddeclarations.managers.Router;
import app.com.domain.models.PersonModel;

public class SearchFragment extends BaseSearchFragment {

    @InjectPresenter
    SearchPresenter searchPresenter;

    private PersonModel favoritePersonModel;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Router.FAVORITE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            final Bundle args = data.getExtras();
            if (args != null) {
                favoritePersonModel = (PersonModel) args.get(FavoriteDialogFragment.SEND_FAVORITE_MODEL);
                searchPresenter.favoriteRequest(favoritePersonModel);
            }
        }
    }

    @Override
    public SearchPresenterContract getPresenter() {
        return searchPresenter;
    }
}
