package com.example.adilvirani.contactapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DisplayAddActivity extends AppCompatActivity {

    private EditText FirstName;
    private EditText LastName;
    private EditText GroupName;
    private EditText PhoneNum;

    ContactDatabase myDb;
    Button bottonDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_add);

        myDb = new ContactDatabase(this);

        FirstName = (EditText) findViewById(R.id.editFirstName);
        LastName = (EditText) findViewById(R.id.editLastName);
        GroupName = (EditText) findViewById(R.id.editGroup);
        PhoneNum = (EditText) findViewById(R.id.editPhone);
        bottonDone = (Button) findViewById(R.id.button4);

        addContact();
    }
/*
    public void addContact(View button) {


        String first = FirstName.getText().toString();
        String last = LastName.getText().toString();
        String gn = GroupName.getText().toString();
        String num = PhoneNum.getText().toString();

        String fullName = first + " " + last;


        Contact newContact = new Contact(first, last, gn, num);

        Intent intent = new Intent();

        //((MyData)getApplicationContext()).contactArrayList.add(newContact);
        if(!fullName.equals(" ")) {
            intent.putExtra("fullName", fullName);
            setResult(RESULT_OK, intent);
            finish();
        }
        else
        {
            setResult(RESULT_CANCELED,intent);
            finish();
        }

    }*/

    public void addContact() {
        bottonDone.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String first = FirstName.getText().toString();
                        String last = LastName.getText().toString();
                        String gn = GroupName.getText().toString();
                        String num = PhoneNum.getText().toString();

                        String fullName = first + " " + last;

                        Contact newContact = new Contact(first, last, gn, num);

                        Intent intent = new Intent();

                        //((MyData)getApplicationContext()).contactArrayList.add(newContact);
                        if(!fullName.equals(" ")) {
                            intent.putExtra("fullName", fullName);
                            setResult(RESULT_OK, intent);
                            finish();
                            boolean isInserted = myDb.insertContact(first, last, gn, num);
                            if (isInserted == true)
                                Toast.makeText(DisplayAddActivity.this,"Data Inserted", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(DisplayAddActivity.this,"Data NOT Inserted", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(DisplayAddActivity.this,"Data NOT Inserted", Toast.LENGTH_LONG).show();

                            setResult(RESULT_CANCELED,intent);
                            finish();
                        }
                    }
                }
        );
    }

    public void goBack(View button) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
