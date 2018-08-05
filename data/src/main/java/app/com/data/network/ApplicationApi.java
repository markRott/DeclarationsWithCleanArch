package app.com.data.network;

import java.util.List;

import app.com.data.models.networkentity.PersonEntity;
import app.com.data.models.networkentity.PersonsEntity;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApplicationApi {

    @GET("/v1/declaration/")
    Flowable<PersonsEntity> fetchPersons(@Query("q") String personName);
//    Flowable<List<PersonEntity>> fetchPersons(@Query("q") String personName);
}
