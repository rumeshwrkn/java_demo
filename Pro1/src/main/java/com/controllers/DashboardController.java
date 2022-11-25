package com.controllers;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.models.Db;
import com.models.UserModel;
import com.models.UserTypesModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private TableView<User> TableUser;
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
    @FXML
    private TableColumn<User, String> select;
    @FXML
    private Label LblSuccess;
    @FXML
    private TextField TxtSearch;
    @FXML
    private Button deleteUser;
    UserModel userModel = new UserModel();
    ObservableList<User> allUsers;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.userInfo();
        LblSuccess.setText("");
        fname.getStyleClass().add("header-left");
        lname.getStyleClass().add("header-left");
        mobile.getStyleClass().add("header-left");
        email.getStyleClass().add("header-left");
        status.getStyleClass().add("header-left");
    }
    public void userInfo(){
        id.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
        fname.setCellValueFactory(new PropertyValueFactory<User, String>("fname"));
        lname.setCellValueFactory(new PropertyValueFactory<User, String>("lname"));
        mobile.setCellValueFactory(new PropertyValueFactory<User, String>("mobile"));
        email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        status.setCellValueFactory(new PropertyValueFactory<User, String>("status"));
        select.setCellValueFactory(new PropertyValueFactory<User, String>("selectUser"));

        fname.setCellFactory(TextFieldTableCell.forTableColumn());
        lname.setCellFactory(TextFieldTableCell.forTableColumn());
        mobile.setCellFactory(TextFieldTableCell.forTableColumn());
        email.setCellFactory(TextFieldTableCell.forTableColumn());

        ArrayList test = new ArrayList<String>();

        UserTypesModel userTypes = new UserTypesModel();
        ResultSet rs =  userTypes.getTypes();
        try {
            while (rs.next()){
                test.add(rs.getString("ut_name"));
            }
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }

        status.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList(test)));

        /*
        UserModel userModel = new UserModel();
        TableUser.getItems().clear();
        */

        ObservableList<User> allUsers = userModel.getUsers();
        TableUser.setItems(allUsers);
        TableUser.setEditable(true);

        FilteredList<User> filteredList = new FilteredList<>(allUsers, v -> true);
        TxtSearch.textProperty().addListener((observable, old_value, new_value ) -> {
            filteredList.setPredicate(user -> {
                if (new_value.isEmpty() || new_value == "" || new_value.isBlank()){
                    return true;
                }
                String searchKey = new_value.toLowerCase();

                if(user.getId().toLowerCase().indexOf(searchKey) > -1 ) {
                    return true;
                }
                else if(user.getFname().toLowerCase().indexOf(searchKey) > -1) {
                    return true;
                }else if(user.getLname().toLowerCase().indexOf(searchKey) > -1 ){
                        return true;
                }else if(user.getMobile().toLowerCase().indexOf(searchKey) > -1 ){
                    return true;
                }
                else if(user.getEmail().toLowerCase().indexOf(searchKey) > -1 ){
                    return true;
                }else {
                    return false;
                }
            });
        });
        SortedList<User> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(TableUser.comparatorProperty());
        TableUser.setItems(sortedList);
    }


    /**
     * Edit First name of the user
     */
    public void editFirstName(TableColumn.CellEditEvent e) throws SQLException {
        LblSuccess.getStyleClass().removeAll();
        String id = TableUser.getSelectionModel().getSelectedItem().id;
        String oldVal = (String) e.getOldValue();
        String newVal = (String) e.getNewValue();

        if(! ValidationRules.isText(newVal)){
            Alert alert = new Alert(Alert.AlertType.WARNING,"Invalid First name", ButtonType.OK);
            alert.initStyle(StageStyle.UNDECORATED);
            alert.setHeaderText(null);
            alert.setGraphic(null);
            alert.show();
        }else {
            if (!oldVal.matches(newVal)) {
                UserModel userModel = new UserModel();
                boolean isUpdate = userModel.update("u_fname", newVal, id);
                if (isUpdate) {
                    LblSuccess.getStyleClass().add("text-success");
                    LblSuccess.setText("Update successful");
                }
            } else {
                LblSuccess.getStyleClass().add("text-warning");
                LblSuccess.setText("No changes found");
            }
        }
       // this.userInfo();
    }

    /**
     * Edit Last name of the user
     */
    public void editLastName(TableColumn.CellEditEvent e) throws SQLException {
        LblSuccess.getStyleClass().removeAll();
        String id = TableUser.getSelectionModel().getSelectedItem().id;
        String oldVal = (String) e.getOldValue();
        String newVal = (String) e.getNewValue();

        if(! ValidationRules.isText(newVal)){
            Alert alert = new Alert(Alert.AlertType.WARNING,"Invalid Last name", ButtonType.OK);
            alert.initStyle(StageStyle.UNDECORATED);
            alert.setHeaderText(null);
            alert.setGraphic(null);
            alert.show();
        }else {
            if (!oldVal.matches(newVal)) {
                UserModel userModel = new UserModel();
                boolean isUpdate = userModel.update("u_lname", newVal, id);
                if (isUpdate) {
                    LblSuccess.getStyleClass().add("text-success");
                    LblSuccess.setText("Update successful");
                }
            } else {
                LblSuccess.getStyleClass().add("text-warning");
                LblSuccess.setText("No changes found");
            }
        }
       // this.userInfo();
    }

    /**
     * Edit Mobile number of the user
     */
    public void editMobile(TableColumn.CellEditEvent e) throws SQLException {
        LblSuccess.getStyleClass().removeAll();
        String id = TableUser.getSelectionModel().getSelectedItem().id;
        String oldVal = (String) e.getOldValue();
        String newVal = (String) e.getNewValue();

        if(! ValidationRules.isMobile(newVal)){
            Alert alert = new Alert(Alert.AlertType.WARNING,"Invalid Mobile number", ButtonType.OK);
            alert.initStyle(StageStyle.UNDECORATED);
            alert.setHeaderText(null);
            alert.setGraphic(null);
            alert.show();
        }else {
            if (!oldVal.matches(newVal)) {
                UserModel userModel = new UserModel();
                boolean isUpdate = userModel.update("u_mobile", newVal, id);
                if (isUpdate) {
                    LblSuccess.setText("Update successful");
                    LblSuccess.getStyleClass().add("text-success");
                }
            } else {
                LblSuccess.getStyleClass().add("text-warning");
                LblSuccess.setText("No changes found");
            }
        }
       // this.userInfo();
    }


    /**
     * Edit Email of the user
     */
    public void editEmail(TableColumn.CellEditEvent e)  {
        LblSuccess.getStyleClass().removeAll();
        boolean isUpdate = false;
        String id = TableUser.getSelectionModel().getSelectedItem().id;
        String oldVal = (String) e.getOldValue();
        String newVal = (String) e.getNewValue();

        if(! ValidationRules.isEmail(newVal)){
            Alert alert = new Alert(Alert.AlertType.WARNING,"Invalid Email address", ButtonType.OK);
            alert.initStyle(StageStyle.UNDECORATED);
            alert.setHeaderText(null);
            alert.setGraphic(null);
            alert.show();
        }else {
            if (!oldVal.matches(newVal)) {
                UserModel userModel = new UserModel();
                try {
                    isUpdate = userModel.update("u_email", newVal, id);
                }catch (Exception ex){
                    LblSuccess.getStyleClass().add("text-warning");
                    LblSuccess.setText("Email already in use");
                }
                if (isUpdate) {
                    LblSuccess.getStyleClass().add("text-success");
                    LblSuccess.setText("Update successful");
                }
            } else {
                LblSuccess.getStyleClass().add("text-warning");
                LblSuccess.setText("No changes found");
            }
        }
       // this.userInfo();
    }

//    public String userStatus(String name){
//        BiMap<String, String> status = HashBiMap.create();
//        status.put("1","Active");
//        status.put("0","Inactive");
//        return status.inverse().get(name);
//    }

    /**
     * Edit status of the user
     */
    public void editStatus(TableColumn.CellEditEvent e)  {
        LblSuccess.getStyleClass().removeAll();
        boolean isUpdate = false;
        String id = TableUser.getSelectionModel().getSelectedItem().id;
        String oldVal = (String) e.getOldValue();
        String newVal = (String) e.getNewValue();

        if(ValidationRules.isEmpty(newVal)){
            Alert alert = new Alert(Alert.AlertType.WARNING,"Invalid Status", ButtonType.OK);
            alert.initStyle(StageStyle.UNDECORATED);
            alert.setHeaderText(null);
            alert.setGraphic(null);
            alert.show();
        }else {
            if (!oldVal.matches(newVal)) {
                UserModel userModel = new UserModel();
                try {
                    newVal =  Helper.selectedUserStatus(newVal, true );
                    isUpdate = userModel.update("u_status",newVal, id);
                }catch (Exception ex){
                    LblSuccess.getStyleClass().add("text-warning");
                    LblSuccess.setText(ex.getMessage());
                }
                if (isUpdate) {
                    LblSuccess.getStyleClass().add("text-success");
                    LblSuccess.setText("Update successful");
                }
            } else {
                LblSuccess.getStyleClass().add("text-warning");
                LblSuccess.setText("No changes found");
            }
        }
      //   this.userInfo();
    }




    public void clearLabel(TableColumn.CellEditEvent e){
        LblSuccess.setText("");
    }
    public void deleteUser(ActionEvent e){
        allUsers = userModel.getUsers();
        ObservableList<User> selected = FXCollections.observableArrayList();

        for (User bean: allUsers){
            if (bean.getSelectUser().isSelected()){
                System.out.println(bean);
            }
        }

        boolean isRowSelected = TableUser.getSelectionModel().getSelectedItem() != null;
        if (isRowSelected) {
            String id = TableUser.getSelectionModel().getSelectedItem().id;
            String name =   TableUser.getSelectionModel().getSelectedItem().fname + " " +
                            TableUser.getSelectionModel().getSelectedItem().lname;
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to remove the user account associated with \n" +
                    "User ID: " + id + "\nUser name: " + name);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Remove user");
            alert.setHeaderText(null);
            alert.setGraphic(null);
            Optional<ButtonType> re =  alert.showAndWait();

            if (re.get() == ButtonType.OK){
                UserModel userModel = new UserModel();
                try {
                 boolean isDeleted = userModel.delete(id);
                 if (isDeleted){
                    LblSuccess.getStyleClass().removeAll();
                    LblSuccess.setText("User account successfully removed");
                    allUsers = userModel.getUsers();

                    allUsers.removeIf(user -> user.id.equals(id));
                    TableUser.getItems().removeAll();
                    TableUser.setItems(allUsers);
                 }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
