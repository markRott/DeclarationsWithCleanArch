package app.com.dataonsubmitteddeclarations.favorite;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import app.com.dataonsubmitteddeclarations.R;
import app.com.dataonsubmitteddeclarations.base.BaseSearchFragment;
import app.com.dataonsubmitteddeclarations.search.SearchPresenterContract;
import app.com.domain.models.PersonModel;
import butterknife.ButterKnife;

public class FavoriteFragment extends BaseSearchFragment {

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    @InjectPresenter
    FavoriteSearchPresenter favoriteSearchPresenter;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_favorite_search, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

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
    protected SearchPresenterContract getPresenter() {
        return favoriteSearchPresenter;
    }

    @Override
    public void checkFavoriteState(List<PersonModel> personModelList) {
        // nothing to do
    }
}
