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
public class Customer_HomeController implements Initializable{
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
    private void withdraw() throws IOException {
        App.setRoot("Customer_Withdraw");
    }
    
    @FXML
    private void transfer() throws IOException {
        App.setRoot("Customer_Transfer");
    }
    
    @FXML
    private void showHistory() throws IOException {
        App.setRoot("Customer_History");
    }
    
    @FXML
    private void showBranches() throws IOException {
        App.setRoot("Customer_Branches");
    }
    
    @FXML
    private void showInfor() throws IOException {
        App.setRoot("Customer_Infor");
    }
    
    @FXML
    private void report() throws IOException {
        App.setRoot("Customer_Report");
    }
    
    @FXML
    public void home() throws IOException{
        App.setRoot("Customer_Home");
    }
    
}
