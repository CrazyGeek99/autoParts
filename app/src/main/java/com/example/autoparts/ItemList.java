package com.example.autoparts;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import static com.example.autoparts.PartsDbHelper.getAllItems;

public class ItemList extends Activity {

    //private Activity activity = ItemList.this;
    Context context = ItemList.this;
    //creating RecyclerView variable
    private RecyclerView itemsRecycler;
    private ArrayList<Items> listItems;

    //To customize the layout property
    private RecyclerView.LayoutManager itemLayoutManager;
    //calling the ItemAdapter
    private ItemAdapter itemAdapter;
    //calling the dbhelper class
    PartsDbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        //Recycler View for items to display
        itemsRecycler = (RecyclerView) findViewById(R.id.items_recycler_view);
        dbHelper = new PartsDbHelper(this);

        //set the fixed size of the RecyclerView
        itemsRecycler.setHasFixedSize(true);
        //setting Linear as layout
        itemLayoutManager = new LinearLayoutManager(this);
        itemsRecycler.setLayoutManager(itemLayoutManager);
        itemAdapter = new ItemAdapter(listItems, this);

        //ArrayList<Items> items = new ArrayList<Items>();
        //displayItems();
        
        generateObjects();
        getItemsFromSQLite();

    }

    private void generateObjects()
    {
        listItems = new ArrayList<>();
        itemAdapter = new ItemAdapter(listItems, this);
        //listItems = new ArrayList<>(listItems);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        itemsRecycler.setLayoutManager(layoutManager);
        //itemsRecycler.setLayoutManager(null);

        itemsRecycler.setAdapter(itemAdapter);
        dbHelper = new PartsDbHelper(this);
    }

    private void getItemsFromSQLite()
    {
        //AsyncTask is used that sqlite operation not blocks the UI Thread
        new AsyncTask<Void, Void, Void>()
        {
            @Override
            protected Void doInBackground(Void... voids) {
                listItems.clear();
                listItems.addAll(dbHelper.getAllItems());

                return null;
            }

            protected void onPostExecute(Void params){
                super.onPostExecute(params);
                itemAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

    /*
    public void displayItems()
    { ArrayList<Integer> listData = new ArrayList<>();
    Cursor itemCursor = dbHelper.fetchItems();
        if (itemCursor.getCount() == 0)
        {
            Toast.makeText(getApplicationContext(),
                    "NO ITEM FOUND", Toast.LENGTH_LONG).show();
        }else {
            while (itemCursor.moveToNext())
            {
                listData.add(itemCursor.getColumnIndex(PartsDbHelper.COLUMN_ITEM_NAME));
                listData.add(itemCursor.getColumnIndex(PartsDbHelper.COLUMN_ITEM_VENDOR));
                listData.add(itemCursor.getColumnIndex(PartsDbHelper.COLUMN_ITEM_QTY));
                listData.add(itemCursor.getColumnIndex(PartsDbHelper.COLUMN_ITEM_PRICE));

                //how to pass the column information here? with cursor....
            }
        }

        iAdapter = new ItemAdapter(this, listData);
        itemsRecycler.setAdapter(iAdapter);
        /*
        ArrayAdapter<Items> itemArray = new ArrayAdapter<Items>(this, R.layout.activity_item_list);
        itemsRecycler.setAdapter(itemAdapter);

        itemAdapter = new ItemAdapter(this, listData);
        itemsRecycler.setAdapter(itemAdapter);
    }
    */
}
