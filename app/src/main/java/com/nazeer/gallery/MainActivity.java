package com.nazeer.gallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nazeer.gallery.Api.ApiCallback;
import com.nazeer.gallery.Api.ApiClient;
import com.nazeer.gallery.Api.models.Flower;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApiClient client=new ApiClient();
        client.getFlowers(new ApiCallback<List<Flower>>(){
            @Override
            public void onSuccess(List<Flower> response) {
                response.size();
            }
        });
    }
}
