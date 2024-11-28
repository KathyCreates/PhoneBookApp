package com.example.phonebook;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.net.URL;

import java.util.Optional;

public class Main extends Application {

    public final ObservableList<Contact> contactList = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Створення елементів інтерфейсу
        ListView<Contact> listView = new ListView<>(contactList);
        listView.setCellFactory(param -> new ListCell<Contact>() {
            @Override
            protected void updateItem(Contact item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName() + " - " + item.getPhoneNumber());
                }
            }
        });

        Button addButton = new Button("Додати контакт");
        addButton.setOnAction(e -> addContact());

        Button editButton = new Button("Редагувати контакт");
        editButton.setOnAction(e -> editContact(listView));

        Button deleteButton = new Button("Видалити контакт");
        deleteButton.setOnAction(e -> deleteContact(listView));

        listView.getStyleClass().add("list-view"); // Клас для ListView
        addButton.getStyleClass().add("button"); // Клас для кнопок
        editButton.getStyleClass().add("button");
        deleteButton.getStyleClass().add("button");

        // Основний контейнер для елементів
        VBox vbox = new VBox(10, listView, addButton, editButton, deleteButton);
        vbox.setId("main-container");
        Scene scene = new Scene(vbox, 230, 400);

        URL cssResource = getClass().getResource("/styles/style.css");
        if (cssResource == null) {
            throw new RuntimeException("CSS файл не знайдено: /styles/style.css");
        }

        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

        primaryStage.setTitle("Телефонний довідник");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Додавання нового контакту
    private void addContact() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Новий контакт");
        dialog.setHeaderText("Введіть ім'я та номер телефону");
        dialog.setContentText("Ім'я:");

        Optional<String> nameResult = dialog.showAndWait();
        nameResult.ifPresent(name -> {
            TextInputDialog phoneDialog = new TextInputDialog();
            phoneDialog.setTitle("Номер телефону");
            phoneDialog.setHeaderText("Введіть номер телефону");
            phoneDialog.setContentText("Номер:");

            Optional<String> phoneResult = phoneDialog.showAndWait();
            phoneResult.ifPresent(phone -> {
                contactList.add(new Contact(name, phone));
            });
        });
    }

    // Редагування існуючого контакту
    private void editContact(ListView<Contact> listView) {
        Contact selectedContact = listView.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            TextInputDialog dialog = new TextInputDialog(selectedContact.getName());
            dialog.setTitle("Редагувати контакт");
            dialog.setHeaderText("Змініть ім'я");
            dialog.setContentText("Ім'я:");

            Optional<String> nameResult = dialog.showAndWait();
            nameResult.ifPresent(name -> {
                TextInputDialog phoneDialog = new TextInputDialog(selectedContact.getPhoneNumber());
                phoneDialog.setTitle("Редагувати номер");
                phoneDialog.setHeaderText("Змініть номер телефону");
                phoneDialog.setContentText("Номер:");

                Optional<String> phoneResult = phoneDialog.showAndWait();
                phoneResult.ifPresent(phone -> {
                    selectedContact.setName(name);
                    selectedContact.setPhoneNumber(phone);
                    listView.refresh(); // Оновлення списку
                });
            });
        } else {
            showAlert("Виберіть контакт для редагування");
        }
    }

    // Видалення контакту
    private void deleteContact(ListView<Contact> listView) {
        Contact selectedContact = listView.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            contactList.remove(selectedContact);
        } else {
            showAlert("Виберіть контакт для видалення");
        }
    }

    // Показати повідомлення (alert)
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Інформація");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Клас для зберігання інформації про контакт
    public static class Contact {
        private String name;
        private String phoneNumber;

        public Contact(String name, String phoneNumber) {
            this.name = name;
            this.phoneNumber = phoneNumber;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        @Override
        public String toString() {
            return name + " - " + phoneNumber;
        }
    }
}
