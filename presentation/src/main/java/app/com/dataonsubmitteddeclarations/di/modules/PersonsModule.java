package app.com.dataonsubmitteddeclarations.di.modules;

import javax.inject.Named;
import javax.inject.Singleton;

import app.com.data.network.ApplicationApi;
import app.com.data.repositories.FavoriteRepositoryImpl;
import app.com.data.repositories.FetchPersonsFromDatabase;
import app.com.data.repositories.FetchPersonsFromNetwork;
import app.com.domain.interactors.FavoriteInteractor;
import app.com.domain.interactors.FetchPersonsContract;
import app.com.domain.interactors.FetchPersonsInteractor;
import app.com.domain.interfaces.FavoriteRepository;
import app.com.domain.interfaces.FetchPersonsRepository;
import app.com.domain.interfaces.ThreadContract;
import dagger.Module;
import dagger.Provides;

@Module
public class PersonsModule {

    @Provides
    @Singleton
    @Named("search")
    public FetchPersonsRepository provideSearchPersonRepository(final ApplicationApi applicationApi) {
        return new FetchPersonsFromNetwork(applicationApi);
    }

    @Provides
    @Singleton
    @Named("search")
    public FetchPersonsContract provideSearchPersonsInteractor(
            final ThreadContract threadContract,
            @Named("search") final FetchPersonsRepository repository) {
        return new FetchPersonsInteractor(threadContract, repository);
    }

    @Provides
    @Singleton
    @Named("favorite")
    public FetchPersonsRepository provideSearchFavoritePersonRepository() {
        return new FetchPersonsFromDatabase();
    }

    @Provides
    @Singleton
    @Named("favorite")
    public FetchPersonsContract provideFavoritePersonsInteractor(
            final ThreadContract threadContract,
            @Named("favorite") final FetchPersonsRepository repository) {

        return new FetchPersonsInteractor(threadContract, repository);
    }

    @Provides
    @Singleton
    FavoriteRepository provideFavoriteRepository() {
        return new FavoriteRepositoryImpl();
    }

    @Provides
    @Singleton
    public FavoriteInteractor provideFavoriteInteractor(
            final ThreadContract threadContract,
            final FavoriteRepository favoriteRepository) {

        return new FavoriteInteractor(threadContract, favoriteRepository);
    }
}
