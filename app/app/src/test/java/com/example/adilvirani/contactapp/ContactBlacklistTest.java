package com.example.adilvirani.contactapp;

import org.junit.Test;

import static org.junit.Assert.*;

//Test to see if the blacklist works when you add a contact
public class ContactBlacklistTest {
    @Test
    public void blacklist_works() throws Exception {
        Contact c = new Contact("adil", "virani", "n/a", "2145664791", false);
        assertEquals(c.getBlacklist(), false);
    }
}