package app.com.domain.interactors;

import app.com.domain.interfaces.FavoriteRepository;
import app.com.domain.interfaces.ThreadContract;
import app.com.domain.models.PersonModel;
import io.reactivex.Single;

public class FavoriteInteractor {

    private final ThreadContract threadContract;
    private final FavoriteRepository favoriteRepository;

    public FavoriteInteractor(
            final ThreadContract threadContract,
            final FavoriteRepository favoriteRepository) {

        this.threadContract = threadContract;
        this.favoriteRepository = favoriteRepository;
    }

    public Single<PersonModel> favoriteRequest(PersonModel personModel) {
        return favoriteRepository
                .executeFavoriteRequest(personModel)
                .subscribeOn(threadContract.io())
                .observeOn(threadContract.ui());
    }
}
