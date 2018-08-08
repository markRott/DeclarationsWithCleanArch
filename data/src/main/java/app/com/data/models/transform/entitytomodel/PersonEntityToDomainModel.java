package app.com.data.models.transform.entitytomodel;

import java.util.ArrayList;
import java.util.List;

import app.com.data.models.networkentity.PersonEntity;
import app.com.domain.models.PersonModel;

public class PersonEntityToDomainModel {

    private static final int MAX_LIST_SIZE = 50;
    private static final int FIRST_NAME_INDEX = 0;
    private static final int MIDDLE_NAME_INDEX = 1;

    public List<PersonModel> transform(List<PersonEntity> personEntityList) {
        final List<PersonModel> personModelList = new ArrayList<>(personEntityList.size());
        if (personEntityList.size() > MAX_LIST_SIZE) {
            personEntityList = personEntityList.subList(0, MAX_LIST_SIZE);
        }
        for (PersonEntity currPersonEntity : personEntityList) {
            if (currPersonEntity == null) continue;
            personModelList.add(transform(currPersonEntity));
        }
        return personModelList;
    }

    private PersonModel transform(PersonEntity personEntity) {
        final PersonModel personModel = new PersonModel();
        if (personEntity != null) {
            personModel.setId(personEntity.getId());
            divideNameFromMiddleName(personModel, personEntity);
            personModel.setLastName(personEntity.getLastName().toLowerCase());
            personModel.setPlaceOfWork(personEntity.getPlaceOfWork());
            personModel.setPosition(personEntity.getPosition());
            personModel.setLinkPdf(personEntity.getLinkPDF());
            personModel.setDraftComment(true);
        }
        return personModel;
    }

    private void divideNameFromMiddleName(
            final PersonModel personModel,
            final PersonEntity personEntity) {
        final String[] nameArray = personEntity.getFirstName().split(" ");
        personModel.setFirstName((nameArray[FIRST_NAME_INDEX] == null || nameArray[FIRST_NAME_INDEX].isEmpty()) ?
                "" : nameArray[FIRST_NAME_INDEX].toLowerCase());
        personModel.setMiddleName((nameArray[MIDDLE_NAME_INDEX] == null || nameArray[MIDDLE_NAME_INDEX].isEmpty()) ?
                "" : nameArray[MIDDLE_NAME_INDEX].toLowerCase());
    }
}
