package app.com.dataonsubmitteddeclarations.favorite;

import com.arellomobile.mvp.presenter.InjectPresenter;

import app.com.dataonsubmitteddeclarations.base.BaseSearchFragment;
import app.com.dataonsubmitteddeclarations.search.SearchPresenterContract;
import app.com.domain.models.PersonModel;

public class FavoriteFragment extends BaseSearchFragment {

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    @InjectPresenter
    FavoriteSearchPresenter favoriteSearchPresenter;

    @Override
    protected void removeItemFromFavoriteList(PersonModel personModel) {
        int index = -1;
        PersonModel currentModel;
        if (personModel.isRemoveFavoriteItem()) {
            for (int i = personAdapter.getData().size() - 1; i >= 0; i--) {
                currentModel = personAdapter.getData().get(i);
                if (currentModel == null) continue;
                if (!(currentModel.getId().equals(personModel.getId()))) continue;
                index = i;
                personAdapter.getData().remove(index);
            }
            if (index >= 0) {
                personAdapter.notifyItemRemoved(index);
            }
        }
    }

    @Override
    public SearchPresenterContract getPresenter() {
        return favoriteSearchPresenter;
    }
}
