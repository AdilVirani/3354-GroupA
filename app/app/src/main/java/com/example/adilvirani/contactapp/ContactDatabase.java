package com.example.adilvirani.contactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

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

    //dele contact from database
    public boolean destroyContact(Contact c) {
        SQLiteDatabase db = this.getWritableDatabase();

        int result = db.delete(TABLE_NAME, "id = ?", new String[] { Integer.toString(c.id()) });

        return (result == 1);
    }

    //Insert contacts into database
    public Contact insertContact(Contact c) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, c.getFirstName());
        contentValues.put(COL_3, c.getLastName());
        contentValues.put(COL_4, c.getGroupName());
        contentValues.put(COL_5, c.getPhoneNumber());
        if (c.getBlacklist())
            contentValues.put(COL_6, 1);
        else
            contentValues.put(COL_6, 0);

        long rid = db.insert(TABLE_NAME, null, contentValues);

        if (rid != -1) {
            c.setID((int) (rid));
            return c;
        }

        return null;
    }


    public void truncateContacts() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery("delete from contacts", null);
    }


    //All contacts into a Contact List
    public List<Contact> allContacts() {
        ArrayList<Contact> contactsList = new ArrayList<Contact>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while (!res.isAfterLast()) {
            String firstName = res.getString(res.getColumnIndex(COL_2));
            String lastName = res.getString(res.getColumnIndex(COL_3));
            String groupName = res.getString(res.getColumnIndex(COL_4));
            String phoneNumber = res.getString(res.getColumnIndex(COL_5));
            Boolean bl;
            if (res.getInt(res.getColumnIndex(COL_6)) == 0)
                bl = false;
            else
                bl = true;
            int cid = res.getInt(res.getColumnIndex(COL_1));

            Contact nc = new Contact(firstName, lastName, groupName, phoneNumber, bl);
            nc.setID(cid);

            contactsList.add(nc);

            res.moveToNext();
        }

        return contactsList;
    }

    //Get data through cursor
    public Cursor getData(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }

    //Update contact after edit
    public boolean updateContact(Contact c) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, c.firstName);
        contentValues.put(COL_3, c.lastName);
        contentValues.put(COL_4, c.groupName);
        contentValues.put(COL_5, c.phoneNumber);
        if (c.getBlacklist())
            contentValues.put(COL_6, 1);
        else
            contentValues.put(COL_6, 0);
        int rt = db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(c._id) } );

        return rt > 0;
    }


}
