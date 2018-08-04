package app.com.dataonsubmitteddeclarations.base;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatFragment;

import javax.inject.Inject;

import app.com.dataonsubmitteddeclarations.di.InjectHelper;
import app.com.dataonsubmitteddeclarations.utils.CompositeDisposableManager;

public abstract class BaseFragment extends MvpAppCompatFragment {

    @Inject
    protected CompositeDisposableManager disposableManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectHelper.getMainAppComponent().inject(this);
    }

    @Override
    public void onPause() {
        disposableManager.clearCompositeDisposable();
        super.onPause();
    }
}
