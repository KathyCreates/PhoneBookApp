package com.example.phonebook;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneBookTest {

    @Test
    public void testDeleteContact() {
        Main app = new Main();

        Main.Contact contact1 = new Main.Contact("Ірина", "123456789");
        Main.Contact contact2 = new Main.Contact("Олег", "987654321");

        app.contactList.add(contact1);
        app.contactList.add(contact2);

        assertEquals(2, app.contactList.size());
        assertTrue(app.contactList.contains(contact1));
        assertTrue(app.contactList.contains(contact2));

        app.contactList.remove(contact1);

        assertEquals(1, app.contactList.size());
        assertFalse(app.contactList.contains(contact1));
        assertTrue(app.contactList.contains(contact2));
    }
}
