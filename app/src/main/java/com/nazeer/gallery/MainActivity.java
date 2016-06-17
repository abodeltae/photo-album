package com.nazeer.gallery;

import android.animation.ValueAnimator;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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
    private RelativeLayout galleryDetailContainer;
    private ImageView galleryDetailIv;
    private RelativeLayout mainContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        fetchData();

    }

    private void initViews() {
        mainContainer=(RelativeLayout)findViewById(R.id.mainContaier);
        recyclerView=(RecyclerView)findViewById(R.id.galleryRV);
        galleryDetailContainer = (RelativeLayout) findViewById(R.id.galleryDetailContainer);
        galleryDetailIv=(ImageView)findViewById(R.id.galleryDetailIv);
        galleryDetailContainer.setVisibility(View.INVISIBLE);

    }

    private void fetchData() {
        App.apiClient.getFlowers(new ApiCallback<List<Flower>>() {
            @Override
            public void onSuccess(List<Flower> response) {
                flowerList=response;
                fillRecyclerView(flowerList);
            }

            @Override
            public void onException(Exception e) {

            }
        });
    }

    private void fillRecyclerView(List<Flower> list) {
        final FlowersAdapter adapter=new FlowersAdapter(this,list);
        adapter.setOnItemClickListner(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                App.imageLoader.displayImage(adapter.getItem(i).getPhoto(),galleryDetailIv);
                flipAnimate(true);
            }
        });
        layoutManager=new MGridLayoutManager(this, Utils.dpToPixel(this,100));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        recyclerView.invalidate();
    }

     void flipAnimate(final boolean showDetail){
         int start,end;
         if(showDetail){
             start=0;end=180;
         }
         else{
             start=180;end=0;
         }
        ValueAnimator animator=ValueAnimator.ofInt(start,end);
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int animatedValue=(int)valueAnimator.getAnimatedValue();
                mainContainer.setRotationY(animatedValue);
                if((showDetail&&animatedValue>=90)||(!showDetail&&animatedValue<=90)){
                    if(showDetail){
                        recyclerView.setVisibility(View.INVISIBLE);
                        galleryDetailContainer.setVisibility(View.VISIBLE);
                    }
                    else {
                        recyclerView.setVisibility(View.VISIBLE);
                        galleryDetailContainer.setVisibility(View.INVISIBLE);
                    }
                }

            }
        });

        animator.start();
    }

    @Override
    public void onBackPressed() {
        if(galleryDetailContainer.getVisibility()==View.VISIBLE){
            flipAnimate(false);
        }
        else{
            super.onBackPressed();
        }
    }
}
