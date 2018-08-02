package app.com.data.network;

import app.com.data.models.PersonsEntity;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApplicationApi {

    @GET("/v1/declaration/")
    Flowable<PersonsEntity> fetchPersons(@Query("q") String personName);
}
