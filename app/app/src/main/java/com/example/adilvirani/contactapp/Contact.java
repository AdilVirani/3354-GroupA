package com.example.adilvirani.contactapp;


import android.app.Application;
import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringJoiner;


public class Contact implements Serializable,Comparable<Contact>{

    protected int _id;
    protected String firstName, lastName, groupName, phoneNumber;
    protected boolean blacklist;


    //Contact constructor
    Contact(String firstName, String lastName, String groupName, String phoneNumber, boolean bl)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.groupName = groupName;
        this.phoneNumber = phoneNumber;
        this.blacklist = bl;
    }

    public int id()  {
        return this._id;
    }


    //Update contact
    public void update(String firstName, String lastName, String groupName, String phoneNumber, boolean bl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.groupName = groupName;
        this.phoneNumber = phoneNumber;
        this.blacklist = bl;
    }

    //Compare function for search
    public int compareTo(Contact c) {
        return this.fullName().compareTo(c.fullName());
    }

    //Set ID
    public void setID(int id) {
        this._id = id;
    }


    //Get first name
    public String getFirstName() {
        return this.firstName;
    }

    //Get last name
    public String getLastName() {
        return this.lastName;
    }


    //Print fullName
    public String fullName() {
        StringJoiner j = new StringJoiner(" ");
        j.add(this.firstName);
        j.add(this.lastName);

        return j.toString();
    }

    //Get GroupName
    public String getGroupName() {
        return this.groupName;
    }

    //Get phone number
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    //get black list
    public boolean getBlacklist() {return this.blacklist; }

    //return full name
    public String toString() {
        return this.fullName();
    }
}

//class MyData extends AppCompatActivity {
//
//    ArrayList<Contact> contactArrayList = new ArrayList<Contact>();
//
//
//}

