package com.example.adilvirani.contactapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayEditActivity extends AppCompatActivity {

    protected ContactDatabase mydb;
    protected Contact contact;

    EditText firstfield;
    EditText lastfield;
    EditText phonefield;
    EditText groupfield;

    private boolean blacklist;

    Button buttonDone;
    int id_To_Update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_edit);

        firstfield = (EditText) findViewById(R.id.editFirstName);
        lastfield = (EditText) findViewById(R.id.editLastName);
        phonefield = (EditText) findViewById(R.id.editPhone);
        groupfield = (EditText) findViewById(R.id.editGroup);

        buttonDone = (Button) findViewById(R.id.button4);

        mydb = new ContactDatabase(this);
        Bundle extras = getIntent().getExtras();

        Contact c = (Contact) extras.getSerializable("contact");
        contact = c;

        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox_blacklist);
        if (checkBox.isChecked()) {
            checkBox.setChecked(true);
        }

            firstfield.setText(c.getFirstName(), TextView.BufferType.EDITABLE);
            lastfield.setText(c.getLastName(), TextView.BufferType.EDITABLE);
            groupfield.setText(c.getGroupName(), TextView.BufferType.EDITABLE);
            phonefield.setText(c.getPhoneNumber(), TextView.BufferType.EDITABLE);
//
//        if(extras !=null) {
//            int value = extras.getInt("id");
//            if(value>0){
//                id_To_Update = value;
//                Cursor rs = mydb.getData(value);
//                rs.moveToFirst();
//
//                String first = rs.getString(rs.getColumnIndex(ContactDatabase.COL_2));
//                String last = rs.getString(rs.getColumnIndex(ContactDatabase.COL_3));
//                String group = rs.getString(rs.getColumnIndex(ContactDatabase.COL_4));
//                String number = rs.getString(rs.getColumnIndex(ContactDatabase.COL_5));
//
//                if (!rs.isClosed())  {
//                    rs.close();
//                }
//
//                firstfield.setText(first, TextView.BufferType.EDITABLE);
//                lastfield.setText(last, TextView.BufferType.EDITABLE);
//                groupfield.setText(group, TextView.BufferType.EDITABLE);
//                phonefield.setText(number, TextView.BufferType.EDITABLE);
//            }
//        }

        editContact();
    }

    public void editContact() {
        buttonDone.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String first = firstfield.getText().toString();
                    String last = lastfield.getText().toString();
                    String gn = groupfield.getText().toString();
                    String num = phonefield.getText().toString();

                    contact.update(first, last, gn, num, blacklist);

                    boolean isInserted = mydb.updateContact(contact);
//                        boolean isInserted = mydb.updateContact(id_To_Update, first, last, gn, num, bl);
                    if (isInserted == true) {
                        Toast.makeText(DisplayEditActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(DisplayEditActivity.this,"Data NOT Updated", Toast.LENGTH_LONG).show();

                }
            }
        );
    }

    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {

            case R.id.checkBox_blacklist:
                if (checked) {
                    blacklist = true;
                } else {
                    blacklist = false;
                }
                break;
        }
    }

    public void goBack(View button) {
        Intent intent = new Intent(this, DisplayContactActivity.class);
        startActivity(intent);
    }
}
