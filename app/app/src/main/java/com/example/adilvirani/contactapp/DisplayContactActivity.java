package com.example.adilvirani.contactapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DisplayContactActivity extends AppCompatActivity {

    private ContactDatabase mydb;
    TextView namefield;
    TextView phonefield;
    TextView groupfield;
    int id_To_Update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contact);

        namefield = (TextView) findViewById(R.id.name_field);
        phonefield = (TextView) findViewById(R.id.phone_field);
        groupfield = (TextView) findViewById(R.id.group_field);


        mydb = new ContactDatabase(this);
        Bundle extras = getIntent().getExtras();

       if(extras !=null) {
            int value = extras.getInt("id");

            if(value>0){

                Cursor rs = mydb.getData(value);
                id_To_Update = value;
                rs.moveToFirst();

                String fullname = rs.getString(rs.getColumnIndex(ContactDatabase.COL_2)) + " " + rs.getString(rs.getColumnIndex(ContactDatabase.COL_3));
                String group = rs.getString(rs.getColumnIndex(ContactDatabase.COL_4));
                String number = rs.getString(rs.getColumnIndex(ContactDatabase.COL_5));

                if (!rs.isClosed())  {
                    rs.close();
                }

                namefield.setText((CharSequence)fullname);
                groupfield.setText((CharSequence)group);
                phonefield.setText((CharSequence)number);
            }
        }

    }

    public void editContact(View button) {

        Bundle dataBundle = new Bundle();
        dataBundle.putInt("id", id_To_Update);

        Intent intent = new Intent(this, DisplayEditActivity.class);

        intent.putExtras(dataBundle);

        startActivity(intent);
    }
}
