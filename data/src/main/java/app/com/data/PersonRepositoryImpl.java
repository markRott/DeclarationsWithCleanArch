package app.com.data;

import app.com.domain.interfaces.PersonRepository;
import app.com.domain.models.PersonsModel;
import io.reactivex.Flowable;

public class PersonRepositoryImpl implements PersonRepository {

    @Override
    public Flowable<PersonsModel> fetchPersonsByName(String name) {
        return null;
    }
}
