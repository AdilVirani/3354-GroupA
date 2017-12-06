package com.example.adilvirani.contactapp;

import android.content.Context;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ContactDBTest {


    private ContactDatabase mydb;

    @Before
    public void setUp(){
        mydb = new ContactDatabase(InstrumentationRegistry.getTargetContext());
    }


    @Test
    public void addContactDatabase()
    {
        Contact c = new Contact("adil", "virani", "n/a", "2145664791", false);

        assertNotNull(mydb.insertContact(c));
    }

    @Test
    public void deleteContactDatabase()
    {
        Contact c = new Contact("adil", "virani", "n/a", "2145664791", false);

        mydb.insertContact(c);

        assertTrue(mydb.destroyContact(c));
    }

}
