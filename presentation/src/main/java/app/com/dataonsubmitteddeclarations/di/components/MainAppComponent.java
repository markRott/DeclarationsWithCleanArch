package app.com.dataonsubmitteddeclarations.di.components;

import javax.inject.Singleton;

import app.com.dataonsubmitteddeclarations.MainActivity;
import app.com.dataonsubmitteddeclarations.base.BaseFragment;
import app.com.dataonsubmitteddeclarations.di.modules.CompositeDisposableModule;
import app.com.dataonsubmitteddeclarations.di.modules.ContextModule;
import app.com.dataonsubmitteddeclarations.di.modules.NetworkModule;
import app.com.dataonsubmitteddeclarations.di.modules.PersonsModule;
import app.com.dataonsubmitteddeclarations.di.modules.RouterModule;
import app.com.dataonsubmitteddeclarations.di.modules.ThreadModule;
import app.com.dataonsubmitteddeclarations.di.modules.UtilsModule;
import app.com.dataonsubmitteddeclarations.favorite.FavoriteSearchPresenter;
import app.com.dataonsubmitteddeclarations.search.SearchPresenter;
import dagger.Component;

@Singleton
@Component(modules = {
        ContextModule.class,
        ThreadModule.class,
        UtilsModule.class,
        NetworkModule.class,
        PersonsModule.class,
        CompositeDisposableModule.class,
        RouterModule.class})
public interface MainAppComponent {

    void inject(MainActivity mainActivity);

    void inject(BaseFragment baseFragment);

    void inject(SearchPresenter searchPresenter);

    void inject(FavoriteSearchPresenter favoriteSearchPresenter);
}
