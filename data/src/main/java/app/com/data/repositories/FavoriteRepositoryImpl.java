package app.com.data.repositories;

import java.util.concurrent.Callable;

import app.com.data.models.CachePersonModel;
import app.com.data.models.transform.PersonModelToCache;
import app.com.domain.interfaces.FavoriteRepository;
import app.com.domain.models.PersonModel;
import io.reactivex.Single;
import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;
import timber.log.Timber;

public class FavoriteRepositoryImpl implements FavoriteRepository {

    @Override
    public Single<PersonModel> executeFavoriteRequest(final PersonModel personModel) {
        if (personModel.isFavorite()) {
            return addFavoriteRequest(personModel);
        } else {
            return removeFromFavoriteRequest(personModel);
        }
    }

    private Single<PersonModel> addFavoriteRequest(final PersonModel personModel) {
        return Single.fromCallable(new Callable<PersonModel>() {
            @Override
            public PersonModel call() {
                final Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                final RealmModel model = realm.copyToRealmOrUpdate(transform(personModel));
                realm.commitTransaction();
                realm.close();
                Timber.d("Save favorite person to data base = %s", personModel.toString());
                return personModel;
            }
        });
    }

    private Single<PersonModel> removeFromFavoriteRequest(final PersonModel personModel) {
        return Single.fromCallable(new Callable<PersonModel>() {
            @Override
            public PersonModel call() {
                final Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                final RealmResults<CachePersonModel> result = realm
                        .where(CachePersonModel.class)
                        .equalTo(CachePersonModel.ID, personModel.getId())
                        .findAll();
                boolean removeState = result.deleteAllFromRealm();
                realm.commitTransaction();
                realm.close();
                Timber.d("Remove favorite person from data base = %s", personModel.toString());
                return personModel;
            }
        });
    }

    private CachePersonModel transform(PersonModel personModel) {
        final PersonModelToCache transformObject = new PersonModelToCache();
        final CachePersonModel cachePersonModel = transformObject.transform(personModel);
        Timber.d("Cache person model = %s", cachePersonModel.toString());
        return cachePersonModel;
    }
}
