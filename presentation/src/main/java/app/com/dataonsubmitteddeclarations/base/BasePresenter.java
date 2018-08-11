package app.com.dataonsubmitteddeclarations.base;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import javax.inject.Inject;

import app.com.dataonsubmitteddeclarations.managers.CompositeDisposableManager;
import app.com.dataonsubmitteddeclarations.utils.network.NetworkContract;
import app.com.domain.interfaces.ThreadContract;

public abstract class BasePresenter<View extends MvpView> extends MvpPresenter<View> {

    @Inject
    protected CompositeDisposableManager disposableManager;
    @Inject
    protected ThreadContract threadContract;
    @Inject
    protected NetworkContract networkContract;

    @Override
    public void onDestroy() {
        if (disposableManager != null) {
            disposableManager.clearCompositeDisposable();
            disposableManager = null;
        }
        super.onDestroy();
    }
}
