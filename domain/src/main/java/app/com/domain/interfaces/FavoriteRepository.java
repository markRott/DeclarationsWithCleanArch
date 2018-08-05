package app.com.domain.interfaces;

import app.com.domain.models.PersonModel;
import io.reactivex.Single;

public interface FavoriteRepository {

    Single<PersonModel> executeFavoriteRequest(final PersonModel personModel);
}
