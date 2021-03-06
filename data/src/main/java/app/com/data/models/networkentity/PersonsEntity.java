package app.com.data.models.networkentity;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PersonsEntity {

    @SerializedName("page")
    private PageEntity page;
    @SerializedName("items")
    private List<PersonEntity> items;

    public List<PersonEntity> getItems() {
        return items == null ? Collections.<PersonEntity>emptyList() : items;
    }

    public PageEntity getPage() {
        return page;
    }

    @Override
    public String toString() {
        return "PersonsEntity{" +
                "page=" + page +
                ", items=" + Arrays.toString(items.toArray()) +
                '}';
    }
}
