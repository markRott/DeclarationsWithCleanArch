package app.com.domain.interactors;

import java.util.List;

import app.com.domain.models.PersonModel;
import io.reactivex.Flowable;

public interface FetchPersonsContract {

    Flowable<List<PersonModel>> fetchPersonsByName(final String personName);
}
