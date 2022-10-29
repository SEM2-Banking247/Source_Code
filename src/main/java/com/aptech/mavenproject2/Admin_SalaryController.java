/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.aptech.mavenproject2;

import Entities.SalaryEntity;
import Entities.UserEntity;
import Models.Custom_Salary;
import Models.Salary;
import Models.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author Admin
 */
public class Admin_SalaryController implements Initializable {
    ArrayList<Salary> salaryList;
    ArrayList<User> userList;
    private final SalaryEntity salaryEntity = new SalaryEntity();
    private final UserEntity userEntity = new UserEntity();
    Salary salary = Salary.getInstance();
    User user = User.getInstance();
    

    @FXML
    private TableView<Custom_Salary> TvSalary;
    @FXML
    private TableColumn<Custom_Salary, String> ColAccountNumber;
    @FXML
    private TableColumn<Custom_Salary, String> ColFullName;
    @FXML
    private TableColumn<Custom_Salary, Float> ColSalary;
    @FXML
    private TableColumn<Custom_Salary, String> ColHrdate;
    @FXML
    private TableColumn<Custom_Salary, String> ColUpdate;
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
    private TextField txtSalary;
    @FXML
    private TextField txtHrDate;
    @FXML
    private Label txtUserCode;
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
        
        //show all salaries
        ShowSalaries();
    }    
    
    @FXML
    private void ShowSalaries() {
        ArrayList<Custom_Salary> custom_salaries = null;
        try {
            custom_salaries = userEntity.selectAllUsersPlusSalary();         
        } catch (SQLException ex) {
            Logger.getLogger(Admin_SalaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ColAccountNumber.setCellValueFactory(new PropertyValueFactory<>("Account_number"));
        ColFullName.setCellValueFactory(new PropertyValueFactory<>("Full_name"));
        ColSalary.setCellValueFactory(new PropertyValueFactory<>("Salary_money"));
        ColHrdate.setCellValueFactory(new PropertyValueFactory<>("Hr_date"));
        ColUpdate.setCellValueFactory(new PropertyValueFactory<>("Update_date"));
        
        TvSalary.setItems(FXCollections.observableArrayList(custom_salaries));  
        
        txtCount.setText("Result: "+(custom_salaries.size()));
    }

    @FXML
    private void Save() throws SQLException {
        Custom_Salary selected_salary = TvSalary.getSelectionModel().getSelectedItem();
        if (selected_salary.getUpdate_date()!= null) {
            //update
            salaryEntity.updateSalaryByUserID(selected_salary.getUser_id(), Float.parseFloat(txtSalary.getText()));

            //show notification
            txtNotification.setText("Salary Updated!");
        }
        else {
            //add
            Salary new_salary = new Salary();
            new_salary.setUser_id(selected_salary.getUser_id());
            new_salary.setSalary_money(Float.parseFloat(txtSalary.getText()));
            new_salary.setHr_date(txtHrDate.getText());
            salaryEntity.addSalary(new_salary);

            //show notification
            txtNotification.setText("Salary Added!");
        }
        ShowSalaries();
        clear();
    }

    @FXML
    private void Refresh() throws SQLException {
        ShowSalaries();
        clear();
    }

    @FXML
    private void Reset() {
        clear();      
    }

    @FXML
    private void Find() throws SQLException {
        String findname = txtFindname.getText();
        findname = "%"+findname+"%";
//        ArrayList<Salary> salaries = null;
//        try {
//            salaries = salaryEntity.searchSalaryByUserName(findname);         
//        } catch (SQLException ex) {
//            Logger.getLogger(Admin_SalaryController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        ArrayList<Custom_Salary> custom_salaries = new ArrayList<Custom_Salary>();
//        for(Salary salary : salaries){
//            Custom_Salary custom_Salary = new Custom_Salary();
//            
//            User user= null;
//            try {
//                user = userEntity.selectUserById(salary.getUser_id());
//            } catch (SQLException ex) {
//                Logger.getLogger(Admin_SalaryController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            custom_Salary.setSalary_id(salary.getSalary_id());
//            custom_Salary.setUser_id(salary.getUser_id());
//            custom_Salary.setAccount_number(user.getAccount_number());
//            custom_Salary.setFull_name(user.getFull_name());
//            custom_Salary.setSalary_money(String.format("%.0f",salary.getSalary_money()));
//            custom_Salary.setHr_date(salary.getHr_date());
//            custom_Salary.setUpdate_date(salary.getUpdate_date().toString());
//            custom_Salary.setCreate_date(salary.getCreate_date().toString());
//            custom_salaries.add(custom_Salary);
//        }
        ArrayList<Custom_Salary> custom_salaries = null;
        try {
            custom_salaries = userEntity.searchUsersPlusSalary(findname);         
        } catch (SQLException ex) {
            Logger.getLogger(Admin_SalaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ColAccountNumber.setCellValueFactory(new PropertyValueFactory<>("Account_number"));
        ColFullName.setCellValueFactory(new PropertyValueFactory<>("Full_name"));
        ColSalary.setCellValueFactory(new PropertyValueFactory<>("Salary_money"));
        ColHrdate.setCellValueFactory(new PropertyValueFactory<>("Hr_date"));
        ColUpdate.setCellValueFactory(new PropertyValueFactory<>("Update_date"));
        
        TvSalary.setItems(FXCollections.observableArrayList(custom_salaries));     
        
//        ColAccountNumber.setCellValueFactory(new PropertyValueFactory<>("Account_number"));
//        ColFullName.setCellValueFactory(new PropertyValueFactory<>("Full_name"));
//        ColSalary.setCellValueFactory(new PropertyValueFactory<>("Salary_money"));
//        ColHrdate.setCellValueFactory(new PropertyValueFactory<>("Hr_date"));
//        ColUpdate.setCellValueFactory(new PropertyValueFactory<>("Update_date"));
//        
//        TvSalary.setItems(FXCollections.observableArrayList(custom_salaries));     
        
        txtCount.setText("Result: "+(custom_salaries.size()));
    }

    @FXML
    private void Delete() throws SQLException {
        Custom_Salary salary_select = TvSalary.getSelectionModel().getSelectedItem();
        if(salary_select!=null){
            salaryEntity.deleteSalary(salary_select.getSalary_id());
        }
        ShowSalaries();
        clear();
    }
    
    public void ShowSalaryDetail() throws SQLException{
        Custom_Salary salary_select = TvSalary.getSelectionModel().getSelectedItem();

        txtSalary.setText(salary_select.getSalary_money());
        txtHrDate.setText(salary_select.getHr_date());
        txtUserCode.setText(salary_select.getAccount_number());
    }
    
    public void clear(){
        txtFindname.setText("");
        txtHrDate.setText("");
        txtSalary.setText("");
        txtUserCode.setText("");
        txtNotification.setText("");
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
    private void logout() throws IOException {
        App.setRoot("Login");
    }
    
    @FXML
    public void home() throws IOException{
        App.setRoot("Admin_Home");
    }
}
