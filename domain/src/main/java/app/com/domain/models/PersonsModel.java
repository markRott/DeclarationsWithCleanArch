package app.com.domain.models;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PersonsModel implements Serializable {

    private PageModel pageModel;
    private List<PersonModel> items;

    public List<PersonModel> getItems() {
        return items == null ? Collections.<PersonModel>emptyList() : items;
    }

    public void setItems(List<PersonModel> items) {
        this.items = items;
    }

    public void setPageModel(PageModel pageModel) {
        this.pageModel = pageModel;
    }

    @Override
    public String toString() {
        return "PersonsModel{" +
                "pageModel=" + pageModel +
                ", items=" + Arrays.toString(getItems().toArray()) +
                '}';
    }
}
