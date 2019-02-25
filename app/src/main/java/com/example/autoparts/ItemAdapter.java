package com.example.autoparts;

import android.content.ClipData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {

    Context iContext;
    PartsDbHelper itemDbHelper;
    ArrayList<Items> items;


    //how do you construct this class
    public ItemAdapter (Context c, ArrayList<Items> items)
    {
        this.iContext = c;
        this.items = items;
    }

    //Initiating viewHolder
    @Override
    public ItemHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view,null);

        //Holder
        ItemHolder iHolder = new ItemHolder(v);

        return iHolder;
    }

    @Override
    public void onBindViewHolder(ItemHolder itemHolder, int i)
    {
        itemHolder.itemNameTextView.setText(items.get(i).getItem());
        itemHolder.itemVendorTextView.setText(items.get(i).getVendor());
        itemHolder.itemQtyTextView.setText(items.get(i).getQty());
        itemHolder.itemPriceTextView.setText(items.get(i).getPrice());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
