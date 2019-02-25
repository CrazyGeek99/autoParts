package com.example.autoparts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //creating an object of db helper class
    PartsDbHelper mDbHelper;

    //EditText variables
    EditText mItemID, mItemName, mItemVendor, mItemQty, mItemPrice;
    //Button Variables
    Button mSaveButton, mDisplayButton, mUpdateButton, mDeleteButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialising the db helper object
        mDbHelper = new PartsDbHelper(this);

        //Find the all xml views that will be needed to get input from users about Items
        mItemID = (EditText)findViewById(R.id.item_update_id);
        mItemName = (EditText)findViewById(R.id.item_name_edit_text);
        mItemVendor = (EditText)findViewById(R.id.item_vendor_edit_text);
        mItemQty = (EditText)findViewById(R.id.item_qty_edit_text);
        mItemPrice = (EditText)findViewById(R.id.item_price_edit_text);

        //Find all the xml Buttons that will be used to work with user input
        mSaveButton = (Button)findViewById(R.id.save_button);
        mDisplayButton = (Button)findViewById(R.id.display_button);
        mUpdateButton = (Button)findViewById(R.id.update_button);
        mDeleteButton = (Button)findViewById(R.id.delete_button);

        //calling the methods to really execute the press
        saveItem();
        mItemDelete();
        mItemUpdate();
        displayItemsListView();
    }

   /**
    private View.OnClickListener mDisplayItemClickListener = new View.OnClickListener()
    {

        @Override
        public void onClick(View v) {
            Intent mIntent = new Intent(MainActivity.this, ItemList.class);
            startActivity(mIntent);

        }
    };
    */

    /**
     * insert item
     */
    public void saveItem()
    {
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = mDbHelper.insertItems(
                                mItemName.getText().toString().trim(),
                                mItemVendor.getText().toString().trim(),
                                mItemQty.getText().toString().trim(),
                                mItemPrice.getText().toString().trim());
                if (isInserted == true)
                    Toast.makeText(getApplicationContext(),
                            "Item inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(),
                            "Item insertion failed", Toast.LENGTH_LONG).show();

                mItemName.setText("");
                mItemVendor.setText("");
                mItemQty.setText("");
                mItemPrice.setText("");
            }
        });
    }

    //display items
    public void displayItemsListView()
    {
        mDisplayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent displayIntent = new Intent(MainActivity.this,ItemList.class);
                startActivity(displayIntent);
            }
        });
    }

    //when update button pressed with the respective ID
    public void mItemUpdate()
    {
        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdated = mDbHelper.updateItem(
                        mItemID.getText().toString().trim(),
                        mItemName.getText().toString().trim(),
                        mItemVendor.getText().toString().trim(),
                        mItemQty.getText().toString().trim(),
                        mItemPrice.getText().toString().trim()
                );
                if (isUpdated == true)
                    Toast.makeText(getApplicationContext(),
                            "Item updated", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(),
                            "Error updating item!", Toast.LENGTH_LONG).show();

                mItemName.setText("");
                mItemVendor.setText("");
                mItemQty.setText("");
                mItemPrice.setText("");
            }
        });
    }

    //when delete button pressed with the respective ID
    public void mItemDelete()
    {
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer rowsDeleted = mDbHelper.deleteItem(
                        mItemID.getText().toString().trim());
                if (rowsDeleted >0)
                    Toast.makeText(getApplicationContext(),
                            "Item deleted!", Toast.LENGTH_LONG).show();
                else Toast.makeText(getApplicationContext(),
                        "Error deleting item!", Toast.LENGTH_LONG).show();
            }
        });
    }


}
