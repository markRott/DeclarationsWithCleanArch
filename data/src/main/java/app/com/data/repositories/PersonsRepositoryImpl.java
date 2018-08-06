package app.com.data.repositories;

import java.util.List;

import app.com.data.models.networkentity.PersonsEntity;
import app.com.data.models.transform.PersonsEntityToDomainModel;
import app.com.data.network.ApplicationApi;
import app.com.domain.interfaces.PersonsRepository;
import app.com.domain.models.PersonModel;
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
    public Flowable<List<PersonModel>> fetchPersonsByName(final String personName) {
        return api
                .fetchPersons(personName)
                .map(new TransformOperator());
    }

    private class TransformOperator implements Function<PersonsEntity, List<PersonModel>> {
        @Override
        public List<PersonModel> apply(@NonNull PersonsEntity personsEntity) {
            final PersonsModel personModel = new PersonsEntityToDomainModel().transform(personsEntity);
            return personModel.getItems();
        }
    }
}
