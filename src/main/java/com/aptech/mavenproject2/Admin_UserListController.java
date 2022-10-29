/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.aptech.mavenproject2;

import Entities.RoleEntity;
import Entities.SalaryEntity;
import Entities.UserEntity;
import Models.Role;
import Models.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class Admin_UserListController implements Initializable {
    private final SalaryEntity salaryEntity = new SalaryEntity();
    private final UserEntity userEntity = new UserEntity();
    private final RoleEntity roleEntity = new RoleEntity();
    User user = User.getInstance();
    Role role = Role.getInstance();
    ArrayList<User> userList;
    
    @FXML
    private TableView<User> TvUserList;
    @FXML
    private TableColumn<User, Integer> ColAccountNumber;
    @FXML
    private TableColumn<User, String> ColUserName;
    @FXML
    private TableColumn<User, String> ColFullName;
    @FXML
    private TableColumn<User, String> ColAddress;
    @FXML
    private TableColumn<User, String> ColEmail;
    @FXML
    private TableColumn<User, String> ColPhone;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnRefresh;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnFind;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtPwd;
    @FXML
    private TextField txtAccountNumber;
    @FXML
    private TextField txtEmail;
    @FXML
    private ChoiceBox<String> Roles;
    @FXML
    private TextField txtFullname;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtPhonenumber;
    @FXML
    private TextField txtCCCD;
    @FXML
    private TextField txtDOB;
    @FXML
    private ToggleGroup txtGender;
    @FXML
    private RadioButton GenderMale;
    @FXML
    private RadioButton GenderFemale;
    @FXML
    private TextField txtPin;
    @FXML
    private TextField txtFindname;
    @FXML
    private Label txtFullname_header;
    @FXML
    private Label txtNotification;
    @FXML
    private Label txtCount;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //show user name
        txtFullname_header.setText("Hi, "+user.getFull_name());
        
        //insert data into role choiceBox
        ArrayList<Role> roles = null;
        try {
            roles = roleEntity.selectAllRoles();
        } catch (SQLException ex) {
            Logger.getLogger(Admin_UserListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        roles.forEach(role -> Roles.getItems().add(role.getName()));
        
        //show all accounts
        try {
            showAccounts();
        } catch (SQLException ex) {
            Logger.getLogger(Admin_UserListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    @FXML
    private void ShowUserDetail(MouseEvent event) throws SQLException {
        User user_select = TvUserList.getSelectionModel().getSelectedItem();
       
        txtAccountNumber.setText(user_select.getAccount_number());
        txtUserName.setText(user_select.getUser_name());
        txtFullname.setText(user_select.getFull_name());
        txtPhonenumber.setText(user_select.getPhone_number());
        txtEmail.setText(user_select.getEmail());
        txtCCCD.setText(user_select.getCCCD());
        txtDOB.setText(user_select.getDOB());
        txtPin.setText(user_select.getPin());
        txtPwd.setText(user_select.getPassword());
        txtAddress.setText(user_select.getAddress());
        String gender = user_select.getGender();
        if (gender.equals("Male")) {
            txtGender.selectToggle(GenderMale);
        }
        else {
            txtGender.selectToggle(GenderFemale);
        }
        Role selected_role = roleEntity.selectRoleById(user_select.getRole_id());
        Roles.setValue(selected_role.getName());
    }
    
    @FXML
    private void Save(ActionEvent event) throws SQLException, InterruptedException {
        //get branch and status data:
        String role = Roles.getValue();
        Role selected_role = roleEntity.selectRoleByName(role);
        String gender = ((Labeled)txtGender.getSelectedToggle()).getText();
        
        User update_user = new User();
        
        update_user.setRole_id(selected_role.getRole_id());
        update_user.setAccount_number(txtAccountNumber.getText()); 
        update_user.setUser_name(txtUserName.getText()); 
        update_user.setEmail(txtEmail.getText());
        update_user.setPassword(txtPwd.getText());
        update_user.setFull_name(txtFullname.getText()); 
        update_user.setAddress(txtAddress.getText());
        update_user.setPhone_number(txtPhonenumber.getText()); 
        update_user.setCCCD(txtCCCD.getText()); 
        update_user.setGender(gender);
        update_user.setCreate_at(LocalDate.now());
        update_user.setDOB(txtDOB.getText());
        update_user.setPin(txtPin.getText());
        update_user.setUpdate_at(LocalDate.now()); 
        
        User selected_user = TvUserList.getSelectionModel().getSelectedItem();
        //if the user select an account from the table, its update, otherwise its add.
        if (selected_user != null) {
            update_user.setUser_id(selected_user.getUser_id());
            
            //update user info
            userEntity.updateUserInfor(update_user);
            //update user pin
            userEntity.updatePinNumber(update_user.getPin(), update_user.getUser_id());
            //update password
            userEntity.updatePassword(update_user.getPassword(), update_user.getUser_id());
            txtNotification.setText("Account Updated!");
            Refresh();
        }
        else {
            //add user
            userEntity.addUser(update_user);
            
            //show notification
            txtNotification.setText("Account Added!");
            Refresh();
        }
    }
    
    @FXML
    private void Refresh() throws SQLException {
        showAccounts();
        clear();
    }
    
    @FXML
    private void Reset(ActionEvent event) {
        clear();
    }
    
    @FXML
    private void Find(ActionEvent event) throws SQLException {
        String findname = txtFindname.getText();
        findname = "%"+findname+"%";
        ArrayList<User>  userList = userEntity.searchUserByName(findname);
        
        ColAccountNumber.setCellValueFactory(new PropertyValueFactory<>("Account_number"));
        ColUserName.setCellValueFactory(new PropertyValueFactory<>("User_name"));
        ColFullName.setCellValueFactory(new PropertyValueFactory<>("full_name"));
        ColAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        ColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ColPhone.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        
        TvUserList.setItems(FXCollections.observableArrayList(userList));

        txtCount.setText("Result: "+(userList.size()));
    }
    
    @FXML
    private void Delete(ActionEvent event) throws SQLException {
       User user_select = TvUserList.getSelectionModel().getSelectedItem();
       if( user_select!=null){
           userEntity.deleteUser(user_select.getUser_id());
       }
       showAccounts();
       clear();
        
    }
    
    public void showAccounts() throws SQLException {
        ArrayList<User> data = userEntity.selectAllUsers();

        ColAccountNumber.setCellValueFactory(new PropertyValueFactory<>("Account_number"));
        ColUserName.setCellValueFactory(new PropertyValueFactory<>("User_name"));
        ColFullName.setCellValueFactory(new PropertyValueFactory<>("full_name"));
        ColAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        ColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ColPhone.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        
        TvUserList.setItems(FXCollections.observableArrayList(data));
        
        txtCount.setText("Result: "+(data.size()));
    }
    
    public void clear() {
        txtFindname.setText("");
        txtAccountNumber.setText("");
        txtUserName.setText("");
        txtFullname.setText("");
        txtEmail.setText("");
        txtPhonenumber.setText("");
        txtCCCD.setText("");
        txtDOB.setText("");
        txtPin.setText("");
        txtPwd.setText("");
        txtAddress.setText("");
    }
    
    public void getRandomAccountNumber(){
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        txtAccountNumber.setText(String.format("%06d", number));
    }
    
    @FXML
    private void logout() throws IOException {
        App.setRoot("Login");
    }
    
    @FXML
    public void getRandomPin(){
        Random rnd = new Random();
        int number = rnd.nextInt(9999);
        txtPin.setText(String.format("%04d", number));
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


