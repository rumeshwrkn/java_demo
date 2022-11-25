package com.controllers;

import com.pro1.pro1.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.xml.transform.Transformer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewDashboard  implements Initializable {
    @FXML
    private Button BtnAll;

    @FXML
    private ToggleGroup vehicle;

    @FXML
    private Button test;

    @FXML
    private AnchorPane regNewUser;



    public void onClickReg(ActionEvent e) throws IOException {
       //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register-view.fxml"));
       AnchorPane pane = new FXMLLoader().load(App.class.getResource("register-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());

        regNewUser.getChildren().setAll(pane);

//        Stage dashboard = new Stage();
//        dashboard.setTitle("Register new user ");
//        dashboard.setScene(scene);
//        dashboard.show();
    }

    public void onClickAllUser(ActionEvent e) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("test.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        Stage dashboard = new Stage();
//        dashboard.setTitle("All Users");
//        dashboard.setScene(scene);
//        dashboard.show();

        AnchorPane pane = new FXMLLoader().load(App.class.getResource("test.fxml"));
        regNewUser.getChildren().setAll(pane);
    }

    public void test(ActionEvent e){
        RadioButton vehicleType = (RadioButton) vehicle.getSelectedToggle();
        System.out.println(vehicleType.getText());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



}
