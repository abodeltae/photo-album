package com.nazeer.gallery.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nazeer.gallery.Api.models.Flower;
import com.nazeer.gallery.R;
import com.nazeer.gallery.Util.App;

import java.util.List;

/**
 * Created by nazeer on 16/06/16.
 */
public class FlowersAdapter extends RecyclerView.Adapter<FlowersAdapter.MViewHolder> {

    private final Context context;
    private final List<Flower> items;

    public FlowersAdapter(Context context, List<Flower>items){
        this.context=context;
        this.items=items;
    }
    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.gallery_item,null);

        return new MViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FlowersAdapter.MViewHolder holder, int position) {
        App.imageLoader.displayImage(items.get(position).getPhoto(),holder.flowerIv);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    static class MViewHolder extends RecyclerView.ViewHolder{
        ImageView flowerIv;

        public MViewHolder(View itemView) {
            super(itemView);
            flowerIv= (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

}
