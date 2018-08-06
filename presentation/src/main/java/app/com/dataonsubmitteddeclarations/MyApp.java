package app.com.dataonsubmitteddeclarations;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import app.com.dataonsubmitteddeclarations.di.InjectHelper;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initTimber();
        initRealm();

        InjectHelper.initMainAppComponent(this);
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("personData.realm").build();
        Timber.d(config.getRealmFileName());
        Realm.setDefaultConfiguration(config);
    }

    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
