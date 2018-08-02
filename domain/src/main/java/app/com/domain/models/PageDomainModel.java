package app.com.domain.models;

import java.io.Serializable;

public class PageDomainModel implements Serializable {

    private String totalItems;
    private int batchSize;
    private int currentPage;

    public String getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(String totalItems) {
        this.totalItems = totalItems;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return "PageDomainModel{" +
                "totalItems='" + totalItems + '\'' +
                ", batchSize=" + batchSize +
                ", currentPage=" + currentPage +
                '}';
    }
}
