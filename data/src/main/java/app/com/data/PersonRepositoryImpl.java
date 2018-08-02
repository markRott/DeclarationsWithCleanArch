package app.com.data;

import app.com.data.models.PersonsEntity;
import app.com.data.transform.PersonsEntityToDomainModel;
import app.com.domain.interfaces.PersonRepository;
import app.com.domain.models.PersonsModel;
import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class PersonRepositoryImpl implements PersonRepository {

    private ApplicationApi api;

    public PersonRepositoryImpl(ApplicationApi api) {
        this.api = api;
    }

    @Override
    public Flowable<PersonsModel> fetchPersonsByName(String personName) {
        return api.fetchPersons(personName).map(new TransformOperator());
    }

    private class TransformOperator implements Function<PersonsEntity, PersonsModel> {
        @Override
        public PersonsModel apply(@NonNull PersonsEntity personsEntity) {
            return new PersonsEntityToDomainModel().transform(personsEntity);
        }
    }
}
