package app.com.data.models.transform;

import app.com.data.models.CachePersonModel;
import app.com.domain.models.PersonModel;

public class PersonModelToCache {

    public CachePersonModel transform(PersonModel personModel) {
        final CachePersonModel cache = new CachePersonModel();
        if (personModel != null) {
            cache.setId(personModel.getId());
            cache.setFirstName(personModel.getFirstName());
            cache.setLastName(personModel.getLastName());
            cache.setPlaceOfWork(personModel.getPlaceOfWork());
            cache.setPosition(personModel.getPosition());
            cache.setLinkPdf(personModel.getLinkPdf());
            cache.setComment(personModel.getComment());
            cache.setFavoriteStatus(personModel.isFavoriteStatus());
        }
        return cache;
    }
}
