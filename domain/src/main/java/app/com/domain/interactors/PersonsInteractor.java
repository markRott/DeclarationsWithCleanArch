package app.com.domain.interactors;

import app.com.domain.interfaces.PersonsRepository;
import app.com.domain.interfaces.ThreadContract;
import app.com.domain.models.PersonsModel;
import io.reactivex.Flowable;

public class PersonsInteractor {

    private final ThreadContract threadContract;
    private final PersonsRepository personsRepository;

    public PersonsInteractor(
            final ThreadContract threadContract,
            final PersonsRepository personsRepository) {
        this.threadContract = threadContract;
        this.personsRepository = personsRepository;
    }

    public Flowable<PersonsModel> fetchPersonsByName(final String personName) {
        return personsRepository
                .fetchPersonsByName(personName)
                .subscribeOn(threadContract.io())
                .observeOn(threadContract.ui());
    }
}