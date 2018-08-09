package app.com.data.models.transform.domaintodatabase;

import app.com.data.models.transform.databasetodonain.DatabasePersonModel;
import app.com.domain.models.PersonModel;

public class PersonModelToDatabase {

    public DatabasePersonModel transform(PersonModel personModel) {
        final DatabasePersonModel cache = new DatabasePersonModel();
        if (personModel != null) {
            cache.setId(personModel.getId());
            cache.setFirstName(personModel.getFirstName());
            cache.setMiddleName(personModel.getMiddleName());
            cache.setLastName(personModel.getLastName().toLowerCase());
            cache.setPlaceOfWork(personModel.getPlaceOfWork());
            cache.setPosition(personModel.getPosition());
            cache.setLinkPdf(personModel.getLinkPdf());
            cache.setComment(personModel.getComment());
            cache.setFavoriteStatus(personModel.isFavoriteStatus());
        }
        return cache;
    }
}
