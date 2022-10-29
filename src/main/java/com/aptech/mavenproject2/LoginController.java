/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aptech.mavenproject2;

import Entities.UserEntity;
import Models.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author WINDOWS
 */
public class LoginController implements Initializable {
    private final UserEntity userEntity = new UserEntity();
    User user = User.getInstance();

    @FXML
    private Label af2;
    
    @FXML
    private Label af1;

    @FXML
    private Button btnFSearch;
    
    @FXML
    private TextField txtFUser;
    
    @FXML
    private Label a1;

    @FXML
    private Label a2;
    
    @FXML
    private Button btnok;
    
    @FXML
    private Button btnForgot;

    @FXML
    private PasswordField txtPass;

    @FXML
    private TextField txtUser;

    @FXML
    private Label af4;

    @FXML
    private Label af5;

    @FXML
    private Button btnFBack;

    @FXML
    private Button btnFGet;
    
    @FXML
    private Label af3;
    
    @FXML
    private Label txtFName;
        
    @FXML
    private Label txtFPass;

    @FXML
    private TextField txtFPin;
        
    @FXML 
    private Label txtNotification;
    
    @FXML 
    private Button SignUp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        af1.setVisible(false);
        txtFUser.setVisible(false);
        btnFSearch.setVisible(false);
        af2.setVisible(false);
        af4.setVisible(false);
        af5.setVisible(false);
        btnFGet.setVisible(false);
        btnFBack.setVisible(false);
        txtFPin.setVisible(false);
        txtFPass.setVisible(false);
        af3.setVisible(false);
        txtFName.setVisible(false);
    }
    
    @FXML
    public int Login(ActionEvent event) throws SQLException, IOException {
        int validate = 1;
        String username = txtUser.getText();
        String pass = txtPass.getText();
        
        // validate username
        if (username.equals("") || pass.equals("")) {
            txtNotification.setText("Empty Username/Password. Please try again.");
            return 0;
        }
        Pattern p =Pattern.compile("^[a-z]+$");
        if (!username.equals("") && !p.matcher(username).find()){
            txtNotification.setText("Wrong format username. Please try again.");
            validate = 0;
        }
        
        //validate password 
        Pattern pPassword = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
        if (!pPassword.matcher(pass).find()){
            txtNotification.setText("Wrong format password. Please try again.");
            validate = 0;
        }
        
        if (validate == 0) {
            return 0;
        }
        
        User result = userEntity.signIn(username, pass);
        user.setAddress(result.getAddress());
        user.setCCCD(result.getCCCD());
        user.setCreate_at(result.getCreate_at());
        user.setDOB(result.getDOB());
        user.setEmail(result.getEmail());
        user.setFull_name(result.getFull_name());
        user.setGender(result.getGender());
        user.setPassword(result.getPassword());
        user.setPhone_number(result.getPhone_number());
        user.setRole_id(result.getRole_id());
        user.setUpdate_at(result.getUpdate_at());
        user.setAccount_number(result.getAccount_number());
        user.setUser_name(result.getUser_name());
        user.setUser_id(result.getUser_id());
        user.setPin(result.getPin());
        
        switch (user.getRole_id()) {
            case 3:
                App.setRoot("Customer_Home");
                break;
            case 2:
                //employee
                App.setRoot("Employee_Home");
                break;
            case 1:
                //admin
                App.setRoot("Admin_Home");
                break;
            default:
                txtNotification.setText("Wrong Username/Password!");
                break;
        }
        return 1;
    }
    

    @FXML
    void Forgot(ActionEvent event) {
        txtUser.setVisible(false);
        txtPass.setVisible(false);
        a1.setVisible(false);
        a2.setVisible(false);
        btnForgot.setVisible(false);
        btnok.setVisible(false);
        SignUp.setVisible(false);
        txtNotification.setText("");
        
        af1.setVisible(true);
        btnFSearch.setVisible(true);
        txtFUser.setVisible(true);
        af2.setVisible(true);
        af4.setVisible(false);
        af5.setVisible(false);
        btnFGet.setVisible(false);
        btnFBack.setVisible(true);
        txtFPin.setVisible(false);
        txtFPass.setVisible(false);
        af3.setVisible(false);
        txtFName.setVisible(false);
    }
    
    @FXML
    public int Search(ActionEvent event) throws SQLException {
        String fuser = txtFUser.getText();
        int validate = 1;
        
        //validate email
        if (fuser.equals("")) {
            txtNotification.setText("Empty Email. Please try again.");
            validate = 0;
        }
        Pattern pEmail =Pattern.compile("^\\S+@\\S+\\.\\S+$");
        if (!fuser.equals("") && !pEmail.matcher(fuser).find()){
            txtNotification.setText("Wrong format email. Please try again.");
            validate = 0;
        }  
        
        if (validate == 0) {
            return 0;
        }
        txtNotification.setText("");
        User selected_user = userEntity.selectUserByEmail(fuser);
        
        if (!selected_user.getAccount_number().equals("")) {
            txtFName.setText(selected_user.getFull_name());
            af3.setVisible(true);
            af4.setVisible(true);
            txtFName.setVisible(true);
            txtFPin.setVisible(true);
            btnFGet.setVisible(true);
        }
        return 1;
    }
    
    @FXML
    void Back(ActionEvent event) {
        txtUser.setVisible(true);
        txtPass.setVisible(true);
        a1.setVisible(true);
        a2.setVisible(true);
        btnForgot.setVisible(true);
        btnok.setVisible(true);
        txtNotification.setVisible(true);
        SignUp.setVisible(true);
        
        af1.setVisible(false);
        btnFSearch.setVisible(false);
        txtFUser.setVisible(false);
        af2.setVisible(false);    
        af4.setVisible(false);
        af5.setVisible(false);
        btnFGet.setVisible(false);
        btnFBack.setVisible(false);
        txtFPin.setVisible(false);
        txtFPass.setVisible(false);
        af3.setVisible(false);
        txtFName.setVisible(false);
    }
    
    @FXML
    public void Get(ActionEvent event) throws SQLException {
        String fuser = txtFUser.getText();
        
        User selected_user = userEntity.selectUserByEmail(fuser);
        String PIN = txtFPin.getText();
        if (PIN.equals(selected_user.getPin())) {
            txtFPass.setVisible(true);
            af5.setVisible(true);
            txtFPass.setText(selected_user.getPassword());
        }
        else {
            txtNotification.setText("Wrong Pin!");
        }   
    }
    
    @FXML 
    public void SignUp() throws IOException {
        App.setRoot("Customer_SignUp");
    }
    
}
