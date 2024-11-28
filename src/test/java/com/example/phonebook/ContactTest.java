package com.example.phonebook;

import com.example.phonebook.models.Contact;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {
    @Test
    void testContactCreation() {
        Contact contact = new Contact("Kate Shkuryna", "1234567890");
        assertEquals("Kate Shkuryna", contact.getName());
        assertEquals("1234567890", contact.getPhoneNumber());
    }

    @Test
    void testSetters(){
        Contact contact = new Contact("Kate Shkuryna", "1234567890");
        contact.setName("Kate Shkuryna");
        contact.setPhoneNumber("0634764723");
        assertEquals("Kate Shkuryna", contact.getName());
        assertEquals("0634764723", contact.getPhoneNumber());
    }
}
