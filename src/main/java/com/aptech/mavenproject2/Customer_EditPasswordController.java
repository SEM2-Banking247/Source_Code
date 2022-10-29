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
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author tuan
 */
public class Customer_EditPasswordController implements Initializable{
    private final UserEntity userEntity = new UserEntity();
    User user = User.getInstance();
    
    @FXML
    private Label txtFullname_header;
    
    @FXML
    private TextField txtEmail;
    
    @FXML 
    private Label txtNotification;

    @FXML
    private TextField txtPIN;
    
    @FXML
    private TextField txtOldPassword;
    
    @FXML
    private TextField txtNewPassword;
        
    @FXML
    private TextField txtConfirmPassword;
    
    @FXML
    private Label ValidateEmail;
    
    @FXML
    private Label ValidatePIN;
    
    @FXML
    private Label ValidateOldPassword;
    
    @FXML
    private Label ValidateNewPassword;
    
    @FXML
    private Label ValidateConfirmPassword;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //add employees infor
        txtFullname_header.setText("Hi, "+user.getFull_name());
    }    
    
    @FXML
    private void showBranches() throws IOException {
        App.setRoot("Customer_Branches");
    }
    
    @FXML
    public int updatePassword() throws IOException, SQLException{
        ValidateConfirmPassword.setText("");
        ValidateEmail.setText("");
        ValidateNewPassword.setText("");
        ValidateOldPassword.setText("");
        ValidatePIN.setText("");
        txtNotification.setText("");
        int validate = 1;
        
        //validate email
        if (txtEmail.getText().equals("")) {
            ValidateEmail.setText("Email cannot be empty. Please try again.");
            validate = 0;
        }
        
        Pattern pEmail = Pattern.compile("^\\S+@\\S+\\.\\S+$");
        if (!txtEmail.getText().equals("") && !pEmail.matcher(txtEmail.getText()).find()){
            ValidateEmail.setText("Wrong format for Email. Please try again.");
            validate = 0;
        } 
        
        //validate PIN
        if (txtPIN.getText().equals("")) {
            ValidatePIN.setText("PIN cannot be empty. Please try again.");
            validate = 0;
        }
        
        Pattern pPIN =Pattern.compile("^\\d{4}$");
        if (!txtPIN.getText().equals("") && !pPIN.matcher(txtPIN.getText()).find()){
            ValidatePIN.setText("Wrong format for PIN. Please try again.");
            validate = 0;
        } 
        
        //validate old password
        if (txtOldPassword.getText().equals("")) {
            ValidateOldPassword.setText("Password cannot be empty. Please try again.");
            validate = 0;
        }
        
        Pattern pPassword =Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
        if (!txtOldPassword.getText().equals("") && !pPassword.matcher(txtOldPassword.getText()).find()){
            ValidateOldPassword.setText("Wrong format for Password. Please try again.");
            validate = 0;
        } 
        
        //validate new password
        if (txtNewPassword.getText().equals("")) {
            ValidateNewPassword.setText("Password cannot be empty. Please try again.");
            validate = 0;
        }
        
        if (!txtNewPassword.getText().equals("") && !pPassword.matcher(txtNewPassword.getText()).find()){
            ValidateNewPassword.setText("Wrong format for Password. Please try again.");
            validate = 0;
        } 
        
        //validate confirm password
        if (txtConfirmPassword.getText().equals("")) {
            ValidateConfirmPassword.setText("Password cannot be empty. Please try again.");
            validate = 0;
        }
        
        if (!txtConfirmPassword.getText().equals("") && !pPassword.matcher(txtConfirmPassword.getText()).find()){
            ValidateConfirmPassword.setText("Wrong format for Password. Please try again.");
            validate = 0;
        } 
        
        if(validate == 0){
            return 0;
        }
        
        //update password.
        if (txtNewPassword.getText().equals(txtConfirmPassword.getText())) {
            userEntity.updatePassword(txtNewPassword.getText(), user.getUser_id());
            txtNotification.setText("Password Updated");
        }
        else {
            txtNotification.setText("Wrong confirm new password! Please try again.");
            return 0;
        }
        return 1;
    }
    
    @FXML
    private void cancelEdit() throws IOException {
        App.setRoot("Customer_Infor");
    }
    
    @FXML
    private void logout() throws IOException {
        App.setRoot("Login");
    }
    
    @FXML
    private void home() throws IOException {
        App.setRoot("Customer_Home");
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
    private void showInfor() throws IOException {
        App.setRoot("Customer_Infor");
    }
    
    @FXML
    private void report() throws IOException {
        App.setRoot("Customer_Report");
    }
    
    @FXML
    private void withdraw() throws IOException {
        App.setRoot("Customer_Withdraw");
    }
}
