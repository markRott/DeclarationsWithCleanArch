package app.com.data.models.networkentity;

import com.google.gson.annotations.SerializedName;

public class PersonEntity {

    @SerializedName("linkPDF")
    private String linkPdf;
    @SerializedName("position")
    private String position;
    @SerializedName("placeOfWork")
    private String placeOfWork;
    @SerializedName("lastname")
    private String lastName;
    @SerializedName("firstname")
    private String firstName;
    @SerializedName("id")
    private String id;

    public String getLinkPDF() {
        return linkPdf;
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

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PersonEntity{" +
                "linkPDF='" + linkPdf + '\'' +
                ", position='" + position + '\'' +
                ", placeOfWork='" + placeOfWork + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
