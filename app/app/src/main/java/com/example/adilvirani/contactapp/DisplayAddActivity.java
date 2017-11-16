package com.example.adilvirani.contactapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DisplayAddActivity extends AppCompatActivity {

    private EditText FirstName;
    private EditText LastName;
    private EditText GroupName;
    private EditText PhoneNum;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_add);

        FirstName = (EditText) findViewById(R.id.editFirstName);
        LastName = (EditText) findViewById(R.id.editLastName);
        GroupName = (EditText) findViewById(R.id.editGroup);
        PhoneNum = (EditText) findViewById(R.id.editPhone);

    }

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

    }

    public void goBack(View button) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
