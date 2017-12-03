package com.example.adilvirani.contactapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class DisplayContactActivity extends AppCompatActivity {

    private ContactDatabase mydb;
    TextView namefield;
    TextView phonefield;
    TextView groupfield;
    int id_To_Update = 0;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.contact_actions, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contact);

//        Toolbar contactActions = (Toolbar) findViewById(R.id.);
//        setSupportActionBar(contactActions);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_contact:
                // User chose the "Settings" item, show the app settings UI...
                if (mydb.destroyContact(id_To_Update)) {
                    Toast.makeText(DisplayContactActivity.this,"Contact deleted", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent();

                    setResult(RESULT_OK, intent);
                    finish();
                }

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
