package app.com.data.repositories;

import java.util.List;
import java.util.concurrent.Callable;

import app.com.data.models.transform.databasetodonain.DatabasePersonModel;
import app.com.data.models.transform.databasetodonain.DatabasePersonModelToDomain;
import app.com.domain.interfaces.FetchPersonsRepository;
import app.com.domain.models.PersonModel;
import io.reactivex.Flowable;
import io.realm.Realm;

public class FetchPersonsFromDatabase implements FetchPersonsRepository {

    @Override
    public Flowable<List<PersonModel>> fetchPersonsByName(String personName) {
        return Flowable.fromCallable(new Callable<List<PersonModel>>() {
            @Override
            public List<PersonModel> call() {
                final Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                final List<DatabasePersonModel> result = realm.where(DatabasePersonModel.class).findAll();
                final DatabasePersonModelToDomain converter = new DatabasePersonModelToDomain();
                final List<PersonModel> personModelList = converter.transform(result);
                realm.commitTransaction();
                realm.close();
                return personModelList;
            }
        });
    }
}
