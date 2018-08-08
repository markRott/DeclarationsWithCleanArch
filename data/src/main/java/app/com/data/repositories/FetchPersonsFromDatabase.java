package app.com.data.repositories;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import app.com.data.models.transform.databasetodonain.DatabasePersonModel;
import app.com.data.models.transform.databasetodonain.DatabasePersonModelToDomain;
import app.com.domain.interfaces.FetchPersonsRepository;
import app.com.domain.models.PersonModel;
import io.reactivex.Flowable;
import io.realm.Realm;
import timber.log.Timber;

public class FetchPersonsFromDatabase implements FetchPersonsRepository {

    @Override
    public Flowable<List<PersonModel>> fetchPersonsByName(final String personName) {
        return Flowable.fromCallable(new Callable<List<PersonModel>>() {
            @Override
            public List<PersonModel> call() {
                final Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                final List<DatabasePersonModel> result = query(realm, personName);
                final List<PersonModel> personModelList = convertDatabaseModelToDomain(result);
                realm.commitTransaction();
                realm.close();
                return personModelList;
            }
        });
    }

    private List<DatabasePersonModel> query(final Realm realm, final String personName) {
        return realm
                .where(DatabasePersonModel.class)
                .beginGroup()
                .contains(DatabasePersonModel.FIRST_NAME, personName)
                .or()
                .contains(DatabasePersonModel.LAST_NAME, personName)
                .or()
                .contains(DatabasePersonModel.MIDDLE_NAME, personName)
                .endGroup()
                .findAll();
    }

    private final List<PersonModel> convertDatabaseModelToDomain(final List<DatabasePersonModel> result){
        if(result == null) return Collections.emptyList();
        final DatabasePersonModelToDomain converter = new DatabasePersonModelToDomain();
        return converter.transform(result);
    }
}
