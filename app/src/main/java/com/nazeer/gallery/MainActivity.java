package com.nazeer.gallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nazeer.gallery.Api.ApiCallback;
import com.nazeer.gallery.Api.ApiClient;
import com.nazeer.gallery.Api.models.Flower;
import com.nazeer.gallery.Util.App;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        fetchData();
    }

    private void initViews() {

    }

    private void fetchData() {

    }
}
