package app.com.data.models.networkentity;

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

    public int getBatchSize() {
        return batchSize;
    }

    public int getCurrentPage() {
        return currentPage;
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
