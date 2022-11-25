module com.pro1.pro1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires bcrypt;
    requires com.google.common;
    requires jsr305;
    requires passay;

    opens com.pro1.pro1 to javafx.fxml;
    exports com.pro1.pro1;
    exports com.controllers;
    opens com.controllers to javafx.fxml;
}