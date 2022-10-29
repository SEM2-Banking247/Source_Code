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
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author tuan
 */
public class Employee_EditInforController implements Initializable{
    private final UserEntity userEntity = new UserEntity();
    User user = User.getInstance();
    
    @FXML
    private Label txtFullname_header;
        
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
    private TextField txtDOB;
    
    @FXML 
    private Label txtNotification;
    
    @FXML 
    private RadioButton male;
    
    @FXML
    private RadioButton female;
    
    @FXML 
    private Label ValidateUserName;
    
    @FXML 
    private Label ValidateFullName;
    
    @FXML 
    private Label ValidateEmail;
    
    @FXML 
    private Label ValidateAddress;
    
    @FXML 
    private Label ValidateCCCD;
    
    @FXML 
    private Label ValidatePhone;
    
    @FXML 
    private Label ValidateDOB;
    
    @FXML 
    private Label ValidateGender;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //add employees infor
        txtFullname_header.setText("Hi, "+user.getFull_name());
        txtUserName.setText(user.getUser_name());
        txtFullname.setText(user.getFull_name());
        txtEmail.setText(user.getEmail());
        txtAddress.setText(user.getAddress());
        txtPhone.setText(user.getPhone_number());
        txtCCCD.setText(user.getCCCD());
        txtDOB.setText(user.getDOB());
        
        if (user.getGender().equals("Male")) {
            male.setSelected(true);
        }
        else {
            female.setSelected(true);
        }
    }    
    
    @FXML
    public int updateUser() throws IOException, SQLException{
        int validate = 1;   
        txtNotification.setText("");
        ValidateAddress.setText("");
        ValidateCCCD.setText("");
        ValidateDOB.setText("");
        ValidateEmail.setText("");
        ValidateFullName.setText("");
        ValidateGender.setText("");
        ValidatePhone.setText("");
        ValidateUserName.setText("");
        String gender = ((Labeled)txtGender.getSelectedToggle()).getText();
        
                
        //validate Full name
        if (txtFullname.getText().equals("")) {
            ValidateFullName.setText("Full name cannot be empty. Please try again.");
            validate = 0;
        }
        
        Pattern pFullName =Pattern.compile("^[a-zA-Z ]+$");
        if (!txtFullname.getText().equals("") && !pFullName.matcher(txtFullname.getText()).find()){
            ValidateFullName.setText("Full name only contains letters. Please try again.");
            validate = 0;
        } 
        
        //validate User name
        if (txtUserName.getText().equals("")) {
            ValidateUserName.setText("Username cannot be empty. Please try again.");
            validate = 0;
        }
        
        Pattern pUserName =Pattern.compile("^[a-zA-Z0-9]+$");
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
        if (gender == null) {
            ValidateGender.setText("Gender cannot be empty. Please try again.");
            validate = 0;
        }
        
        if (!gender.equals("Male") && !gender.equals("Female")){
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
        
        if (validate ==0) {
            return 0;
        }
        
        user.setFull_name(txtFullname.getText()); 
        user.setUser_name(txtUserName.getText());  
        user.setEmail(txtEmail.getText()); 
        user.setAddress(txtAddress.getText()); 
        user.setPhone_number(txtPhone.getText());
        user.setCCCD(txtCCCD.getText()); 
        user.setGender(gender); 
        user.setDOB(txtDOB.getText()); 
        userEntity.updateUserInfor(user);
        
        txtNotification.setText("Update successfull");
        txtFullname_header.setText("Hi, " + user.getFull_name());
        App.setRoot("Employee_Infor");
        return 1;
    }
    
    @FXML
    private void cancelEdit() throws IOException {
        App.setRoot("Employee_Infor");
    }
    
    @FXML
    private void logout() throws IOException {
        App.setRoot("Login");
    }
    
    @FXML
    private void showInfor() throws IOException{
        App.setRoot("Employee_Infor");
    }
    
    @FXML
    public void cardManager() throws IOException{
        App.setRoot("Employee_CardList");
    }
    
    @FXML
    public void userManager() throws IOException {
        App.setRoot("Employee_UserList");
    }
    
    @FXML
    public void TransactionManager() throws IOException{
        App.setRoot("Employee_TransactionHistory");
    }
    
    @FXML
    public void FeedbackManager() throws IOException{
        App.setRoot("Employee_FeedbackList");
    }
    
    @FXML
    public void home() throws IOException{
        App.setRoot("Employee_Home");
    }
}
