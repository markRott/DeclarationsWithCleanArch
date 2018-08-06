package app.com.dataonsubmitteddeclarations.base;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import javax.inject.Inject;

import app.com.dataonsubmitteddeclarations.managers.CompositeDisposableManager;

public abstract class BasePresenter<View extends MvpView> extends MvpPresenter<View> {

    @Inject
    protected CompositeDisposableManager disposableManager;

    @Override
    public void onDestroy() {
        if (disposableManager != null) {
            disposableManager.clearCompositeDisposable();
            disposableManager = null;
        }
        super.onDestroy();
    }
}
