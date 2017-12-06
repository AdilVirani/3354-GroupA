package com.example.adilvirani.contactapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    protected List<Contact> contacts;
    protected ArrayAdapter<Contact> adapter;
    protected ContactDatabase mydb;
    protected boolean sortReverse = false;

    static final int ADD_CONTACT_REQUEST = 1;
    static final int VIEW_CONTACT_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mydb = new ContactDatabase(this);
        refreshList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.list_contacts, menu);

        return true;
    }

    //Refresh list when activity created
    protected void refreshList() {
        this.contacts = mydb.allContacts();
        this.adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, this.contacts);

        renderList();
    }

    //render list and check if sort clicked
    protected void renderList() {
        if (this.sortReverse) {
            Comparator<Contact> comp = Collections.reverseOrder();
            Collections.sort(this.contacts, comp);
        } else {
            Collections.sort(this.contacts);
        }

        //Add to listview and adapter
        ListView listView = (ListView) findViewById(R.id.contactList);
        listView.setAdapter(this.adapter);

        //if contact clicked, open contact viewer
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                int id_To_Search = position + 1;
                Contact c = (Contact) (parent.getItemAtPosition(position));
                System.out.println(c.getPhoneNumber());
                System.out.println(c.getGroupName());
                System.out.println(c.fullName());

                Bundle data = new Bundle();
                data.putSerializable("contact", c);

//                parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, DisplayContactActivity.class);
                intent.putExtras(data);

                startActivityForResult(intent, VIEW_CONTACT_REQUEST);
            }
        });
    }

    //Sort toggle in menu
    protected void toggleSort() {
        this.sortReverse = !this.sortReverse;

        renderList();
    }
//
//    protected void toggleSort() {
//        this.sortReverse = !this.sortReverse;
//        System.out.println(this.sortReverse);
//        applySort();
//    }

    /**
     * Called when the user taps the Contact's button
     */
    //display contact info intent when name clicked
    public void displayContactInfo(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayContactActivity.class);
        startActivity(intent);
    }

    //add contact button
    public void addContact(View button) {
        Intent intent = new Intent(this, DisplayAddActivity.class);
        startActivityForResult(intent, ADD_CONTACT_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        refreshList();

    }

    //if sort clicked in menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_contacts:
                this.toggleSort(); //toggle the sort method if clickd
                return true;
       /* switch (item.getItemId()) {
            case R.id.search_contact:
                return true;*/
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }


    private void addContact(String element) {
        adapter.notifyDataSetChanged();
    }

}

