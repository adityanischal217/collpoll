package com.aditya.collpolltest.Utils;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class InternetConnection {
    private static NetworkInfo getActiveNetworkInfo(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            return cm.getActiveNetworkInfo();
        }
        return null;
    }

    public static boolean isConnected(Context context) {
        NetworkInfo info = getActiveNetworkInfo(context);
        return info != null && info.isConnected();
    }



    public static void noInternetError(View view) {
        Snackbar.make(view, "Please check the internet connection",
                Snackbar.LENGTH_LONG).show();
    }
}
