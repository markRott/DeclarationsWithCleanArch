package app.com.domain.interfaces;

import app.com.domain.models.PersonsModel;
import io.reactivex.Flowable;

public interface PersonRepository {

    Flowable<PersonsModel> fetchPersonsByName(final String personName);
}
