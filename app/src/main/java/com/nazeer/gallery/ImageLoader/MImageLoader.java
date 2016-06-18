package com.nazeer.gallery.ImageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

import com.nazeer.gallery.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import okhttp3.internal.Util;

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
                .displayer(new RoundedBitmapDisplayer((int) dpToPixel(context, 4)))
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
    public void displayImage(String url, final ImageView imageView){
        imageLoader.loadImage(url, options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                imageView.setImageResource(R.drawable.loading_img);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                imageView.setImageResource(R.drawable.loading_img);

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                int minWidth=dpToPixel(context,100);
                Bitmap scaled = Bitmap.createScaledBitmap(loadedImage, Math.max(minWidth,imageView.getWidth()), Math.max(minWidth,imageView.getHeight()),false);
                imageView.setImageBitmap(scaled);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                imageView.setImageResource(R.drawable.loading_img);
            }
        });

        imageLoader.displayImage(url,imageView,options);
    }
    public void displayRoundCornerImage(String url,ImageView imageView){
        imageLoader.displayImage(url,imageView,optionsRoundCornered);
    }
    private int dpToPixel(Context context, int value) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int)(value * density);
    }


}
