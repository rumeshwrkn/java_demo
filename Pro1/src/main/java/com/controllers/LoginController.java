package com.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.models.UserModel;
import com.pro1.pro1.App;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable{
    @FXML
    private Button BtnLogin;
    @FXML
    private Label LblMessage;
    @FXML
    private TextField TxtEmail;
    @FXML
    private PasswordField TxtPass;
    public void onClickLogin(ActionEvent e) throws Exception{

        String email, password;
        email = TxtEmail.getText();
        password = TxtPass.getText();
        LblMessage.setText("");

        if( ValidationRules.isEmpty(email) || ValidationRules.isEmpty(password)){
            LblMessage.setText("Please enter Username & Password");
        }else {
            UserModel userModel = new UserModel();
            boolean result =  userModel.login(email, password);
            if (result) {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("dashboard-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage dashboard = new Stage();
                dashboard.setTitle("Dashboard");
                dashboard.setMaximized(false);
                dashboard.setScene(scene);
                dashboard.setResizable(false);
                dashboard.show();
                closeLoginWindow();

            } else {
                LblMessage.setText("Incorrect Username or Password");
            }
        }
    }

    private void closeLoginWindow(){
        Stage stage = (Stage) BtnLogin.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TxtEmail.setOnKeyPressed(keyEvent -> LblMessage.setText(""));

        TxtPass.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                LblMessage.setText("");
            }
        });
    }
}
