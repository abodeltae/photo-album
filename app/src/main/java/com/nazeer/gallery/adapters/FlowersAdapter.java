package com.nazeer.gallery.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by nazeer on 16/06/16.
 */
public class FlowersAdapter extends RecyclerView.Adapter<FlowersAdapter.MViewHolder> {
    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(FlowersAdapter.MViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    static class MViewHolder extends RecyclerView.ViewHolder{
        ImageView flowerIv;

        public MViewHolder(View itemView) {
            super(itemView);
        }
    }

}
