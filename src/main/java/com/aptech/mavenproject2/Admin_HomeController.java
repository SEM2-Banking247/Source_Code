package com.aptech.mavenproject2;

import Entities.UserEntity;
import Models.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tuan
 */
public class Admin_HomeController implements Initializable{
    private final UserEntity userEntity = new UserEntity();
    User user = User.getInstance();
    
    @FXML
    public Label txtFullname_header;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtFullname_header.setText("Hi, "+user.getFull_name());
    }
    
    @FXML
    private void logout() throws IOException {
        App.setRoot("Login");
    }
    
    @FXML
    public void salaryManager() throws IOException{
        App.setRoot("Admin_Salary");
    }
    
    @FXML
    public void cardManager() throws IOException{
        App.setRoot("Admin_CardList");
    }
    
    @FXML
    public void branchManager() throws IOException {
        App.setRoot("Admin_BranchList");
    }
    
    @FXML
    public void userManager() throws IOException {
        App.setRoot("Admin_UserList");
    }
    
    @FXML
    public void home() throws IOException{
        App.setRoot("Admin_Home");
    }
}
