package com.example.autoparts;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ItemHolder extends RecyclerView.ViewHolder {

    public TextView itemNameTextView;
    public TextView itemVendorTextView;
    public TextView itemQtyTextView;
    public TextView itemPriceTextView;

    public ItemHolder(View itemView) {
        super(itemView);

        itemNameTextView = (TextView) itemView.findViewById(R.id.item_name_text_view);
        itemVendorTextView = (TextView) itemView.findViewById(R.id.item_vendor_text_view);
        itemQtyTextView = (TextView) itemView.findViewById(R.id.item_qty_edit_text);
        itemPriceTextView = (TextView)itemView.findViewById(R.id.item_price_text_view);
    }
}
