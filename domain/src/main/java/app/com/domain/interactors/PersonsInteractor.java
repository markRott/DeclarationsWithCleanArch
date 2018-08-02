package app.com.domain.interactors;

import app.com.domain.interfaces.PersonRepository;
import app.com.domain.interfaces.ThreadContract;
import app.com.domain.models.PersonsModel;
import io.reactivex.Flowable;

public class PersonsInteractor {

    private final ThreadContract threadContract;
    private final PersonRepository personRepository;

    public PersonsInteractor(
            final ThreadContract threadContract,
            final PersonRepository personRepository) {
        this.threadContract = threadContract;
        this.personRepository = personRepository;
    }

    public Flowable<PersonsModel> fetchPersonsByName(final String personName) {
        return personRepository
                .fetchPersonsByName(personName)
                .subscribeOn(threadContract.io())
                .observeOn(threadContract.ui());
    }
}
