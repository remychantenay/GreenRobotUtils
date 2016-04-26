package com.cremy.greenrobotutils.library.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;

/**
 * Created by remychantenay on 26/04/2016.
 */
public final class NetworkUtils {
    private final static String TAG = "NetworkUtils";


    public final static int NO_NETWORK = 0;
    public final static int WIFI = 1;
    public final static int NETWORK = 2;

    /**
     * Allows to check the network state
     * @param _context
     * @return	true is network is available, false otherwise
     */
    public static boolean isNetworkEnabled(Context _context)
    {
        try {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();

            return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Allows to get the current network type
     * Priority order :
     * 1. TYPE_WIFI
     * 2. TYPE_MOBILE
     * 3. -1 : No specific type found
     * @return ConnectivityManager.TYPE_WIFI | ConnectivityManager.TYPE_MOBILE | -1
     */
    private int getCurrentNetworkType(Context _context) {


        ConnectivityManager connManager = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (Build.VERSION.SDK_INT >= 21) {
            int bestConnectionType = -1;

            Network[] networks = connManager.getAllNetworks();
            final int nbNetworks = networks.length;

            for (int i = 0; i < nbNetworks; i++) {
                final NetworkInfo networkInfo = connManager.getNetworkInfo(networks[i]);
                if (networkInfo != null) {
                    if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                        if (networkInfo.isAvailable() && networkInfo.isConnected()) {
                            Log.d(TAG, "Network : WiFi is connected");

                            bestConnectionType = ConnectivityManager.TYPE_WIFI;
                        }
                    }
                    if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                        if (networkInfo.isAvailable() && networkInfo.isConnected()) {
                            Log.d(TAG, "Network : Mobile network (data) is connected");

                            if (bestConnectionType != ConnectivityManager.TYPE_WIFI) {
                                bestConnectionType = ConnectivityManager.TYPE_MOBILE;
                            }
                        }
                    }
                }
            }

            return bestConnectionType;

        }
        // Pre-Lollipop
        else {

            // Is WiFi connected ?
            NetworkInfo networkInfoWiFi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (networkInfoWiFi != null && networkInfoWiFi.isAvailable() && networkInfoWiFi.isConnected()) {
                Log.d(TAG, "Network : WiFi is connected");
                return ConnectivityManager.TYPE_WIFI;
            }

            // Is Mobile data connected ?
            NetworkInfo networkInfoMobileData = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (networkInfoMobileData != null && networkInfoMobileData.isAvailable() && networkInfoMobileData.isConnected()) {
                Log.d(TAG, "Network : Mobile network (data) is connected");
                return ConnectivityManager.TYPE_MOBILE;
            }

            Log.d(TAG, "Network : No network type found");
            return -1;
        }

    }
}
