/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aptech.mavenproject2;

import Entities.BranchEntity;
import Entities.CardEntity;
import Entities.RoleEntity;
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
public class Customer_ChangePIN2Controller implements Initializable{
    private final BranchEntity branchEntity = new BranchEntity();
    private final RoleEntity roleEntity = new RoleEntity();
    private final UserEntity userEntity = new UserEntity();
    private final CardEntity cardEntity = new CardEntity();
    User user = User.getInstance();
    
    @FXML
    private Label txtFullname_header;
    
    @FXML
    private TextField txtOldPIN;
    
    @FXML
    private TextField txtNewPIN;
    
    @FXML
    private TextField txtVerifyNewPIN;
    
    @FXML
    private Label txtNotification;
    
    @FXML
    private Label ValidateOldPIN;
    
    @FXML
    private Label ValidateNewPIN;
    
    @FXML
    private Label ValidateConfirmPIN;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtFullname_header.setText("Hi, "+user.getFull_name());
    }  
    
    @FXML
    private void showBranches() throws IOException {
        App.setRoot("Customer_Branches");
    }
    
    public int changePIN() throws SQLException{
        ValidateConfirmPIN.setText("");
        ValidateNewPIN.setText("");
        ValidateOldPIN.setText("");
        txtNotification.setText("");
        
        int validate = 0;
        String oldPIN = txtOldPIN.getText();
        String newPIN = txtNewPIN.getText();
        String verifyPin = txtVerifyNewPIN.getText();
        
        //validate Old PIN
        if (txtOldPIN.getText().equals("")) {
            ValidateOldPIN.setText("PIN cannot be empty. Please try again.");
            validate = 0;
        }
        
        Pattern pPIN =Pattern.compile("^\\d{4}$");
        if (!txtOldPIN.getText().equals("") && !pPIN.matcher(txtOldPIN.getText()).find()){
            ValidateOldPIN.setText("Wrong format for PIN. Please try again.");
            validate = 0;
        } 
        
        //validate PIN
        if (txtNewPIN.getText().equals("")) {
            ValidateNewPIN.setText("PIN cannot be empty. Please try again.");
            validate = 0;
        }
        
        if (!txtNewPIN.getText().equals("") && !pPIN.matcher(txtNewPIN.getText()).find()){
            ValidateNewPIN.setText("Wrong format for PIN. Please try again.");
            validate = 0;
        } 
        
        //validate PIN
        if (txtVerifyNewPIN.getText().equals("")) {
            ValidateConfirmPIN.setText("PIN cannot be empty. Please try again.");
            validate = 0;
        }
        
        if (!txtVerifyNewPIN.getText().equals("") && !pPIN.matcher(txtVerifyNewPIN.getText()).find()){
            ValidateConfirmPIN.setText("Wrong format for PIN. Please try again.");
            validate = 0;
        } 
        
        if (validate == 0) {
            return 0;
        }
        
        if (oldPIN.equals(user.getPin()) && newPIN.equals(verifyPin)) {
            userEntity.updatePinNumber(newPIN, user.getUser_id());
            txtNotification.setText("New PIN number updated!Please dont disclose this information to others.");
        }
        else {
            txtNotification.setText("Errol!");
        }
        return 1;
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
