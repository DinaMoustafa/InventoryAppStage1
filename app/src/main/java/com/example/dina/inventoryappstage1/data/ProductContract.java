package com.example.dina.inventoryappstage1.data;
import android.provider.BaseColumns;
public final class ProductContract {
    public final static class ProductEntry implements BaseColumns {
        public final static String TABLE_NAME = "Products";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_PRODUCT_NAME = "ProductName";
        public final static String COLUMN_PRICE = "Price";
        public final static String COLUMN_QUANTITY = "Quantity";
        public final static String COLUMN_SUPPLIER_NAME = "SupplierName";
        public final static String COLUMN_SUPPLIER_PHONE_NUMBER = "SupplierPhone";
    }
}
