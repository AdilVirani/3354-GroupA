package com.example.adilvirani.contactapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ArrayList<String> nameList;
    private ArrayAdapter<String> adapter;


    static final int ADD_CONTACT_REQUEST = 1;

    nameList.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameList = new ArrayList<String>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nameList);
        ListView listView = (ListView) findViewById(R.id.contactList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ListView a = (ListView) adapterView;

            }
        });
    }

    /**
     * Called when the user taps the Contact's button
     */
    public void displayContactInfo(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayContactActivity.class);
        startActivity(intent);
    }

    public void addContact(View button) {
        Intent intent = new Intent(this, DisplayAddActivity.class);
        startActivityForResult(intent, ADD_CONTACT_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == ADD_CONTACT_REQUEST) {

            // Make sure the request was successful
            if (resultCode == RESULT_OK && data != null) {

                String returnString = data.getStringExtra("fullName");
                addListElement(returnString);
            }
        }
    }

    private void addListElement(String element) {
        nameList.add(element);
        adapter.notifyDataSetChanged();
    }


}

