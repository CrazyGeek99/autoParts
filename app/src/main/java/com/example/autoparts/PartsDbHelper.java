package com.example.autoparts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PartsDbHelper extends SQLiteOpenHelper {


    Context iContext;

    /**
     * Creating Database constants
     * @param context
     */
    //database name
    public static final String DATABASE_NAME = "autoPart.db";

    //database version
    public static final int DATABASE_VERSION = 1;

    //Table name for items
    public static final String TABLE_NAME = "items";

    //Column name for the table
    public static final String ID = "_id";
    public static final String COLUMN_ITEM_NAME = "item_name";
    public static final String COLUMN_ITEM_VENDOR = "items_vendor";
    public static final String COLUMN_ITEM_QTY = "items_qty";
    public static final String COLUMN_ITEM_PRICE = "item_price";

    //creating table constants
    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + ID + " INTEGER PRIMARY KEY,"
            + COLUMN_ITEM_NAME + " TEXT,"
            + COLUMN_ITEM_VENDOR + " TEXT,"
            + COLUMN_ITEM_QTY + " INTEGER,"
            + COLUMN_ITEM_PRICE + " INTEGER" + ")";

    /**
     * upgrading database
     */
    //public static final String SQL_UPGRADE_TABLE = "DROP IF EXITS "+TABLE_NAME;


    //SQL fetching all the items from the item table
    public static final String getAllItems = "SELECT * FROM " + TABLE_NAME;

    //passing database name and version database number
    public PartsDbHelper(Context context) {
        //creating database with version 1
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        iContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creating database table
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //if any upgrades come is the database, will call some sqlite command here
    }

    /**
     * Inserting items to table db method
     */
    public boolean insertItems(String iName, String iVendor, String iQty, String iPrice)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(COLUMN_ITEM_NAME, iName);
        cValues.put(COLUMN_ITEM_VENDOR, iVendor);
        cValues.put(COLUMN_ITEM_QTY, iQty);
        cValues.put(COLUMN_ITEM_PRICE, iPrice);
        long iResult = db.insert(TABLE_NAME, null, cValues);
        db.close();
        if (iResult == -1)
            return false;
        else
            return true;
    }

    /**
     * Fetching all the data from table db method
     * @return Cursor
     */
    public Cursor fetchItems()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor iCursor = db.query(getAllItems,
                null,
                null,
                null,
                null,
                null,
                null);
        db.close();
        return iCursor;
    }

    /**
     * Update item db method
     * @param id
     * @param name
     * @param vendor
     * @param qty
     * @param price
     * @return
     */
    public boolean updateItem(String id, String name, String vendor, String qty, String price)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(ID, id);
        cValues.put(COLUMN_ITEM_NAME, name);
        cValues.put(COLUMN_ITEM_VENDOR,vendor);
        cValues.put(COLUMN_ITEM_QTY, qty);
        cValues.put(COLUMN_ITEM_PRICE, price);
        db.update(TABLE_NAME, cValues, "ID = ?", new String[]{id});
        db.close();
        return true;
    }


    /**
     * deleting item from table db method
     * @param id
     * @return
     */
    public int deleteItem(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int deletedItem = db.delete(TABLE_NAME, "ID = ?", new String[]{id});
        db.close();
        return deletedItem;
    }

}
