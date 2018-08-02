package app.com.dataonsubmitteddeclarations.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import app.com.dataonsubmitteddeclarations.utils.network.NetworkContract;
import app.com.dataonsubmitteddeclarations.utils.network.NetworkContractImpl;
import dagger.Module;
import dagger.Provides;

@Module
public class UtilsModule {

    @Provides
    @Singleton
    public NetworkContract provideNetworkContract(final Context context) {
        return new NetworkContractImpl(context);
    }

    public interface Expose {
        NetworkContract networkContract();
    }
}
