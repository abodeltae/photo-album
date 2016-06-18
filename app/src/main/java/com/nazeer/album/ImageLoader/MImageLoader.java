package com.nazeer.album.ImageLoader;

import android.content.Context;
import android.widget.ImageView;

import com.nazeer.album.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Created by nazeer on 6/17/16.
 */
public class MImageLoader {

    public static ImageLoader imageLoader;
    private final DisplayImageOptions optionsRoundCornered;
    private final DisplayImageOptions options;
    private final Context context;

    public MImageLoader(Context context){
        this.context=context;
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .build();
        imageLoader=ImageLoader.getInstance();
        imageLoader.init(config);
        optionsRoundCornered = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.loading_img)
                .showImageOnFail(R.drawable.loading_img)
                .showImageForEmptyUri(R.drawable.loading_img)
                .displayer(new RoundedBitmapDisplayer(dpToPixel(context, 4)))
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .showImageOnLoading(R.drawable.loading_img)
                .showImageOnFail(R.drawable.loading_img)
                .showImageForEmptyUri(R.drawable.loading_img)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();

    }

    public void displayRoundCornerImage(String url, final ImageView imageView){

        imageLoader.displayImage(url,imageView,optionsRoundCornered);
    }
    private int dpToPixel(Context context, int value) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int)(value * density);
    }


}
