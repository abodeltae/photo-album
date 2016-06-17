package com.nazeer.gallery.customViews;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.TypedValue;

/**
 * Created by nazeer on 6/17/16.
 */
public class MGridLayoutManager extends GridLayoutManager{

    private int itemWidth;
    private boolean mColumnWidthChanged;

    public MGridLayoutManager(Context context, int itemWidth) {
        super(context, 1);
        this.itemWidth=itemWidth;

    }




    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {

            int totalSpace;
            if (getOrientation() == VERTICAL) {
                totalSpace = getWidth() - getPaddingRight() - getPaddingLeft();
            } else {
                totalSpace = getHeight() - getPaddingTop() - getPaddingBottom();
            }
            int spanCount = Math.max(1, totalSpace / itemWidth);
            setSpanCount(spanCount);
            mColumnWidthChanged = false;

        super.onLayoutChildren(recycler, state);
    }

}
