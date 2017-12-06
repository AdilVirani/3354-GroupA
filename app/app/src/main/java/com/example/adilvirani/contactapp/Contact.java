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

    public void update(String firstName, String lastName, String groupName, String phoneNumber, boolean bl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.groupName = groupName;
        this.phoneNumber = phoneNumber;
        this.blacklist = bl;
    }

    public int compareTo(Contact c) {
        return this.fullName().compareTo(c.fullName());
    }


    public void setID(int id) {
        this._id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }


    public String fullName() {
        StringJoiner j = new StringJoiner(" ");
        j.add(this.firstName);
        j.add(this.lastName);

        return j.toString();
    }

    public String getGroupName() {
        return this.groupName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public boolean getBlacklist() {return this.blacklist; }

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

