package app.com.dataonsubmitteddeclarations.base;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatFragment;

import javax.inject.Inject;

import app.com.dataonsubmitteddeclarations.di.InjectHelper;
import app.com.dataonsubmitteddeclarations.managers.CompositeDisposableManager;
import app.com.dataonsubmitteddeclarations.managers.Router;

public abstract class BaseFragment extends MvpAppCompatFragment {

    @Inject
    protected CompositeDisposableManager disposableManager;
    @Inject
    protected Router router;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectHelper.getMainAppComponent().inject(this);
    }

    @Override
    public void onPause() {
        if (disposableManager != null) {
            disposableManager.clearCompositeDisposable();
        }
        super.onPause();
    }

    @Override
    public void onDestroy() {
        if (disposableManager != null) {
            disposableManager.clearCompositeDisposable();
            disposableManager = null;
        }
        super.onDestroy();
    }
}
