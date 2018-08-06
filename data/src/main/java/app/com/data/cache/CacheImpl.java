package app.com.data.cache;

import java.util.List;

import app.com.data.models.CachePersonModel;
import io.realm.Realm;
import io.realm.RealmResults;

public class CacheImpl implements CacheContract {

    @Override
    public boolean isCached() {
        final Realm realm = RealmHolder.getInstance().getRealm();
        final RealmResults<CachePersonModel> response = realm.where(CachePersonModel.class).findAll();
        return response != null && response.size() > 0;
    }

    @Override
    public void saveFavoriteItem(CachePersonModel cachePersonModel) {

    }

    @Override
    public List<CachePersonModel> getFavoritePersons() {
        final Realm realm = RealmHolder.getInstance().getRealm();
        final List<CachePersonModel> list = realm.where(CachePersonModel.class).findAll();
//        final List<CountryEntity> entityList = countryRealmModelToEntity.transform(list);
        return list;
    }
}
