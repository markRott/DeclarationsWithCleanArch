package app.com.dataonsubmitteddeclarations.di.modules;

import javax.inject.Singleton;

import app.com.data.network.ApplicationApi;
import app.com.data.repositories.PersonsRepositoryImpl;
import app.com.domain.interactors.PersonsInteractor;
import app.com.domain.interfaces.PersonsRepository;
import app.com.domain.interfaces.ThreadContract;
import dagger.Module;
import dagger.Provides;

@Module
public class PersonsModule {

    @Provides
    @Singleton
    public PersonsRepository providePersonRepository(final ApplicationApi applicationApi) {
        return new PersonsRepositoryImpl(applicationApi);
    }

    @Provides
    @Singleton
    public PersonsInteractor providePersonsInteractor(
            final ThreadContract threadContract,
            final PersonsRepository repository) {
        return new PersonsInteractor(threadContract, repository);
    }

    public interface Expose {

        PersonsRepository personRepository();

        PersonsInteractor personsInteractor();
    }
}
