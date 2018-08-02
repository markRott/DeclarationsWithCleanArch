package app.com.data.repositories;

import app.com.data.models.PersonsEntity;
import app.com.data.network.ApplicationApi;
import app.com.data.transform.PersonsEntityToDomainModel;
import app.com.domain.interfaces.PersonRepository;
import app.com.domain.models.PersonsModel;
import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class PersonRepositoryImpl implements PersonRepository {

    private final ApplicationApi api;

    public PersonRepositoryImpl(final ApplicationApi api) {
        this.api = api;
    }

    @Override
    public Flowable<PersonsModel> fetchPersonsByName(final String personName) {
        return api.fetchPersons(personName)
                .map(new TransformOperator())
                .doOnNext(new Consumer<PersonsModel>() {
                    @Override
                    public void accept(PersonsModel personsModel) throws Exception {
                        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
                    }
                });
    }

    private class TransformOperator implements Function<PersonsEntity, PersonsModel> {
        @Override
        public PersonsModel apply(@NonNull PersonsEntity personsEntity) {
            return new PersonsEntityToDomainModel().transform(personsEntity);
        }
    }
}
