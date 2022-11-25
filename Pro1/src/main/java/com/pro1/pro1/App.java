package com.pro1.pro1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("register-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("ABC Company");
        //stage.getIcons().add(new Image("file:icon.png"));
        stage.setScene(scene);
        stage.setMaximized(false);
        //stage.setResizable(false);
       // stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public static void main(String[] args) {
        launch();

    }
}