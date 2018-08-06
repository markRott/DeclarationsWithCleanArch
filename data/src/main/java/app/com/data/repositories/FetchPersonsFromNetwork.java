package app.com.data.repositories;

import java.util.List;

import app.com.data.models.networkentity.PersonsEntity;
import app.com.data.models.transform.entitytomodel.PersonsEntityToDomainModel;
import app.com.data.network.ApplicationApi;
import app.com.domain.interfaces.FetchPersonsRepository;
import app.com.domain.models.PersonModel;
import app.com.domain.models.PersonsModel;
import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class FetchPersonsFromNetwork implements FetchPersonsRepository {

    private final ApplicationApi api;

    public FetchPersonsFromNetwork(final ApplicationApi api) {
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
