/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aptech.mavenproject2;

import Entities.BranchEntity;
import Entities.CardEntity;
import Entities.ReportEntity;
import Entities.Report_TypeEntity;
import Entities.RoleEntity;
import Entities.UserEntity;
import Models.Branch;
import Models.Card;
import Models.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

/**
 *
 * @author tuan
 */
public class Customer_NewCardController implements Initializable{
    private final BranchEntity branchEntity = new BranchEntity();
    private final RoleEntity roleEntity = new RoleEntity();
    private final UserEntity userEntity = new UserEntity();
    private final CardEntity cardEntity = new CardEntity();
    private final Report_TypeEntity report_TypeEntity = new Report_TypeEntity();
    private final ReportEntity reportEntity = new ReportEntity();
    User user = User.getInstance();
    
    @FXML
    private ChoiceBox<String> branchesChoiceBox;

    @FXML
    private Label txtFullname_header;
    
    @FXML 
    private Label txtNotification;
    
    @FXML
    private ToggleButton agree_term;
    
    @FXML
    private TextField txtNotice;
    
    @FXML
    private Label success1;
    
    @FXML
    private Label success3;
    
    @FXML
    private Label success2;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //show user's infor
        txtFullname_header.setText("Hi, "+user.getFull_name());
        
        //add branches 
        ArrayList<Branch> branches = new ArrayList<>();
        try {
            branches = branchEntity.getAllBranches();
        } catch (SQLException ex) {
            Logger.getLogger(Customer_WithdrawController.class.getName()).log(Level.SEVERE, null, ex);
        }
        branches.forEach(branch -> branchesChoiceBox.getItems().add(branch.getBranch_name()));
        
        //set result notes hidden
        success2.setVisible(false);
        success1.setVisible(false);
        success3.setVisible(false);
    }    
    
    @FXML
    public int addCard() throws SQLException{
        //get data
        String branch_name = branchesChoiceBox.getValue();
        String notice = txtNotice.getText();
        Branch selected_branch = branchEntity.selectBranchByName(branch_name);
        
        //validate
        if (branch_name == null) {
            txtNotification.setText("Please choose your branch first!");
            return 0;
        }
        
        //add card
        boolean agreed = agree_term.isSelected();
        if (agreed) {
            Card new_card = new Card();
            new_card.setBranch_id(selected_branch.getBranch_id());
            new_card.setUser_id(user.getUser_id());
            new_card.setCard_balance(Double.parseDouble("50000"));
            Random rnd = new Random();
            int number = rnd.nextInt(999999);
            new_card.setCard_number(String.format("%06d", number));
            new_card.setStatus("Pending");
            new_card.setCreated_at(LocalDate.now());
            new_card.setUpdated_at(LocalDate.now());
            new_card.setNotice(notice);
            cardEntity.addCard(new_card);
            
            //show notifications after add
            txtNotification.setText(String.format("%06d", number));
            success2.setVisible(true);
            success1.setVisible(true);
            success3.setVisible(true);
        }
        else {
            txtNotification.setText("Please agree to our user terms first.");
            return 0;
        }
        return 1;
    }
    
    @FXML
    private void getRandomPIN(){
        
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
    private void showBranches() throws IOException {
        App.setRoot("Customer_Branches");
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
    private void showTerm() throws IOException{
        App.setRoot("Customer_Terms");
    }
}
