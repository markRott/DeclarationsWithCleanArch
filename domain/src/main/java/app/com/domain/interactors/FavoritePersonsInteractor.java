package app.com.domain.interactors;

import java.util.List;

import app.com.domain.interfaces.FetchPersonsRepository;
import app.com.domain.interfaces.ThreadContract;
import app.com.domain.models.PersonModel;
import io.reactivex.Flowable;

public class FavoritePersonsInteractor {

    private final ThreadContract threadContract;
    private final FetchPersonsRepository fetchPersonsRepository;

    public FavoritePersonsInteractor(
            final ThreadContract threadContract,
            final FetchPersonsRepository fetchPersonsRepository) {
        this.threadContract = threadContract;
        this.fetchPersonsRepository = fetchPersonsRepository;
    }

    public Flowable<List<PersonModel>> fetchPersonsByName(final String personName) {
        return fetchPersonsRepository
                .fetchPersonsByName(personName)
                .subscribeOn(threadContract.io())
                .observeOn(threadContract.ui());
    }
}
