package app.com.dataonsubmitteddeclarations.di.modules;

import android.annotation.SuppressLint;

import app.com.dataonsubmitteddeclarations.managers.CompositeDisposableManager;
import dagger.Module;
import dagger.Provides;
import timber.log.Timber;

@Module
public class CompositeDisposableModule {

    @SuppressLint("TimberArgCount")
    @Provides
    public CompositeDisposableManager provideCompositeDisposableManager() {
        return new CompositeDisposableManager();
    }
}
