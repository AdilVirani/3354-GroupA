package com.example.adilvirani.contactapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
//@RunWith(AndroidJUnit4.class)
//public class ExampleInstrumentedTest {
//    @Test
//    public void useAppContext() throws Exception {
//        // Context of the app under test.
//        Context appContext = InstrumentationRegistry.getTargetContext();
//
//        assertEquals("com.example.adilvirani.contactapp", appContext.getPackageName());
//    }
//}

////
//@RunWith(AndroidJUnit4.class)
//public class CreateContactTest {
//    @Test
//    public void useAppContext() throws Exception {
//        // Context of the app under test.
//        Context appContext = InstrumentationRegistry.getTargetContext();
//
//        assertEquals("com.example.adilvirani.contactapp", appContext.getPackageName());
//    }
//}


@RunWith(AndroidJUnit4.class)
public class ContactDatabaseTest {
    @Test
    public void addContact() throws Exception {
        // warn: runs on same db
        Context appContext = InstrumentationRegistry.getTargetContext();
        Contact c = new Contact("adil", "virani", "n/a", "2145664791", false);


        ContactDatabase db = new ContactDatabase(appContext);
        List<Contact> allContacts = db.allContacts();
//        assertEquals(db.allContacts().size(), 1);

        assertNotNull(db.insertContact(c));
        assertEquals(db.allContacts().size(), allContacts.size() + 1);

        assertTrue(db.destroyContact(c));
        assertEquals(db.allContacts().size(), allContacts.size());
    }
}