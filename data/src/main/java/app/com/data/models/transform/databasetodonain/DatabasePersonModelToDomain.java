package app.com.data.models.transform.databasetodonain;

import java.util.ArrayList;
import java.util.List;

import app.com.domain.models.PersonModel;

public class DatabasePersonModelToDomain {

    public List<PersonModel> transform(List<DatabasePersonModel> databasePersonModels) {
        final List<PersonModel> personModelList = new ArrayList<>(databasePersonModels.size());
        for (DatabasePersonModel currDatabasePersonModel : databasePersonModels) {
            if (currDatabasePersonModel == null) continue;
            personModelList.add(transform(currDatabasePersonModel));
        }
        return personModelList;
    }

    private PersonModel transform(DatabasePersonModel databasePersonModel) {
        final PersonModel personModel = new PersonModel();
        if (personModel != null) {
            personModel.setId(databasePersonModel.getId());
            personModel.setFirstName(databasePersonModel.getFirstName());
            personModel.setLastName(databasePersonModel.getLastName());
            personModel.setPlaceOfWork(databasePersonModel.getPlaceOfWork());
            personModel.setPosition(databasePersonModel.getPosition());
            personModel.setLinkPdf(databasePersonModel.getLinkPdf());
            personModel.setComment(databasePersonModel.getComment());
            personModel.setFavoriteStatus(databasePersonModel.isFavoriteStatus());
        }
        return personModel;
    }
}
