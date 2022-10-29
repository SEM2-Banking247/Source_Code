/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aptech.mavenproject2;

import Entities.RoleEntity;
import Entities.UserEntity;
import Models.Role;
import Models.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author tuan
 */
public class Customer_EditInforController implements Initializable{
    private final RoleEntity roleEntity = new RoleEntity();
    private final UserEntity userEntity = new UserEntity();
    User user = User.getInstance();
    
    @FXML
    public Label txtFullname_header;
        
    @FXML
    public Label txtAccountNumber;
    
    @FXML
    public TextField txtUserName;
    
    @FXML
    public TextField txtFullname;
    
    @FXML
    public TextField txtEmail;
    
    @FXML
    public TextField txtAddress;
    
    @FXML
    public TextField txtPhone;
    
    @FXML
    public TextField txtCCCD;
    
    @FXML
    public TextField txtGender;
    
    @FXML
    public TextField txtDOB;
    
    @FXML
    public Label txtRole;
    
    @FXML 
    public Label txtNotification;
    
    @FXML
    public Label ValidateFullname;
    
    @FXML
    public Label ValidateUserName;
    
    @FXML
    public Label ValidateEmail;
    
    @FXML
    public Label ValidateAddress;
    
    @FXML
    public Label ValidatePhone;
    
    @FXML
    public Label ValidateCCCD;
    
    @FXML
    public Label ValidateDOB;
    
    @FXML
    public Label ValidateGender;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Role role = null;
        try {
            role = roleEntity.selectRoleById(user.getRole_id());
        } catch (SQLException ex) {
            Logger.getLogger(Customer_EditInforController.class.getName()).log(Level.SEVERE, null, ex);
        }
  
        if (role != null) {
            txtRole.setText(role.getName());
        }
        txtFullname_header.setText("Hi, "+user.getFull_name());
        txtAccountNumber.setText(user.getAccount_number());
        txtUserName.setText(user.getUser_name());
        txtFullname.setText(user.getFull_name());
        txtEmail.setText(user.getEmail());
        txtAddress.setText(user.getAddress());
        txtPhone.setText(user.getPhone_number());
        txtCCCD.setText(user.getCCCD());
        txtGender.setText(user.getGender());
        txtDOB.setText(user.getDOB());
    }    
    
    @FXML
    private void showBranches() throws IOException {
        App.setRoot("Customer_Branches");
    }
    
    @FXML
    public int updateUser() throws IOException, SQLException{
        int validate = 1;   
        txtNotification.setText("");
        ValidateAddress.setText("");
        ValidateCCCD.setText("");
        ValidateDOB.setText("");
        ValidateEmail.setText("");
        ValidateFullname.setText("");
        ValidateGender.setText("");
        ValidatePhone.setText("");
        ValidateUserName.setText("");
        
        //validate Full name
        if (txtFullname.getText().equals("")) {
            ValidateFullname.setText("Full name cannot be empty. Please try again.");
            validate = 0;
        }
        
        Pattern pFullName =Pattern.compile("^[a-zA-Z ]+$");
        if (!txtFullname.getText().equals("") && !pFullName.matcher(txtFullname.getText()).find()){
            ValidateFullname.setText("Full name only contains letters. Please try again.");
            validate = 0;
        } 
        
        //validate User name
        if (txtUserName.getText().equals("")) {
            ValidateUserName.setText("Username cannot be empty. Please try again.");
            validate = 0;
        }
        
        Pattern pUserName =Pattern.compile("^[a-z0-9]+$");
        if (!txtUserName.getText().equals("") && !pUserName.matcher(txtUserName.getText()).find()){
            ValidateUserName.setText("Wrong format for Username. Please try again.");
            validate = 0;
        } 
        
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
        
        //validate Address
        if (txtAddress.getText().equals("")) {
            ValidateAddress.setText("Address cannot be empty. Please try again.");
            validate = 0;
        }
        
        Pattern pAddress =Pattern.compile("^[a-zA-Z0-9,.]+$");
        if (!txtAddress.getText().equals("") && !pAddress.matcher(txtAddress.getText()).find()){
            ValidateAddress.setText("Wrong format for Address. Please try again.");
            validate = 0;
        } 
        
        //validate Phone number
        if (txtPhone.getText().equals("")) {
            ValidatePhone.setText("Phone number cannot be empty. Please try again.");
            validate = 0;
        }
        
        Pattern pPhone =Pattern.compile("^\\+?[0-9][0-9]{7,14}$");
        if (!txtPhone.getText().equals("") && !pPhone.matcher(txtPhone.getText()).find()){
            ValidatePhone.setText("Wrong format for Phone number. Please try again.");
            validate = 0;
        } 
        
        //validate CCCD
        if (txtCCCD.getText().equals("")) {
            ValidateCCCD.setText("CCCD cannot be empty. Please try again.");
            validate = 0;
        }
        
        Pattern pCCCD =Pattern.compile("^\\d{7,14}$");
        if (!txtCCCD.getText().equals("") && !pCCCD.matcher(txtCCCD.getText()).find()){
            ValidateCCCD.setText("Wrong format for CCCD. Please try again.");
            validate = 0;
        } 
        
        //validate gender
        if (txtGender.getText().equals("")) {
            ValidateGender.setText("Gender cannot be empty. Please try again.");
            validate = 0;
        }
        
        if (!txtGender.getText().equals("Male") && !txtCCCD.getText().equals("Female")){
            ValidateGender.setText("Genders are Male and Female. Please try again.");
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
        
        //only update if all the fields are in correct format
        if (validate == 0) {
            return 0;
        }
        
        //update user
        user.setFull_name(txtFullname.getText()); 
        user.setUser_name(txtUserName.getText());  
        user.setEmail(txtEmail.getText()); 
        user.setAddress(txtAddress.getText()); 
        user.setPhone_number(txtPhone.getText());
        user.setCCCD(txtCCCD.getText()); 
        user.setGender(txtGender.getText()); 
        user.setDOB(txtDOB.getText()); 
        userEntity.updateUserInfor(user);
        
        txtNotification.setText("Update successfull");
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
    public void home() throws IOException{
        App.setRoot("Customer_Home");
    }
    
}
