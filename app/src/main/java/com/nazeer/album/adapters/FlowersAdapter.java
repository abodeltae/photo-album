package com.nazeer.album.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nazeer.album.Api.models.Flower;
import com.nazeer.album.R;
import com.nazeer.album.Util.App;
import com.nazeer.album.callbacks.OnItemClick;

import java.util.List;

/**
 * Created by nazeer on 16/06/16.
 */
public class FlowersAdapter extends RecyclerView.Adapter<FlowersAdapter.MViewHolder> {

    private final Context context;
    private final List<Flower> items;
    public OnItemClick itemClickListener;

    public FlowersAdapter(Context context, List<Flower>items){
        this.context=context;
        this.items=items;
    }
    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.gallery_item,null);
        final MViewHolder holder = new MViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemClickListener!=null){
                    //use this approach instead of saving the item position directly to support deleting items with notifyItemRemoved without mixing indexes
                    int index=items.indexOf(holder.flower);
                    // check to avoid multible clicks during animating delete
                    if(index!=-1)
                    itemClickListener.onClick(index);

                }
            }
        });
        return holder ;
    }

    @Override
    public void onBindViewHolder(FlowersAdapter.MViewHolder holder, int position) {
        App.imageLoader.displayRoundCornerImage(getItem(position).getPhoto(),holder.flowerIv);
        holder.flower=getItem(position);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }


    public Flower getItem(int position){
        return items.get(position);
    }

    static class MViewHolder extends RecyclerView.ViewHolder{
        ImageView flowerIv;
        Flower flower;
        View view;
        public MViewHolder(View itemView) {
            super(itemView);
            flowerIv= (ImageView) itemView.findViewById(R.id.imageView);
            view=itemView;
        }
    }

    public void setOnItemClickListner(OnItemClick listener){
        this.itemClickListener=listener;
    }


}
