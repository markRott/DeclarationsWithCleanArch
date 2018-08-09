package app.com.dataonsubmitteddeclarations.di.modules;

import android.support.annotation.NonNull;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import app.com.data.network.ApplicationApi;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    public ApplicationApi provideApplicationApi() {
        final Retrofit retrofit = initRetrofit();
        return retrofit.create(ApplicationApi.class);
    }

    private Retrofit initRetrofit() {
        final Retrofit.Builder retrofitBuilder =
                new Retrofit.Builder()
                        .baseUrl("https://public-api.nazk.gov.ua")
                        .addConverterFactory(initConverterFactory())
                        .addCallAdapterFactory(initCallAdapterFactory())
                        .client(initOkHttpClient());

        return retrofitBuilder.build();
    }

    @NonNull
    private Converter.Factory initConverterFactory() {
        return GsonConverterFactory.create();
    }

    private CallAdapter.Factory initCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @NonNull
    private OkHttpClient initOkHttpClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        return builder.build();
    }
}
