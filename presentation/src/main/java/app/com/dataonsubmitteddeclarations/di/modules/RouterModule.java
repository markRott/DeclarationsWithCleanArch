package app.com.dataonsubmitteddeclarations.di.modules;

import javax.inject.Singleton;

import app.com.dataonsubmitteddeclarations.managers.Router;
import dagger.Module;
import dagger.Provides;

@Module
public class RouterModule {

    @Provides
    @Singleton
    public Router provideRouter() {
        return new Router();
    }
}
