package app.com.domain.models;

import java.io.Serializable;

public class PageModel implements Serializable {

    private String totalItems;
    private int batchSize;
    private int currentPage;

    public void setTotalItems(String totalItems) {
        this.totalItems = totalItems;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return "PageModel{" +
                "totalItems='" + totalItems + '\'' +
                ", batchSize=" + batchSize +
                ", currentPage=" + currentPage +
                '}';
    }
}
