package app.com.data;

import app.com.domain.interfaces.PersonRepository;
import app.com.domain.models.PersonsDomainModel;
import io.reactivex.Flowable;

public class PersonRepositoryImpl implements PersonRepository {

    @Override
    public Flowable<PersonsDomainModel> fetchPersonsByName(String name) {
        return null;
    }
}
