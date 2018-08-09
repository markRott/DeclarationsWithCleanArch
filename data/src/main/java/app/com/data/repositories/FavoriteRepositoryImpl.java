package app.com.data.repositories;

import java.util.concurrent.Callable;

import app.com.data.models.transform.databasetodonain.DatabasePersonModel;
import app.com.data.models.transform.domaintodatabase.PersonModelToDatabase;
import app.com.domain.interfaces.FavoriteRepository;
import app.com.domain.models.PersonModel;
import io.reactivex.Single;
import io.realm.Realm;
import io.realm.RealmResults;

public class FavoriteRepositoryImpl implements FavoriteRepository {

    @Override
    public Single<PersonModel> executeFavoriteRequest(final PersonModel personModel) {
        if (personModel.isFavoriteStatus()) {
            return addFavoriteRequest(personModel);
        } else {
            return removeFromFavoriteRequest(personModel);
        }
    }

    private Single<PersonModel> addFavoriteRequest(final PersonModel personModel) {
        return Single.fromCallable(new Callable<PersonModel>() {
            @Override
            public PersonModel call() throws InterruptedException {
                Thread.sleep(9000);// fake request
                final Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                realm.copyToRealmOrUpdate(transform(personModel));
                personModel.setDraftComment(false);
                realm.commitTransaction();
                realm.close();
                return personModel;
            }
        });
    }

    private Single<PersonModel> removeFromFavoriteRequest(final PersonModel personModel) {
        return Single.fromCallable(new Callable<PersonModel>() {
            @Override
            public PersonModel call() throws InterruptedException {
                Thread.sleep(9000);// fake request
                final Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                final RealmResults<DatabasePersonModel> result = getAllObjects(realm, personModel);
                final boolean removeState = result.deleteAllFromRealm();
                updateModelAfterRemove(personModel, removeState);
                realm.commitTransaction();
                realm.close();
                return personModel;
            }
        });
    }

    private RealmResults<DatabasePersonModel> getAllObjects(
            final Realm realm,
            final PersonModel personModel) {
        return realm
                .where(DatabasePersonModel.class)
                .equalTo(DatabasePersonModel.ID, personModel.getId())
                .findAll();
    }

    private void updateModelAfterRemove(final PersonModel personModel, boolean removeState) {
        if (removeState) {
            personModel.setComment("");
            personModel.setDraftComment(false);
            personModel.setRemoveComment(false);
            personModel.setRemoveFavoriteItem(true);
        }
    }

    private DatabasePersonModel transform(PersonModel personModel) {
        final PersonModelToDatabase transformObject = new PersonModelToDatabase();
        return transformObject.transform(personModel);
    }
}
