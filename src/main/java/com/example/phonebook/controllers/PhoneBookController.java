package com.example.phonebook.controllers;

import com.example.phonebook.models.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PhoneBookController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField phoneField;
    @FXML
    private ListView<Contact> contactListView;

    private final ObservableList<Contact> contacts = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        contactListView.setItems(contacts);
        ListView<String> listView = new ListView<>();
        listView.setCellFactory(param -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item);
                }
            }
        });
    }

    @FXML
    public void addContact() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        if (!name.isEmpty() && !phone.isEmpty()) {
            contacts.add(new Contact(name, phone));
            nameField.clear();
            phoneField.clear();
        } else {
            showAlert("Error", "Both name and phone number are required!");
        }
    }

    @FXML
    public void editContact() {
        Contact selectedContact = contactListView.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            selectedContact.setName(nameField.getText());
            selectedContact.setPhoneNumber(phoneField.getText());
            contactListView.refresh();
        } else {
            showAlert("Error", "No contact selected!");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
