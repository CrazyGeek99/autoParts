package com.example.autoparts;

import android.app.Activity;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.Toast;

public class ItemList extends Activity {

    //creating RecyclerView variable
    private RecyclerView itemRecyclerView;
    //
    private RecyclerView.Adapter iAdapter;
    //
    private RecyclerView.LayoutManager iLayoutManager;


    //calling the dbhelper class
    PartsDbHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        dbHelper = new PartsDbHelper(this);

        //Recycler View for items to display
        itemRecyclerView = (RecyclerView) findViewById(R.id.items_recycler_view);

        //set the fixed size of the RecyclerView
        itemRecyclerView.setHasFixedSize(true);

        //Linear setting as layout
        iLayoutManager = new LinearLayoutManager(this);
        itemRecyclerView.setLayoutManager(iLayoutManager);

    }

}
