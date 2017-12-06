package com.example.adilvirani.contactapp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by evelynwong on 12/5/17.
 */

public class ContactDeleteTest {

    ContactDatabase mydb;
    Contact c;

    @Before
    void setUp() throws Exception {
        c = new Contact("adil", "virani", "n/a", "2145664791", false);
    }

    @Test
    void delete_works() {
        Assert.assertEquals(true, mydb.destroyContact(this.c));
    }

}

