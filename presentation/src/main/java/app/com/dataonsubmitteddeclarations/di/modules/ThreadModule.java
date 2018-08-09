package app.com.dataonsubmitteddeclarations.di.modules;

import javax.inject.Singleton;

import app.com.dataonsubmitteddeclarations.utils.ThreadContractImpl;
import app.com.domain.interfaces.ThreadContract;
import dagger.Module;
import dagger.Provides;

@Module
public class ThreadModule {

    @Provides
    @Singleton
    public ThreadContract provideThreadContract() {
        return new ThreadContractImpl();
    }
}
