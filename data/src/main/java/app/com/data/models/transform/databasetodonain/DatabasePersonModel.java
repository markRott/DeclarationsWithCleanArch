package app.com.data.models.transform.databasetodonain;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmField;

public class DatabasePersonModel extends RealmObject {

    public static final String ID = "id";

    public static final String FIRST_NAME = "firstName";
    public static final String MIDDLE_NAME = "middleName";
    public static final String LAST_NAME = "lastName";

    @PrimaryKey
    @RealmField(name = ID)
    private String id;
    @RealmField(name = FIRST_NAME)
    private String firstName;
    @RealmField(name = MIDDLE_NAME)
    private String middleName;
    @RealmField(name = LAST_NAME)
    private String lastName;

    private String linkPdf;
    private String position;
    private String placeOfWork;

    // custom fields
    private String comment;
    private boolean favoriteStatus;

    public String getLinkPdf() {
        return linkPdf;
    }

    public String getPosition() {
        return position;
    }

    public String getPlaceOfWork() {
        return placeOfWork;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public String getMiddleName() {
        return middleName;
    }

    public boolean isFavorite() {
        return favoriteStatus;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLinkPdf(String linkPdf) {
        this.linkPdf = linkPdf;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setFavoriteStatus(boolean favoriteStatus) {
        this.favoriteStatus = favoriteStatus;
    }

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof DatabasePersonModel)) return false;
        DatabasePersonModel personModel = (DatabasePersonModel) o;
        return id.equals(personModel.id);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "DatabasePersonModel{" +
                "id='" + id + '\'' +
                ", linkPdf='" + linkPdf + '\'' +
                ", position='" + position + '\'' +
                ", placeOfWork='" + placeOfWork + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", comment='" + comment + '\'' +
                ", favoriteStatus=" + favoriteStatus +
                '}';
    }
}
