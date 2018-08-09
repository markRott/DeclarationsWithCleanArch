package app.com.dataonsubmitteddeclarations.utils.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkContractImpl implements NetworkContract {

    private final ConnectivityManager connectivityManager;

    public NetworkContractImpl(final Context context) {
        this.connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Override
    public boolean isConnectedToNetwork() {
        final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public NetworkData getNetworkData() {
        final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        final boolean hasInternetConnection =
                (activeNetworkInfo != null && activeNetworkInfo.isConnected());
        final boolean isMobileConnection = (activeNetworkInfo != null
                && activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE);
        return new NetworkData(hasInternetConnection, isMobileConnection);
    }
}
