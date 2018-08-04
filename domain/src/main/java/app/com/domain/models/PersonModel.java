package app.com.domain.models;

import java.io.Serializable;

public class PersonModel implements Serializable {

    private String id;
    private String linkPdf;
    private String position;
    private String placeOfWork;
    private String lastName;
    private String firstName;

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

    @Override
    public String toString() {
        return "PersonModel{" +
                "id='" + id + '\'' +
                ", linkPdf='" + linkPdf + '\'' +
                ", position='" + position + '\'' +
                ", placeOfWork='" + placeOfWork + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", favoriteStatus=" + favoriteStatus +
                '}';
    }
}
