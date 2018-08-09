package app.com.dataonsubmitteddeclarations.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.List;

import app.com.dataonsubmitteddeclarations.R;
import app.com.dataonsubmitteddeclarations.base.BaseSearchFragment;
import app.com.dataonsubmitteddeclarations.utils.RxBus;
import app.com.domain.models.PersonModel;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class SearchFragment extends BaseSearchFragment {

    @InjectPresenter
    SearchPresenter searchPresenter;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    protected SearchPresenterContract getPresenter() {
        return searchPresenter;
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
    public void onResume() {
        super.onResume();
        favoriteStatusUpdateListener();
    }

    private void favoriteStatusUpdateListener() {
        Disposable disposable = RxBus.getInstance().getSubject()
                .subscribe(
                        response -> checkFavoriteState((List<PersonModel>) response),
                        error -> Timber.e(error, "Error: update favorite status"));
        disposableManager.addDisposable(disposable);
    }

    private void checkFavoriteState(List<PersonModel> modelList) {
        final List<Integer> updateIndexList = new ArrayList<>();
        if (modelList != null && !modelList.isEmpty()) {
            final List<PersonModel> copyAdapterData = new ArrayList<>(personAdapter.getData());
            modelList.retainAll(copyAdapterData);
            setupNewItems(modelList, updateIndexList);
            updateAdapter(updateIndexList);
        }
    }

    private void setupNewItems(List<PersonModel> personsFromDatabase, List<Integer> updateIndexList) {
        for (PersonModel currModel : personsFromDatabase) {
            if (currModel == null) continue;
            if (!personAdapter.getData().contains(currModel)) continue;
            int index = personAdapter.getData().indexOf(currModel);
            updateIndexList.add(index);
            personAdapter.getData().set(index, currModel);
        }
    }

    private void updateAdapter(final List<Integer> updateIndexList) {
        for (Integer index : updateIndexList) {
            personAdapter.notifyItemChanged(index);
        }
    }
}
