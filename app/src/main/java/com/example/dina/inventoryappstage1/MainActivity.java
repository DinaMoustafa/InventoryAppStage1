package com.example.dina.inventoryappstage1;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.dina.inventoryappstage1.data.ProductContract.ProductEntry;
import com.example.dina.inventoryappstage1.data.ProductDbHelper;
public class MainActivity extends AppCompatActivity {
    public final static String LOG_TAG = "Cursor Log";
    public ProductDbHelper mDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDbHelper = new ProductDbHelper(this);
        insertProduct();
        readFromDatabase();
    }


    public void insertProduct() {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ProductEntry.COLUMN_PRODUCT_NAME, "HeadPhones");
        values.put(ProductEntry.COLUMN_PRICE, 200);
        values.put(ProductEntry.COLUMN_QUANTITY, 30);
        values.put(ProductEntry.COLUMN_SUPPLIER_NAME, "Computer Shop");
        values.put(ProductEntry.COLUMN_SUPPLIER_PHONE_NUMBER, "01223456789");
        long newRowId = db.insert(ProductEntry.TABLE_NAME, null, values);
        if (newRowId == -1) {
            Toast.makeText(this, "Error Saving Product data ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Product saved with row Id " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }


    public void readFromDatabase() {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {
                ProductEntry._ID,
                ProductEntry.COLUMN_PRODUCT_NAME,
                ProductEntry.COLUMN_PRICE,
                ProductEntry.COLUMN_QUANTITY,
                ProductEntry.COLUMN_SUPPLIER_NAME};
        String selection = ProductEntry._ID + "=?";
        String[] selectionArgs = {"1"};
        Cursor cursor = db.query(ProductEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null);
        cursor.moveToNext();
        int productNameColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_NAME);
        String currentName = cursor.getString(productNameColumnIndex);
        Log.i(LOG_TAG, currentName);
        cursor.close();
    }
}
