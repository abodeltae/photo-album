package com.nazeer.album.Util;

import android.app.Application;

import com.nazeer.album.Api.ApiClient;
import com.nazeer.album.ImageLoader.MImageLoader;

/**
 * Created by nazeer on 16/06/16.
 */
public class App extends Application {

    public static ApiClient apiClient;
    public static MImageLoader imageLoader;

    @Override
    public void onCreate() {
        super.onCreate();
        apiClient=new ApiClient();
        imageLoader=new MImageLoader(this);
    }
}
