package com.nazeer.album.Util;

import android.content.Context;

/**
 * Created by nazeer on 6/17/16.
 */
public class Utils {

    public static int dpToPixel(Context context, int value) {
        float density = context.getResources().getDisplayMetrics().density;
         return (int) (value * density);
    }
}
