package app.com.domain.interfaces;

import app.com.domain.models.PersonsDomainModel;
import io.reactivex.Flowable;

public interface PersonRepository {

    Flowable<PersonsDomainModel> fetchPersonsByName(final String name);
}
