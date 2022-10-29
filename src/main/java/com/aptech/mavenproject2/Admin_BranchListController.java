/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aptech.mavenproject2;

import Entities.BranchEntity;
import Entities.UserEntity;
import Models.Branch;
import Models.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author tuan
 */
public class Admin_BranchListController implements Initializable {
    private final UserEntity userEntity = new UserEntity();
    private final BranchEntity branchEntity = new BranchEntity();
    ArrayList<Branch> branchList;
    int position = -1;
    Branch branch = Branch.getInstance();
    User user = User.getInstance();
        
    @FXML
    private TableView<Branch> TvBranchList;
    @FXML
    private TableColumn<Branch, Integer> NumberColmn;
    @FXML
    private TableColumn<Branch,String> BranchColmn;
    @FXML
    private TableColumn<Branch,String> AddressColmn;
    @FXML
    private TableColumn<Branch,LocalDate> Create_atColmn;
    @FXML
    private TableColumn<Branch,LocalDate> Update_atColmn;
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
    private TextField txtBranchName;
    @FXML
    private TextField txtAddress;
    @FXML
    private Label txtFullname_header;
    @FXML
    private TextField txtPhone;
    @FXML
    private Label txtCount;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtFullname_header.setText("Hi, "+user.getFull_name());

        try {
            ShowBranch();
        } catch (SQLException ex) {
            Logger.getLogger(Admin_BranchListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
    @FXML
    private void Save(ActionEvent event) throws SQLException {
        String branch_name = txtBranchName.getText();
        String branch_address = txtAddress.getText();
        String phone = txtPhone.getText();
        position = TvBranchList.getSelectionModel().getSelectedIndex();
        Branch br = new Branch();
        Branch selected_branch = TvBranchList.getSelectionModel().getSelectedItem();

        
        if(selected_branch != null){
            br.setBranch_id(selected_branch.getBranch_id());
            br.setBranch_name(branch_name);
            br.setBranch_address(branch_address);
            br.setBranch_phone(phone);
            br.setUpdated_at(LocalDate.MAX);
            branchEntity.updateBranch(br);
        }else{
            br = new Branch(0,branch_name, branch_address, LocalDate.now(), LocalDate.now(), phone);
            branchEntity.addBranch(br);
        }
        ShowBranch();
  
        txtBranchName.setText("");
        txtAddress.setText("");
        txtPhone.setText("");
    }

    @FXML
    private void Refresh(ActionEvent event) throws SQLException {
       ShowBranch();  
    }

    @FXML
    private void Reset(ActionEvent event) throws SQLException {
        txtBranchName.setText("");
        txtAddress.setText("");
        txtPhone.setText("");
    }

    @FXML
    private void FInd() throws SQLException {
        //get keyword;
        String txtname = txtBranchName.getText();
        txtname = "%"+txtname+"%";
        
        //get data list
        ArrayList<Branch> data = branchEntity.searchBranches(txtname);
        
        //show list data
        NumberColmn.setCellValueFactory(new PropertyValueFactory<>("branch_id"));
        BranchColmn.setCellValueFactory(new PropertyValueFactory<>("branch_name"));
        AddressColmn.setCellValueFactory(new PropertyValueFactory<>("branch_address"));
        Update_atColmn.setCellValueFactory(new PropertyValueFactory<>("updated_at"));
        Create_atColmn.setCellValueFactory(new PropertyValueFactory<>("created_at"));

        TvBranchList.setItems(FXCollections.observableArrayList(data));
        
        txtCount.setText("Result: "+(data.size()));
    }

    @FXML
    private void Delete() throws SQLException {
        Branch selected_branch = TvBranchList.getSelectionModel().getSelectedItem();
        if (selected_branch != null) {
            branchEntity.deleteBranch(selected_branch.getBranch_id());
        }
        ShowBranch();
        
        txtBranchName.setText("");
        txtAddress.setText("");
    }
    
    public void ShowBranch() throws SQLException{
        ArrayList<Branch> branchList = branchEntity.getAllBranches();
        NumberColmn.setCellValueFactory(new PropertyValueFactory<>("branch_id"));
        BranchColmn.setCellValueFactory(new PropertyValueFactory<>("branch_name"));
        AddressColmn.setCellValueFactory(new PropertyValueFactory<>("branch_address"));
        Update_atColmn.setCellValueFactory(new PropertyValueFactory<>("updated_at"));
        Create_atColmn.setCellValueFactory(new PropertyValueFactory<>("created_at"));

        TvBranchList.setItems(FXCollections.observableArrayList(branchList));
     
        txtCount.setText("Result: "+(branchList.size()));
    }
    
    @FXML
    public void showBranchDetail(){
        Branch selected_branch = TvBranchList.getSelectionModel().getSelectedItem();
        txtBranchName.setText(selected_branch.getBranch_name());
        txtAddress.setText(selected_branch.getBranch_address());
        txtPhone.setText(selected_branch.getBranch_phone());
    }

     @FXML
    private void logout() throws IOException {
        App.setRoot("Login");
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
