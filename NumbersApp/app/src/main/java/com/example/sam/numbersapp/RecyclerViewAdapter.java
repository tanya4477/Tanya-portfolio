package com.example.sam.numbersapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

/**
 * This is the RecyclerView Adapter Class
 * Created by tanya on 03.06.16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<View_Holder> {

    private List<MasterData> list = Collections.emptyList();
    private Context context;

    public RecyclerViewAdapter(List<MasterData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        View_Holder holder = new CustomViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(View_Holder holder, int position) {

        Picasso.with(context)
                .load(list.get(position).image)
                .placeholder(R.drawable.placeholder) // optional
                .error(R.drawable.icerror)         // optional
                .into(holder.imageView);
        holder.name.setText(list.get(position).name);

    }

    @Override
    public int getItemCount() {
        int itemCount = 0;
        //returns the number of elements the RecyclerView will display
        if(list != null){
            itemCount = list.size();
        }
        return itemCount;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, MasterData data) {
        list.add(position, data);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(MasterData data) {
        int position = list.indexOf(data);
        list.remove(position);
        notifyItemRemoved(position);
    }
}
