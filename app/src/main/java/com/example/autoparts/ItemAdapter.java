package com.example.autoparts;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemHolder> implements Filterable {

    private Context iContext;
    private List<Items> itemsList = new ArrayList<>();
    private List<Items> itemsListFilter = new ArrayList<>();
    //private final View.OnClickListener mOnClickListener = new MyOnClickListener();

    //List<Items> itemsList;



    public ItemAdapter(List<Items> itemsList, Context context) {
        this.itemsList = itemsList;
        this.itemsListFilter = itemsList;
        iContext = context;
    }

    //Initiating viewHolder
    @Override
    public ItemHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view, null);

        //Holder
        ItemHolder iHolder = new ItemHolder(v);

        return iHolder;
    }

    @Override
    public void onBindViewHolder(ItemHolder itemHolder, int i) {
        Items item = itemsList.get(i);

        itemHolder.itemNameTextView.setText(item.getItem());
        itemHolder.itemVendorTextView.setText(item.getVendor());
        itemHolder.itemQtyTextView.setText(Integer.toString(item.getQty()));
        itemHolder.itemPriceTextView.setText(Integer.toString(item.getPrice()));
    }

    @Override
    public int getItemCount() {
        if (itemsList != null)
            return itemsList.size();
        else
            return 0;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<Items> filteredList = new ArrayList<>();
                String searchText = constraint.toString().toLowerCase();
                String[]split = searchText.split(",");
                ArrayList<String> searchGenres = new ArrayList<>(split.length);
                for (String aSplit : split){
                    //remove spaces
                    String trim = aSplit.trim();
                    //skip empty entries
                    if (trim.length() > 0)
                        searchGenres.add(trim);
                }
                for (Items dataName : itemsListFilter){
                    //filter by title
                    if (dataName.getItem().toLowerCase().trim().contains(searchText)){
                        filteredList.add(dataName);
                    }
                }
                results.count = filteredList.size();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                itemsList = (List<Items>)results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }


}

