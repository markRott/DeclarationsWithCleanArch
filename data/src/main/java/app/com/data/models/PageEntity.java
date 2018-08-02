package app.com.data.models;

import com.google.gson.annotations.SerializedName;

public class PageEntity {

    @SerializedName("totalItems")
    private String totalItems;
    @SerializedName("batchSize")
    private int batchSize;
    @SerializedName("currentPage")
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
        return "PageEntity{" +
                "totalItems='" + totalItems + '\'' +
                ", batchSize=" + batchSize +
                ", currentPage=" + currentPage +
                '}';
    }
}
