package app.com.dataonsubmitteddeclarations.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import app.com.dataonsubmitteddeclarations.MyApp;
import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private final MyApp application;

    public ContextModule(MyApp application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }
}
