package com.example.adilvirani.contactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

/**
 * Created by evelynwong on 11/16/17.
 */

public class ContactDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Contacts.db";
    public static final String TABLE_NAME = "contacts";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "FIRST_NAME";
    public static final String COL_3 = "LAST_NAME";
    public static final String COL_4 = "GROUP_NAME";
    public static final String COL_5 = "NUMBER";
    public static final String COL_6 = "BLACKLIST";

    public ContactDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "FIRST_NAME TEXT,LAST_NAME TEXT,GROUP_NAME TEXT,NUMBER INTEGER, BLACKLIST INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertContact(String firstName, String LastName, String group, String number, int blacklist) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, firstName);
        contentValues.put(COL_3, LastName);
        contentValues.put(COL_4, group);
        contentValues.put(COL_5, number);
        contentValues.put(COL_6, blacklist);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1 )
            return false;
        else
            return true;
    }

    public ArrayList<String> getAllContacts() {
        ArrayList<String> array_list = new ArrayList<String>();


        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            String FULL_NAME = res.getString(res.getColumnIndex(COL_2)) + " " + res.getString(res.getColumnIndex(COL_3));
            array_list.add(FULL_NAME);
            res.moveToNext();
        }
        return array_list;
    }

    public Cursor getData(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }

    public boolean updateContact (Integer id, String firstName, String lastName, String group, String number, int blacklist) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, firstName);
        contentValues.put(COL_3, lastName);
        contentValues.put(COL_4, group);
        contentValues.put(COL_5, number);
        contentValues.put(COL_6, blacklist);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

}
