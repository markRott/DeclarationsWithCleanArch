package app.com.data.models.networkentity;

import com.google.gson.annotations.SerializedName;

public class PersonEntity {

    @SerializedName("linkPDF")
    private String linkPDF;
    @SerializedName("position")
    private String position;
    @SerializedName("placeOfWork")
    private String placeOfWork;
    @SerializedName("lastname")
    private String lastname;
    @SerializedName("firstname")
    private String firstname;
    @SerializedName("id")
    private String id;

    public String getLinkPDF() {
        return linkPDF;
    }

    public void setLinkPDF(String linkPDF) {
        this.linkPDF = linkPDF;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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
                "linkPDF='" + linkPDF + '\'' +
                ", position='" + position + '\'' +
                ", placeOfWork='" + placeOfWork + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
