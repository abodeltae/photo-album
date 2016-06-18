package com.nazeer.album.Api;

import com.nazeer.album.Api.models.Flower;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nazeer on 16/06/16.
 */
public class ApiClient {
    public static final String BASE_URL="http://services.hanselandpetal.com/";
    public static final String IMAGE_BASE_URL="http://services.hanselandpetal.com/photos/";
    private Services services;

    /*
     * Api client is a layer between the application code and the network library dependency
     */
    public ApiClient(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
         services = retrofit.create(Services.class);

    }

    public void getFlowers(ApiCallback<List<Flower>> callback){
        Call<List<Flower>> call = services.getFlowers();
        execute(call,callback);
    }

    private void execute(Call call , final ApiCallback callback){
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                callback.onException(new Exception(t));
            }
        });

    }
}
