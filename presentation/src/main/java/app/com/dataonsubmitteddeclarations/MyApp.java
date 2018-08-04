package app.com.dataonsubmitteddeclarations;

import android.app.Application;

import app.com.dataonsubmitteddeclarations.di.InjectHelper;
import timber.log.Timber;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        InjectHelper.initMainAppComponent(this);
    }
}
