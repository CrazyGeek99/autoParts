package com.example.autoparts;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import java.util.ArrayList;

public class ItemListActivity extends AppCompatActivity {


    Context context = ItemListActivity.this;

    private RecyclerView itemsRecycler;

    private ArrayList<Items> listItems;

    private SearchView iSearchView;

    private SearchManager manager;

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

        manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        //set the fixed size of the RecyclerView
        itemsRecycler.setHasFixedSize(true);
        //setting Linear as layout
        itemsRecycler.setLayoutManager(new LinearLayoutManager(this));
        itemAdapter = new ItemAdapter(listItems, this);

        generateObjects();
        getItemsFromSQLite();
    }
    private void generateObjects() {
        listItems = new ArrayList<>();
        itemAdapter = new ItemAdapter(listItems, this);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        itemsRecycler.setLayoutManager(layoutManager);

        itemsRecycler.setAdapter(itemAdapter);

        dbHelper = new PartsDbHelper(this);
    }

    private void getItemsFromSQLite() {
        //AsyncTask is used that sqlite operation not blocks the UI Thread
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                listItems.clear();
                listItems.addAll(dbHelper.getAllItems());

                return null;
            }

            protected void onPostExecute(Void params) {
                super.onPostExecute(params);
                itemAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

    //Building Menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        MenuItem item = menu.findItem(R.id.searchMenu);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                itemAdapter.getFilter().filter(newText);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
