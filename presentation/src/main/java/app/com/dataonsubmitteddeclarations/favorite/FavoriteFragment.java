package app.com.dataonsubmitteddeclarations.favorite;

import com.arellomobile.mvp.presenter.InjectPresenter;

import app.com.dataonsubmitteddeclarations.base.BaseSearchFragment;
import app.com.dataonsubmitteddeclarations.search.SearchPresenterContract;

public class FavoriteFragment extends BaseSearchFragment {

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    @InjectPresenter
    FavoriteSearchPresenter favoriteSearchPresenter;

    @Override
    public SearchPresenterContract getPresenter() {
        return favoriteSearchPresenter;
    }
}
