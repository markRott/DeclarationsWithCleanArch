package app.com.data.cache;

import io.realm.Realm;

public class RealmHolder {

    private Realm realm;
    private static RealmHolder instance;

    private RealmHolder() {
        realm = Realm.getDefaultInstance();
    }

    public static RealmHolder getInstance() {
        if (instance == null) {
            instance = new RealmHolder();
        }
        return instance;
    }

    public Realm getRealm() {
        return realm;
    }
}
