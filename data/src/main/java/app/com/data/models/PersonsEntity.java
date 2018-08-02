package app.com.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonsEntity {

    @SerializedName("page")
    private PageEntity page;
    @SerializedName("items")
    private List<PersonEntity> items;

    public List<PersonEntity> getItems() {
        return items;
    }

    public void setItems(List<PersonEntity> items) {
        this.items = items;
    }

    public PageEntity getPage() {
        return page;
    }

    public void setPage(PageEntity page) {
        this.page = page;
    }
}
