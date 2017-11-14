package com.example.adilvirani.contactapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Contact's button */
    public void displayContactInfo(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayContactActivity.class);
        startActivity(intent);
    }

    public void editContact(View view) {
        Intent intent = new Intent(this, DisplayEditActivity.class);
        startActivity(intent);
    }
}
