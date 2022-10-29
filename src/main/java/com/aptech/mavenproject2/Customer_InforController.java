/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aptech.mavenproject2;

import Entities.BranchEntity;
import Entities.CardEntity;
import Models.Card;
import Entities.RoleEntity;
import Entities.UserEntity;
import Models.Branch;
import Models.Custom_Card;
import Models.Role;
import Models.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author tuan
 */
public class Customer_InforController implements Initializable{
    private final BranchEntity branchEntity = new BranchEntity();
    private final RoleEntity roleEntity = new RoleEntity();
    private final UserEntity userEntity = new UserEntity();
    private final CardEntity cardEntity = new CardEntity();
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
    private Label txtRole;
    
    @FXML
    private TableView<Custom_Card> cardList;
    
    @FXML
    private TableColumn<Custom_Card, String> card_number;
    
    @FXML
    private TableColumn<Custom_Card, String> card_balance;
    
    @FXML
    private TableColumn<Custom_Card, String> branch_name;

    @FXML
    private TableColumn<Custom_Card, String> status;
        
    @FXML
    private TableColumn<Custom_Card, String> create_date;
    
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
            Logger.getLogger(Customer_InforController.class.getName()).log(Level.SEVERE, null, ex);
        }
  
        if (role != null) {
            txtRole.setText(role.getName());
        }
        //show user's infor
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
        
        ArrayList<Custom_Card> custom_cards= new ArrayList<>();
        ArrayList<Card> cards = new ArrayList<>();
        try {
            cards = cardEntity.selectCardsByUserId(user.getUser_id());
            for(Card card : cards){
                Custom_Card custom_card = new Custom_Card();
                custom_card.setCard_number(card.getCard_number());
                custom_card.setCard_balance(String.format("%.0f",card.getCard_balance()));
                Branch branch = branchEntity.selectBranchById(card.getBranch_id());
                custom_card.setBranch_name(branch.getBranch_name());
                custom_card.setStatus(card.getStatus());
                custom_card.setCreated_at(card.getCreated_at().toString());
                custom_cards.add(custom_card);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customer_InforController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //add user card's data into tableView
        card_number.setCellValueFactory(new PropertyValueFactory<>("card_number"));
        card_balance.setCellValueFactory(new PropertyValueFactory<>("card_balance"));
        branch_name.setCellValueFactory(new PropertyValueFactory<>("branch_name"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        create_date.setCellValueFactory(new PropertyValueFactory<>("created_at"));
        cardList.setItems(FXCollections.observableArrayList(custom_cards));
    }  
    
    @FXML
    private void showBranches() throws IOException {
        App.setRoot("Customer_Branches");
    }
    
    @FXML
    private void getPIN() throws IOException{
        App.setRoot("Customer_GetPIN");
    }
    
    @FXML
    private void editUser() throws IOException{
        App.setRoot("Customer_EditInfor");
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
    
    @FXML
    private void changePIN() throws IOException{
        App.setRoot("Customer_ChangePIN");
    }
    
    @FXML
    private void newCard() throws IOException{
        App.setRoot("Customer_NewCard");
    }
    
    @FXML
    private void ChangePassword() throws IOException{
        App.setRoot("Customer_EditPassword");
    }
}
