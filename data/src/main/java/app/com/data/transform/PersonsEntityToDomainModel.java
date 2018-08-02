package app.com.data.transform;

import java.util.List;

import app.com.data.models.PageEntity;
import app.com.data.models.PersonsEntity;
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

        final PersonEntityToDomainModel entityToDomain = new PersonEntityToDomainModel();
        final List<PersonModel> personModelList = entityToDomain.transform(personsEntity.getItems());
        personsModel.setItems(personModelList);
    }
}
