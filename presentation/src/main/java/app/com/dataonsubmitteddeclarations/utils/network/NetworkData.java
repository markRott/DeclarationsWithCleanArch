package app.com.dataonsubmitteddeclarations.utils.network;

public final class NetworkData {

    private final boolean hasInternetConnection;
    private final boolean isMobileConnection;

    public NetworkData(
            final boolean hasInternetConnection,
            final boolean isMobileConnection) {

        this.hasInternetConnection = hasInternetConnection;
        this.isMobileConnection = isMobileConnection;
    }

    public boolean isHasInternetConnection() {
        return hasInternetConnection;
    }

    public boolean isMobileConnection() {
        return isMobileConnection;
    }
}
