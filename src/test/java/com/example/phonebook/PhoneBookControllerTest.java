package com.example.phonebook;

import com.example.phonebook.models.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PhoneBookControllerTest {
    @Test
    void testAddContact() {
        ObservableList<Contact> contacts = FXCollections.observableArrayList();
        Contact contact = new Contact("Kate Shkuryna", "1234567890");
        contacts.add(contact);
        assertEquals(1, contacts.size());
        assertEquals(contact, contacts.get(0));
    }
}