package app.com.dataonsubmitteddeclarations.di.modules;

import javax.inject.Singleton;

import app.com.data.network.ApplicationApi;
import app.com.data.repositories.PersonRepositoryImpl;
import app.com.domain.interactors.PersonsInteractor;
import app.com.domain.interfaces.PersonRepository;
import app.com.domain.interfaces.ThreadContract;
import dagger.Module;
import dagger.Provides;

@Module
public class PersonsModule {

    @Provides
    @Singleton
    public PersonRepository providePersonRepository(final ApplicationApi applicationApi) {
        return new PersonRepositoryImpl(applicationApi);
    }

    @Provides
    @Singleton
    public PersonsInteractor providePersonsInteractor(
            final ThreadContract threadContract,
            final PersonRepository repository) {
        return new PersonsInteractor(threadContract, repository);
    }

    public interface Expose {

        PersonRepository personRepository();

        PersonsInteractor personsInteractor();
    }
}
