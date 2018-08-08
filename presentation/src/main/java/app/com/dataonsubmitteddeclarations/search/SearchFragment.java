package app.com.dataonsubmitteddeclarations.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;

import app.com.dataonsubmitteddeclarations.R;
import app.com.dataonsubmitteddeclarations.base.BaseSearchFragment;
import app.com.domain.models.PersonModel;
import butterknife.ButterKnife;

public class SearchFragment extends BaseSearchFragment {

    @InjectPresenter
    SearchPresenter searchPresenter;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_search, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected SearchPresenterContract getPresenter() {
        return searchPresenter;
    }

    @Override
    protected void removeItemFromFavoriteList(PersonModel personModel) {
        // nothing to do
    }
}
