package app.com.data.repositories;

import app.com.data.models.PersonsEntity;
import app.com.data.network.ApplicationApi;
import app.com.data.transform.PersonsEntityToDomainModel;
import app.com.domain.interfaces.PersonsRepository;
import app.com.domain.models.PersonsModel;
import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import timber.log.Timber;

public class PersonsRepositoryImpl implements PersonsRepository {

    private final ApplicationApi api;

    public PersonsRepositoryImpl(final ApplicationApi api) {
        this.api = api;
    }

    @Override
    public Flowable<PersonsModel> fetchPersonsByName(final String personName) {
        return api
                .fetchPersons(personName)
                .map(new TransformOperator());
    }

    private class TransformOperator implements Function<PersonsEntity, PersonsModel> {
        @Override
        public PersonsModel apply(@NonNull PersonsEntity personsEntity) {
            PersonsModel personModel = new PersonsEntityToDomainModel().transform(personsEntity);
            Timber.d(personModel.toString());
            return personModel;
        }
    }
}
