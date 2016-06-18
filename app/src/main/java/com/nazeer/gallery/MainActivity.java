package com.nazeer.gallery;

import android.animation.ValueAnimator;
import android.content.res.Configuration;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.nazeer.gallery.callbacks.OnItemClick;
import com.nazeer.gallery.customViews.MGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView recyclerView;
    private List<Flower>flowerList;
    private MGridLayoutManager layoutManager;
    private RelativeLayout galleryDetailContainer;
    private ImageView galleryDetailIv;
    private RelativeLayout mainContainer;
    private ArrayList<Flower> viewedList;
    private FlowersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setupToolbar();
        fetchData();

    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

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
                viewedList=new ArrayList<Flower>();
                viewedList.addAll(flowerList);
                fillRecyclerView(viewedList);
            }

            @Override
            public void onException(Exception e) {

            }
        });
    }

    private void fillRecyclerView(final List<Flower> list) {
         adapter=new FlowersAdapter(this,list);
        adapter.setOnItemClickListner(new OnItemClick() {
            @Override
            public void onClick(int position) {
                App.imageLoader.displayImage(adapter.getItem(position).getPhoto(),galleryDetailIv);
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



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(item,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        // Do something when collapsed

                        return true; // Return true to collapse action view
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        // Do something when expanded
                        return true; // Return true to expand action view
                    }
                });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(galleryDetailContainer.getVisibility()==View.VISIBLE){
            mainContainer.setRotationY(0);
            galleryDetailContainer.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        ArrayList<Integer>removeSequence=new ArrayList<>();
        ArrayList<Flower>addList=new ArrayList<>();
        FlowerSearchHelper.generateSearchAddAndDeleteList(flowerList,viewedList,addList,removeSequence,newText);
        for(int x:removeSequence){

            adapter.notifyItemRemoved(x);
            viewedList.remove(x);

        }
        for(Flower f: addList){
            viewedList.add(f);
            adapter.notifyItemInserted(viewedList.size()-1);
        }
        return true;
    }
}
