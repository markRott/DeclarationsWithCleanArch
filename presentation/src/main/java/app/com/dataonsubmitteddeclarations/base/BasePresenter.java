package app.com.dataonsubmitteddeclarations.base;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import javax.inject.Inject;

import app.com.dataonsubmitteddeclarations.utils.CompositeDisposableManager;

public abstract class BasePresenter<View extends MvpView> extends MvpPresenter<View> {

    @Inject
    protected CompositeDisposableManager disposableManager;

    @Override
    public void onDestroy() {
        disposableManager.clearCompositeDisposable();
        disposableManager = null;
        super.onDestroy();
    }
}
