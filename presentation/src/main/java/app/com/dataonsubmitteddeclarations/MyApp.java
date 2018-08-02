package app.com.dataonsubmitteddeclarations;

import android.app.Application;

import app.com.dataonsubmitteddeclarations.di.InjectHelper;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        InjectHelper.initMainAppComponent(this);
    }
}
