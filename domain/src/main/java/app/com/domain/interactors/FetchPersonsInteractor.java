package app.com.domain.interactors;

import java.util.List;

import app.com.domain.interfaces.FetchPersonsRepository;
import app.com.domain.interfaces.ThreadContract;
import app.com.domain.models.PersonModel;
import io.reactivex.Flowable;

public final class FetchPersonsInteractor implements FetchPersonsContract{

    private final ThreadContract threadContract;
    private final FetchPersonsRepository fetchPersonsRepository;

    public FetchPersonsInteractor(
            final ThreadContract threadContract,
            final FetchPersonsRepository fetchPersonsRepository) {
        this.threadContract = threadContract;
        this.fetchPersonsRepository = fetchPersonsRepository;
    }

    @Override
    public Flowable<List<PersonModel>> fetchPersonsByName(final String personName) {
        return fetchPersonsRepository
                .fetchPersonsByName(personName)
                .subscribeOn(threadContract.io())
                .observeOn(threadContract.ui());
    }
}
