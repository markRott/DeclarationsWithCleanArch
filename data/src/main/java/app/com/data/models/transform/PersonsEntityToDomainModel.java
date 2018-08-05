package app.com.data.models.transform;

import java.util.Collections;
import java.util.List;

import app.com.data.models.networkentity.PageEntity;
import app.com.data.models.networkentity.PersonEntity;
import app.com.data.models.networkentity.PersonsEntity;
import app.com.domain.models.PersonModel;
import app.com.domain.models.PersonsModel;

public class PersonsEntityToDomainModel {

    public PersonsModel transform(final PersonsEntity personsEntity) {
        final PersonsModel personsModel = new PersonsModel();
        if (personsEntity != null) {
            convertPageEntity(personsEntity, personsModel);
            convertPersonEntity(personsEntity, personsModel);
        }
        return personsModel;
    }

    private void convertPageEntity(
            final PersonsEntity personsEntity,
            final PersonsModel personsModel) {

        final PageEntityToDomainModel entityToDomain = new PageEntityToDomainModel();
        final PageEntity pageEntity = personsEntity.getPage();
        personsModel.setPageModel(entityToDomain.transform(pageEntity));
    }

    private void convertPersonEntity(final PersonsEntity personsEntity,
                                     final PersonsModel personsModel) {
        List<PersonModel> personModelList = null;
        final List<PersonEntity> entities = personsEntity.getItems();
        final PersonEntityToDomainModel entityToDomain = new PersonEntityToDomainModel();
        if (!entities.isEmpty()) {
            personModelList = entityToDomain.transform(personsEntity.getItems());
        }
        personsModel.setItems(personModelList != null ?
                personModelList : Collections.<PersonModel>emptyList());
    }
}
