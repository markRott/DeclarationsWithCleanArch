package app.com.dataonsubmitteddeclarations.di;

import app.com.dataonsubmitteddeclarations.MyApp;
import app.com.dataonsubmitteddeclarations.di.components.DaggerMainAppComponent;
import app.com.dataonsubmitteddeclarations.di.components.MainAppComponent;
import app.com.dataonsubmitteddeclarations.di.modules.ContextModule;
import app.com.dataonsubmitteddeclarations.di.modules.NetworkModule;
import app.com.dataonsubmitteddeclarations.di.modules.PersonsModule;
import app.com.dataonsubmitteddeclarations.di.modules.ThreadModule;
import app.com.dataonsubmitteddeclarations.di.modules.UtilsModule;

public class InjectHelper {

    private static MainAppComponent mainAppComponent;

    private InjectHelper() {
    }

    public static MainAppComponent initMainAppComponent(final MyApp application) {
        if (mainAppComponent != null) return mainAppComponent;
        return mainAppComponent = DaggerMainAppComponent
                .builder()
                .contextModule(new ContextModule(application))
                .networkModule(new NetworkModule())
                .threadModule(new ThreadModule())
                .utilsModule(new UtilsModule())
                .personsModule(new PersonsModule())
                .build();
//        return null;
    }

    public static MainAppComponent getMainAppComponent() {
        return mainAppComponent;
    }
}
