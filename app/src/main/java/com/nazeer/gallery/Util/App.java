package com.nazeer.gallery.Util;

import android.app.Application;

import com.nazeer.gallery.Api.ApiClient;

/**
 * Created by nazeer on 16/06/16.
 */
public class App extends Application {

    public static ApiClient apiClient;

    @Override
    public void onCreate() {
        super.onCreate();
        apiClient=new ApiClient();
    }
}
