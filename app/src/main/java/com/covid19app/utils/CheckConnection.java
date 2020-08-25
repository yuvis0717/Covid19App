package com.covid19app.utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by sotsys-103 on 9/3/18.
 */

public class CheckConnection {
    public static boolean checkConnection(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected()
                || connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();
    }
}
