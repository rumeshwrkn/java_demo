package com.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.models.UserTypesModel;
import com.models.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;


import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

public class RegisterController implements Initializable {
    @FXML
    private Button BtnClose;

    @FXML
    private Label LblError;

    @FXML
    private Label LblErrorEmail;

    @FXML
    private Label LblErrorFname;

    @FXML
    private Label LblErrorLname;

    @FXML
    private Label LblErrorMobile;

    @FXML
    private Label LblErrorPass;

    @FXML
    private Label LblSuccess;

    @FXML
    private TextField TxtEmail;

    @FXML
    private TextField TxtFname;

    @FXML
    private TextField TxtLname;

    @FXML
    private TextField TxtMobileNo;

    @FXML
    private PasswordField TxtPassword;

    @FXML
    private Button btest;

    @FXML
    private AnchorPane mainPain;

    @FXML
    private ComboBox CmbStatus;

    @FXML
    private ComboBox CmbUserType;

    @FXML
    private Label LblErrorStatus;

    @FXML
    private Button BtnGenPass;
    @FXML
    private Label LblGenPass;

    public void RegUser(ActionEvent e){
        // Set Error labels
        LblError.setText("");
        LblSuccess.setText("");
        LblErrorFname.setText("");
        LblErrorLname.setText("");
        LblErrorEmail.setText("");
        LblErrorMobile.setText("");
        LblErrorPass.setText("");

        // set init style to fields
        TxtFname.getStyleClass().remove("errors");
        TxtLname.getStyleClass().remove("errors");
        TxtMobileNo.getStyleClass().remove("errors");
        TxtEmail.getStyleClass().remove("errors");
        TxtPassword.getStyleClass().remove("errors");

        // assign text fields to variables
        String fName    =  TxtFname.getText();
        String lName    =  TxtLname.getText();
        String mobile   =  TxtMobileNo.getText();
        String email    =  TxtEmail.getText();
        String pass     =  TxtPassword.getText();
        String status   = (String) CmbStatus.getSelectionModel().getSelectedItem();

        System.out.println(status);

        ArrayList<Integer> errors = new ArrayList<Integer>();

        if (ValidationRules.isEmpty(fName) ||  ValidationRules.isEmpty(lName) ||
            ValidationRules.isEmpty(mobile) || ValidationRules.isEmpty(email) ||
            ValidationRules.isEmpty(pass) || ValidationRules.isEmpty(status)
            ){
            errors.add(1);
            LblError.setText("All fields are required");
        }else {
            if (!ValidationRules.isText(fName)) {
                errors.add(1);
                LblErrorFname.setText("Invalid first name");
                TxtFname.getStyleClass().add("errors");
            }
            if (!ValidationRules.isText(lName)) {
                errors.add(1);
                LblErrorLname.setText("Invalid last name");
                TxtLname.getStyleClass().add("errors");
            }
            if (!ValidationRules.isMobile(mobile)) {
                errors.add(1);
                LblErrorMobile.setText("Invalid mobile number");
                TxtMobileNo.getStyleClass().add("errors");
            }
            if (!ValidationRules.isEmail(email)) {
                errors.add(1);
                LblErrorEmail.setText("Invalid email address");
                TxtEmail.getStyleClass().add("errors");
            }
            if (!ValidationRules.minLen(pass, 5)) {
                errors.add(1);
                LblErrorPass.setText("Password must have at least 5 characters");
                TxtPassword.getStyleClass().add("errors");
            }
        }
        if (errors.size() == 0) {
            UserModel userModel = new UserModel();
            if (!userModel.isUniqueUser(email)) {
                LblError.setText("Email address already registered");
            } else {
                String hashedPwd = BCrypt.withDefaults().hashToString(12,pass.toCharArray());


                status = Helper.selectedUserStatus(status,true);


                boolean result = userModel.RegUser(fName, lName, mobile, email, hashedPwd, status);
                if (result) {
                    LblSuccess.setText("User added.");
                }
            }
        }
   }
    public void closeWindow(ActionEvent e){
       // Stage stage = (Stage) BtnClose.getScene().getWindow();
       // stage.close();
      // Window anchorPane = BtnClose.getScene().getWindow();
    }
    @FXML
    public void onClickbtn(ActionEvent e){
       try {
           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("com.pro1.pro1/test.fxml"));
           Parent root1 = (Parent) fxmlLoader.load();
           Stage stage = new Stage();
           stage.setScene(new Scene(root1));
           stage.show();
       } catch (IOException ex) {
           throw new RuntimeException(ex);
       }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//
//        BiMap<String, String> students = HashBiMap.create();
//        students.put("1", "stu 1");
//        students.put("2", "stu 2");
//
//        System.out.println(students.inverse().get("stu 1"));


        BiMap<String, String> testList = Helper.userType();
        for (Map.Entry<String, String> list : testList.entrySet()  ){
            CmbUserType.getItems().add(list.getValue());
        }

        BiMap<String, String> userStatus = (BiMap<String, String>) Helper.userStatus("1",false);
        System.out.println(userStatus);

//        for (Map.Entry<String, String> list : userStatus.entrySet()  ){
//            CmbStatus.getItems().add(list.getValue());
//        }



        //CmbStatus.getItems().addAll(Helper.cmbStatus());

       //CmbStatus. Helper.cmbStatus();


//      mainPain.setEffect(new DropShadow(2d, 0d, +2d, Color.BLACK));

//        UserTypesModel userTypes = new UserTypesModel();
//        ResultSet rs = userTypes.getTypes();
//        try {
//            while (rs.next()) {
//                CmbStatus.getItems().add(rs.getString("ut_name"));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }



    }





    public void GeneratePass(ActionEvent e){

       PasswordGenerator pass = new PasswordGenerator();

       CharacterRule R1 = new CharacterRule(EnglishCharacterData.UpperCase,2);
       CharacterRule R2 = new CharacterRule(EnglishCharacterData.LowerCase,3);
       CharacterRule R3 = new CharacterRule(EnglishCharacterData.Digit, 2);
       CharacterRule R4 = new CharacterRule(EnglishCharacterData.Special, 1);


       String newPass =  pass.generatePassword(8, R1,R2,R3);

       TxtPassword.setText(newPass);
       LblGenPass.setText(newPass);


    }
}
