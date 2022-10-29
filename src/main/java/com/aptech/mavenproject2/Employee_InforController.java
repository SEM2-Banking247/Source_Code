/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aptech.mavenproject2;

import Entities.RoleEntity;
import Entities.SalaryEntity;
import Entities.UserEntity;
import Models.Salary;
import Models.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

/**
 *
 * @author tuan
 */
public class Employee_InforController implements Initializable{
    private final RoleEntity roleEntity = new RoleEntity();
    private final UserEntity userEntity = new UserEntity();
    private final SalaryEntity salaryEntity = new SalaryEntity();
    User user = User.getInstance();
    
    
    @FXML
    private Label txtFullname_header;
    
    @FXML
    private Label txtAccountNumber;
    
    @FXML
    private Label txtUserName;
    
    @FXML
    private Label txtFullname;
    
    @FXML
    private Label txtEmail;
    
    @FXML
    private Label txtAddress;
    
    @FXML
    private Label txtPhone;
    
    @FXML
    private Label txtCCCD;
    
    @FXML
    private Label txtGender;
    
    @FXML
    private Label txtDOB; 
    
    @FXML 
    private PasswordField txtPassword;
    
    @FXML
    private Label txtSalary;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //show employee's infor
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
    private void editUser() throws IOException{
        App.setRoot("Employee_EditInfor");
    }
    
    @FXML
    private void logout() throws IOException {
        App.setRoot("Login");
    }
    
    @FXML
    public void home() throws IOException{
        App.setRoot("Employee_Home");
    }
    
    @FXML 
    private int showSalary() throws SQLException{
        String password = txtPassword.getText();
        
        //validate password
        if (txtPassword.getText().equals("")) {
            txtSalary.setText("Password cannot be empty.");
            return 0;
        }
        
        Pattern pPassword =Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
        if (!txtPassword.getText().equals("") && !pPassword.matcher(txtPassword.getText()).find()){
            txtSalary.setText("Wrong format for Password.");
            return 0;
        } 
        
        if (user.getPassword().equals(password)) {
            Salary salary = salaryEntity.selectSalaryByUserID(user.getUser_id());
            txtSalary.setText(Float.toString(salary.getSalary_money()));
        }
        else {
            txtSalary.setText("Wrong password!");
        }
        return 1;
    }
    
    @FXML
    private void hideSalary(){
        txtPassword.setText("");
        txtSalary.setText("");
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
    public void changePassword() throws IOException{
        App.setRoot("Employee_EditPassword");
    }
    
    @FXML
    public void FeedbackManager() throws IOException{
        App.setRoot("Employee_FeedbackList");
    }
}
