package com.controllers;

import com.models.StudentModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentController implements Initializable {

    @FXML
    private TableView<Student> TableStudent;

    @FXML
    private TableColumn<Student, String> id;

    @FXML
    private TableColumn<Student, String> name;

    @FXML
    private Button BtnDelStu;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.stuData();

    }

    public void stuData(){

        id.setCellValueFactory(new PropertyValueFactory<Student, String>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));

        StudentModel studentModel = new StudentModel();
        TableStudent.setItems(studentModel.getStu());

    }


    public void delStu(ActionEvent e){

       String id = TableStudent.getSelectionModel().getSelectedItem().id;
       StudentModel studentModel = new StudentModel();
       Boolean result = studentModel.delStudent(id);

       TableStudent.setItems(null);
       TableStudent.setItems(studentModel.getStu());

       System.out.println(result);

    }



}
