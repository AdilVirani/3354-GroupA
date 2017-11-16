package com.example.adilvirani.contactapp;


import android.app.Application;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;


public class Contact extends AppCompatActivity {

    Contact(String firstname, String lastname, String groupname, String n)
    {

        String first_name = firstname;
        String last_name = lastname;
        String group_name = groupname;
        String number = n;

    }
}

class MyData extends AppCompatActivity {

    ArrayList<Contact> contactArrayList = new ArrayList<Contact>();


}

