package com.example.adilvirani.contactapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ArrayList<String> nameList;
    private ArrayAdapter<String> adapter;
    ContactDatabase mydb;

    static final int ADD_CONTACT_REQUEST = 1;
    static final int VIEW_CONTACT_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        renderList();
    }

    protected void renderList() {
        mydb = new ContactDatabase(this);

        nameList = mydb.getAllContacts();
        System.out.println(Arrays.toString(nameList.toArray()));

        //nameList = new ArrayList<String>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nameList);
        ListView listView = (ListView) findViewById(R.id.contactList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                System.out.println(position);
                        System.out.println(id);

//                int id_To_Search = int(id);
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", (int) id);

                Intent intent = new Intent(MainActivity.this, DisplayContactActivity.class);

                intent.putExtras(dataBundle);

                startActivity(intent);
            }
        });
    }

    /**
     * Called when the user taps the Contact's button
     */
    public void displayContactInfo(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayContactActivity.class);
        startActivityForResult(intent, VIEW_CONTACT_REQUEST);
    }

    public void addContact(View button) {
        Intent intent = new Intent(this, DisplayAddActivity.class);
        startActivityForResult(intent, ADD_CONTACT_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        switch (requestCode) {
            case ADD_CONTACT_REQUEST:

            // Make sure the request was successful
            if (resultCode == RESULT_OK && data != null) {

                String returnString = data.getStringExtra("fullName");
                addListElement(returnString);
            }
            case VIEW_CONTACT_REQUEST:
                System.out.println("hehe");
                renderList();
        }
    }

    private void addListElement(String element) {
        nameList.add(element);
        adapter.notifyDataSetChanged();
    }


}

