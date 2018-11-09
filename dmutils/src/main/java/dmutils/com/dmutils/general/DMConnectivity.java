package dmutils.com.dmutils.general;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class DMConnectivity {

    /**
     * add <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" /> on manifest
     * @param context
     * @return
     */

    @SuppressLint("MissingPermission")
    public static boolean isConnectedToInternet(final Context context) {
        final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork;
        if (cm != null) {
            activeNetwork = cm.getActiveNetworkInfo();

            if (activeNetwork != null) {
                // connected to the internet
                switch (activeNetwork.getType()) {
                    case ConnectivityManager.TYPE_WIFI:
                        return true;
                    case ConnectivityManager.TYPE_MOBILE:
                        return true;
                    default:
                        break;
                }
            }
        }
        return false;
    }

    @SuppressLint("MissingPermission")
    public static int getConnectionType(final Context context) {
        final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork;
        if (cm != null) {
            activeNetwork = cm.getActiveNetworkInfo();

            if (activeNetwork != null) {
                return activeNetwork.getType();
            }
        }
        return -1;
    }

    public static boolean isLocationEnabled(final Context context) {
        final LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (lm != null) {
            try {
                if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER) || lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                    return true;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return false;
    }
}
