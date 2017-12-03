package com.example.david.david_pset5;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RestoDatabase extends SQLiteOpenHelper {

    private static RestoDatabase instance = null;
    // makes the column names
    private static final String TAG ="RestoDatabase";
    private static final String TABLE_NAME ="orders";
    private static final String COL1 ="_id";
    private static final String COL2 ="name";
    private static final String COL3 ="price";
    private static final String COL4 ="amount";

    private RestoDatabase(Context context) {
        super(context, TABLE_NAME, null, 1);

    }
    public static RestoDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new RestoDatabase(context);
        }
        return instance;
    }
    // create the table on create
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + " TEXT, " + COL3 + " FLOAT," + COL4 + " INTEGER);" ;
        db.execSQL(createTable);

    }
    // make the on upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    // check if there is a tablerow with name
    public  boolean checkExistece(String name )   {
        SQLiteDatabase db = this.getWritableDatabase();
        String Query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL2 + " = '" + name+ "';";
        Cursor cursor = db.rawQuery(Query, null);

        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    // update the table
    public void addItem( String name, float price) {
        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println("hier komt hij ook");
        int amount = 1;
        String query;
        if (checkExistece(name)){
            query ="UPDATE " + TABLE_NAME + " SET " + COL4 +  " = " + COL4 + " + 1 WHERE " + COL2 + " = '" + name + "';";
        }
        else {
            query = "INSERT INTO " + TABLE_NAME + "(" + COL2 + ", " + COL3 + ", " + COL4 + ") VALUES( '" + name + "', " + price + ", " + amount + ");";
        }
        System.out.println(query);
        db.execSQL(query);
    }


    // get the whole table
    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String all = "SELECT * FROM " + TABLE_NAME;
        Cursor entries = db.rawQuery(all, null);
        return entries;
    }

    // delete all from one item
    public void deleteItem(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COL1 + " = '" +  id + "';";
        System.out.println(query);
        db.execSQL(query);
    }



    // delete the whole table
    public void clear()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query ="DELETE FROM " + TABLE_NAME +";";
        db.execSQL(query);
    }
    // calculate the total price
    public String totalPrice() {
        int priceCol = 2;
        int amountCol = 3;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        float total = 0;
        while (cursor.moveToNext()) {
            total = total + cursor.getFloat(priceCol) * cursor.getFloat(amountCol);
        }
        return "€ " + total;
    }
}