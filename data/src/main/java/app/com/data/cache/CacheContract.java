package app.com.data.cache;

import java.util.List;

import app.com.data.models.CachePersonModel;

public interface CacheContract {

    boolean isCached();

    void saveFavoriteItem(CachePersonModel cachePersonModel);

    List<CachePersonModel> getFavoritePersons();
}
