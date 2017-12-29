package com.automatedtellermachine.android.atmproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteCursor;
import android.content.Context;
import android.content.ContentValues;
import android.os.Build;


/**
 * Created by matthewkim on 12/21/17.
 */

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "balance.db";
    public static final String TABLE_BALANCE = "balance";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_BALANCE ="balanceName";


    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =  "CREATE TABLE" + TABLE_BALANCE + "(" + COLUMN_ID + "INTEGAR PRIMARY KEY AUTOINCREMENT "
                + COLUMN_BALANCE + "TEXT" + ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BALANCE);
        onCreate(db);

    }
    //Add a new row to the database
    public void addBalance(AccountATM balance){
        ContentValues values = new ContentValues();
        values.put(COLUMN_BALANCE, balance.get_accountname());
        SQLiteDatabase db = getReadableDatabase();
        db.insert(TABLE_BALANCE, null, values);
        db.close();
    }

    //Delete a product from the database
    public void deleteBalance(String Balance){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " +TABLE_BALANCE + " WHERE " + COLUMN_BALANCE + "=\" + Balance + "\;");
    }
    //print out the database as a string
    public String databaseToString(){
        String dbString = " ";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT + FROM " + TABLE_BALANCE + "WHERE 1";

        //cursor point to a location is your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

             if (c.getString(c.getColumnIndex("balance"))! = null){
                dbString += c.getString(c.getColumnIndex("balance"));
                dbString += "\n";


        }
    }
}
