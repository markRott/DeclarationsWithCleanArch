package app.com.domain.interfaces;

import java.util.List;

import app.com.domain.models.PersonModel;
import app.com.domain.models.PersonsModel;
import io.reactivex.Flowable;

public interface FetchPersonsRepository {

    Flowable<List<PersonModel>> fetchPersonsByName(final String personName);
}
