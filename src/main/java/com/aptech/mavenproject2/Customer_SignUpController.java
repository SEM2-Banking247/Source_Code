/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aptech.mavenproject2;

import Entities.UserEntity;
import Models.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author tuan
 */
public class Customer_SignUpController implements Initializable{
    private final UserEntity userEntity = new UserEntity();
    
    @FXML
    private TextField txtUserName;
    
    @FXML
    private TextField txtFullname;
    
    @FXML
    private TextField txtEmail;
    
    @FXML
    private TextField txtAddress;
    
    @FXML
    private TextField txtPhone;
    
    @FXML
    private TextField txtCCCD;
    
    @FXML
    private ToggleGroup txtGender;
    
    @FXML 
    private Label txtNotification;
    
    @FXML
    private TextField txtDOB;
    
    @FXML
    private TextField txtPassword;
    
    @FXML
    private TextField txtVerifyPassword;
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    @FXML
    public void addUser() throws IOException, SQLException, InterruptedException{
        String gender = ((Labeled)txtGender.getSelectedToggle()).getText();
        
        User user = new User();
        user.setRole_id(3);
        user.setUser_name(txtUserName.getText());  
        user.setEmail(txtEmail.getText()); 
        user.setPassword(txtPassword.getText());
        user.setFull_name(txtFullname.getText()); 
        user.setAddress(txtAddress.getText()); 
        user.setPhone_number(txtPhone.getText());
        user.setCCCD(txtCCCD.getText()); 
        user.setGender(gender); 
        user.setDOB(txtDOB.getText()); 
        userEntity.addUser(user);
        
        txtNotification.setText("Account created successfull");
        
        Thread.sleep(3000);
        App.setRoot("Login");
    }
    
    @FXML
    public void reset(){
        txtAddress.setText("");
        txtCCCD.setText("");
        txtDOB.setText("");
        txtEmail.setText("");
        txtFullname.setText("");
        txtGender.getSelectedToggle().setSelected(false);
        txtNotification.setText("");
        txtPassword.setText("");
        txtPhone.setText("");
        txtUserName.setText("");
        txtVerifyPassword.setText("");
    }
        
    @FXML
    private void cancelSignUp() throws IOException {
        App.setRoot("Login");
    }
 
    
}
