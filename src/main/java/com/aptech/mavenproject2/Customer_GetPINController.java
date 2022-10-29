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
public class Customer_GetPINController implements Initializable{
    private final BranchEntity branchEntity = new BranchEntity();
    private final RoleEntity roleEntity = new RoleEntity();
    private final UserEntity userEntity = new UserEntity();
    private final CardEntity cardEntity = new CardEntity();
    User user = User.getInstance();
    
    @FXML
    private Label txtFullname_header;
    
    @FXML
    private TextField txtEmail;
    
    @FXML
    private TextField txtPassword;
    
    @FXML
    private TextField txtDOB;
    
    @FXML 
    private Label txtNotification;
    
    @FXML 
    private Label ValidateEmail;
    
    @FXML 
    private Label ValidatePassword;
    
    @FXML 
    private Label ValidateDOB;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtFullname_header.setText("Hi, "+user.getFull_name());
    }    
    
    @FXML
    private void showBranches() throws IOException {
        App.setRoot("Customer_Branches");
    }
    
    @FXML
    public int verify() throws IOException{
        ValidateDOB.setText("");
        ValidateEmail.setText("");
        ValidatePassword.setText("");
        txtNotification.setText("");
        
        int validate = 0;
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String DOB = txtDOB.getText();
        
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
        
        //validate password
        if (txtPassword.getText().equals("")) {
            ValidatePassword.setText("Password cannot be empty. Please try again.");
            validate = 0;
        }
        
        Pattern pPassword =Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
        if (!txtPassword.getText().equals("") && !pPassword.matcher(txtPassword.getText()).find()){
            ValidatePassword.setText("Wrong format for Password. Please try again.");
            validate = 0;
        }
        
        //validate DOB
        if (txtDOB.getText().equals("")) {
            ValidateDOB.setText("DOB cannot be empty. Please try again.");
            validate = 0;
        }
        
        Pattern pDOB =Pattern.compile("^[0-9]{4}\\-[0-9]{2}\\-[0-9]{2}$");
        if (!txtDOB.getText().equals("") && !pDOB.matcher(txtDOB.getText()).find()){
            ValidateDOB.setText("DOB format is YYYY-MM-DD. Please try again.");
            validate = 0;
        } 
        
        if (validate == 0) {
            return 0;
        }
        
        //show PIN
        if (email.equals(user.getEmail()) && password.equals(user.getPassword()) && DOB.equals(user.getDOB())) {
            txtNotification.setText(user.getPin());
        }
        else {
            txtNotification.setText("Wrong Username/Password!");
        }      
        return 1;
    }
    
    @FXML
    public void reset(){
        txtEmail.setText("");
        txtDOB.setText("");
        txtPassword.setText("");
        txtNotification.setText("");
        ValidateDOB.setText("");
        ValidateEmail.setText("");
        ValidatePassword.setText("");
    }
    
    @FXML
    private void logout() throws IOException {
        App.setRoot("Login");
    }
    
    @FXML
    private void home() throws IOException {
        App.setRoot("Customer_Infor");
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
    
    @FXML
    public void returnhome() throws IOException{
        App.setRoot("Customer_Home");
    }
}
