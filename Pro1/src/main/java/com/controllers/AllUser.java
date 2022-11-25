package com.controllers;

import com.models.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AllUser implements Initializable {

    @FXML
    private Label LblSuccess;

    @FXML
    private TableView<User> TableUser;

    @FXML
    private TextField TxtSearch;

    @FXML
    private Button deleteUser;

    @FXML
    private TableColumn<User, String> email;

    @FXML
    private TableColumn<User, String> fname;

    @FXML
    private TableColumn<User, String> id;

    @FXML
    private TableColumn<User, String> lname;

    @FXML
    private TableColumn<User, String> mobile;

    @FXML
    private TableColumn<User, String> status;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        UserModel userModel = new UserModel();
        id.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
        fname.setCellValueFactory(new PropertyValueFactory<User, String>("fname"));
        TableUser.setItems(userModel.getUsers());


    }
}