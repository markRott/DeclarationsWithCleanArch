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

    private PersonModel transform(DatabasePersonModel databaseModel) {
        final PersonModel personModel = new PersonModel();
        if (databaseModel != null) {
            personModel.setId(databaseModel.getId());
            personModel.setFirstName(databaseModel.getFirstName().toLowerCase());
            personModel.setLastName(databaseModel.getLastName().toLowerCase());
            personModel.setMiddleName(databaseModel.getMiddleName().toLowerCase());
            personModel.setPlaceOfWork(databaseModel.getPlaceOfWork());
            personModel.setPosition(databaseModel.getPosition());
            personModel.setLinkPdf(databaseModel.getLinkPdf());
            personModel.setComment(databaseModel.getComment());
            personModel.setFavoriteStatus(databaseModel.isFavorite());
        }
        return personModel;
    }
}
