package com.project.bongoagro.helper;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.project.bongoagro.R;

public class CheckInternetConnection {
    private Context context;

    // Constructor to initialize context
    public CheckInternetConnection(Context context) {
        this.context = context;
    }

    public void checkInternetConnection() {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (manager != null) {
            NetworkInfo activeNetwork = manager.getActiveNetworkInfo();

            if (activeNetwork != null) {
                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                    // Wifi enabled
                } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                    // Mobile data enabled
                }
            } else {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setIcon(R.drawable.ic_warning);
                dialog.setTitle("No Internet Connection");
                dialog.setMessage("It looks like your internet connection is off. Please turn it on and try again.");
                dialog.setPositiveButton("Ok", null);
                dialog.show();
            }
        }
    }
}

