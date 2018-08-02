package app.com.domain.models;

import java.io.Serializable;
import java.util.List;

public class PersonsDomainModel implements Serializable {

    private PageDomainModel pageDomainModel;
    private List<PersonDomainModel> items;

    public List<PersonDomainModel> getItems() {
        return items;
    }

    public void setItems(List<PersonDomainModel> items) {
        this.items = items;
    }

    public PageDomainModel getPageDomainModel() {
        return pageDomainModel;
    }

    public void setPageDomainModel(PageDomainModel pageDomainModel) {
        this.pageDomainModel = pageDomainModel;
    }

    @Override
    public String toString() {
        return "PersonsDomainModel{" +
                "pageDomainModel=" + pageDomainModel +
                ", items=" + items +
                '}';
    }
}
