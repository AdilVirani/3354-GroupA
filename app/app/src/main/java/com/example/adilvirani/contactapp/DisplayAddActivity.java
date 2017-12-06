package com.example.adilvirani.contactapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class DisplayAddActivity extends AppCompatActivity {

    private EditText FirstName;
    private EditText LastName;
    private EditText GroupName;
    private EditText PhoneNum;

    private boolean blacklist;

    ContactDatabase myDb;
    Button buttonDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_add);

        myDb = new ContactDatabase(this);

        FirstName = (EditText) findViewById(R.id.editFirstName);
        LastName = (EditText) findViewById(R.id.editLastName);
        GroupName = (EditText) findViewById(R.id.editGroup);
        PhoneNum = (EditText) findViewById(R.id.editPhone);
        buttonDone = (Button) findViewById(R.id.button4);

        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox_blacklist);
        if (checkBox.isChecked()) {
            checkBox.setChecked(false);
        }

        addContact();
    }

    public void addContact() {
        buttonDone.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String firstName = FirstName.getText().toString();
                    String lastName = LastName.getText().toString();
                    String groupName = GroupName.getText().toString();
                    String phoneNumber = PhoneNum.getText().toString();

                    int bl = (blacklist) ? 1 : 0;
//                    int bl;
//                    if (blacklist) //if blacklist = true
//                        bl = 1;
//                    else
//                        bl = 0;

                    Contact contact = new Contact(firstName, lastName, groupName, phoneNumber, blacklist);
                    String fullName = contact.fullName();

                    Intent intent = new Intent();


                    if (!fullName.equals(" ")) {
                        intent.putExtra("fullName", fullName);
                        setResult(RESULT_OK, intent);
                        finish();

                        saveContact(contact);

//                            boolean isInserted = myDb.insertContact(first, last, gn, num, bl);
//                            if (isInserted == true)
//                                Toast.makeText(DisplayAddActivity.this,"Data Inserted", Toast.LENGTH_LONG).show();
//                            else
//                                Toast.makeText(DisplayAddActivity.this,"Data NOT Inserted", Toast.LENGTH_LONG).show();
//
                    }
                    else
                    {
                        Toast.makeText(DisplayAddActivity.this,"Data NOT Inserted", Toast.LENGTH_LONG).show();

                        setResult(RESULT_CANCELED, intent);
                        finish();
                    }
                }
            }
        );
    }

    public void saveContact(Contact c) {
//        boolean inserted = this.myDb.insertContact(c);
        if (this.myDb.insertContact(c) !=  null) {
            Toast.makeText(DisplayAddActivity.this,"Data Inserted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(DisplayAddActivity.this,"Data NOT Inserted", Toast.LENGTH_LONG).show();
        }
    }

    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {

            case R.id.checkBox_blacklist:
                blacklist = checked;
//                if (checked) {
//                    blacklist = true;
//                } else {
//                    blacklist = false;
//                }
                break;
        }
    }
}
