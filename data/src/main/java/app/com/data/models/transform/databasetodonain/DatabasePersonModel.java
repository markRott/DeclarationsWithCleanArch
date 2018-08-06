package app.com.data.models.transform.databasetodonain;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmField;

public class DatabasePersonModel extends RealmObject {

    public static final String ID = "id";

    @PrimaryKey
    @RealmField(name = ID)
    private String id;

    private String linkPdf;
    private String position;
    private String placeOfWork;
    private String lastName;
    private String firstName;

    // custom fields
    private String comment;
    private boolean favoriteStatus;

    public String getLinkPdf() {
        return linkPdf;
    }

    public void setLinkPdf(String linkPdf) {
        this.linkPdf = linkPdf;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPlaceOfWork() {
        return placeOfWork;
    }

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isFavorite() {
        return favoriteStatus;
    }

    public void setFavoriteStatus(boolean favoriteStatus) {
        this.favoriteStatus = favoriteStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isFavoriteStatus() {
        return favoriteStatus;
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
                "id ='" + id + '\'' +
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