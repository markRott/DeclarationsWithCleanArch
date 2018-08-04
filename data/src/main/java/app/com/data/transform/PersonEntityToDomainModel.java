package app.com.data.transform;

import java.util.ArrayList;
import java.util.List;

import app.com.data.models.PersonEntity;
import app.com.domain.models.PersonModel;

public class PersonEntityToDomainModel {

    private static final int MAX_LIST_SIZE = 200;

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
            personModel.setFirstName(personEntity.getFirstname());
            personModel.setLastName(personEntity.getLastname());
            personModel.setPlaceOfWork(personEntity.getPlaceOfWork());
            personModel.setPosition(personEntity.getPosition());
            personModel.setLinkPdf(personEntity.getLinkPDF());
        }
        return personModel;
    }
}
