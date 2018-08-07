package app.com.domain.models;

import java.io.Serializable;

public class PersonModel implements Serializable {

    private String id;
    private String linkPdf;
    private String position;
    private String placeOfWork;
    private String lastName;
    private String firstName;

    // custom fields
    private String comment;
    private String middleName;
    private boolean favoriteStatus;
    private int positionInAdapter = -1;
    private boolean progressBarVisibilityState;
    private boolean draftComment = true;

    public String getId() {
        return id;
    }

    public String getLinkPdf() {
        return linkPdf;
    }

    public String getPosition() {
        return position == null ? "" : position;
    }

    public String getPlaceOfWork() {
        return placeOfWork == null ? "" : placeOfWork;
    }

    public String getLastName() {
        return lastName == null ? "" : lastName;
    }

    public String getFirstName() {
        return firstName == null ? "" : firstName;
    }

    public String getComment() {
        return comment == null ? "" : comment;
    }

    public int getPositionInAdapter() {
        return positionInAdapter;
    }

    public String getMiddleName() {
        return middleName == null ? "" : middleName;
    }

    public boolean isFavoriteStatus() {
        return favoriteStatus;
    }

    public boolean isProgressBarVisibilityState() {
        return progressBarVisibilityState;
    }

    public boolean isDraftComment() {
        return draftComment;
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

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setFavoriteStatus(boolean favoriteStatus) {
        this.favoriteStatus = favoriteStatus;
    }

    public void setPositionInAdapter(int positionInAdapter) {
        this.positionInAdapter = positionInAdapter;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setProgressBarVisibilityState(boolean progressBarVisibilityState) {
        this.progressBarVisibilityState = progressBarVisibilityState;
    }

    public void setDraftComment(boolean draftComment) {
        this.draftComment = draftComment;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof PersonModel)) return false;
        PersonModel personModel = (PersonModel) o;
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
        return "PersonModel{" +
                "id='" + id + '\'' +
                ", linkPdf='" + linkPdf + '\'' +
                ", position='" + position + '\'' +
                ", placeOfWork='" + placeOfWork + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", comment='" + comment + '\'' +
                ", favoriteStatus=" + favoriteStatus +
                ", positionInAdapter=" + positionInAdapter +
                ", progressBarVisibilityState=" + progressBarVisibilityState +
                '}';
    }
}
