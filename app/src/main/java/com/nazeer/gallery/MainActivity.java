package com.nazeer.gallery;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nazeer.gallery.Api.ApiCallback;
import com.nazeer.gallery.Api.ApiClient;
import com.nazeer.gallery.Api.models.Flower;
import com.nazeer.gallery.Util.App;
import com.nazeer.gallery.Util.Utils;
import com.nazeer.gallery.adapters.FlowersAdapter;
import com.nazeer.gallery.customViews.MGridLayoutManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Flower>flowerList;
    private MGridLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        fetchData();

    }

    private void initViews() {
        recyclerView=(RecyclerView)findViewById(R.id.galleryRV);
    }

    private void fetchData() {
        App.apiClient.getFlowers(new ApiCallback<List<Flower>>() {
            @Override
            public void onSuccess(List<Flower> response) {
                flowerList=response;
                fillRecyclerView(response);
            }

            @Override
            public void onException(Exception e) {

            }
        });
    }

    private void fillRecyclerView(List<Flower> list) {
        FlowersAdapter adapter=new FlowersAdapter(this,list);
        layoutManager=new MGridLayoutManager(this, Utils.dpToPixel(this,100));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        recyclerView.invalidate();
    }
}
