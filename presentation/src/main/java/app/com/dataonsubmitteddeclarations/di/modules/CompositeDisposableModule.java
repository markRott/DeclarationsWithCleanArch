package app.com.dataonsubmitteddeclarations.di.modules;

import android.annotation.SuppressLint;

import app.com.dataonsubmitteddeclarations.utils.CompositeDisposableManager;
import dagger.Module;
import dagger.Provides;
import timber.log.Timber;

@Module
public class CompositeDisposableModule {

    @SuppressLint("TimberArgCount")
    @Provides
    public CompositeDisposableManager provideCompositeDisposableManager() {
        CompositeDisposableManager manager = new CompositeDisposableManager();
        Timber.d("provideCompositeDisposableManager", manager.toString());
        return manager;
    }
}
