package app.com.dataonsubmitteddeclarations.search;

import com.arellomobile.mvp.presenter.InjectPresenter;

import app.com.dataonsubmitteddeclarations.base.BaseSearchFragment;
import app.com.domain.models.PersonModel;

public class SearchFragment extends BaseSearchFragment {

    @InjectPresenter
    SearchPresenter searchPresenter;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    protected void removeItemFromFavoriteList(PersonModel personModel) {
        // nothing to do
    }

    @Override
    public SearchPresenterContract getPresenter() {
        return searchPresenter;
    }
}
