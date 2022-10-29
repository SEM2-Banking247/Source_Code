/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.aptech.mavenproject2;

import Entities.BranchEntity;
import Entities.CardEntity;
import Entities.UserEntity;
import Models.Branch;
import Models.Card;
import Models.Custom_Card;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class Admin_CardListController implements Initializable {
    private final CardEntity cardEntity = new CardEntity();
    private final UserEntity userEntity = new UserEntity();
    private final BranchEntity branchEntity = new BranchEntity();
    ArrayList<Card> cardList;
    Card card = Card.getInstance();
    User user = User.getInstance();
    
    @FXML
    private TableView<Custom_Card> TvCardManager;
    @FXML
    private TableColumn<Custom_Card, String> ColUserCode;
    @FXML
    private TableColumn<Custom_Card, String> ColCardnumber;
    @FXML
    private TableColumn<Custom_Card, String> ColBalance;
    @FXML
    private TableColumn<Custom_Card, String> ColNotice;
    @FXML
    private TableColumn<Custom_Card, String> ColStatus;
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
    private TextField txtCardnumber;
    @FXML
    private TextField txtBalance;
    @FXML
    private TextField txtNotice;
    @FXML
    private ChoiceBox<String> txtStatus;
    @FXML
    private TextField txtUserCode;
    @FXML
    private TextField txtFiindName;
    @FXML
    private Label txtFullname_header;
    @FXML
    private ChoiceBox<String> branches;
    @FXML
    private Label txtSelectedUser;
    @FXML 
    private TextField txtKeyword;
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
        
        //insert data into status choicebox
        txtStatus.setItems(FXCollections.observableArrayList("Active", "Disabled", "Pending"));
        
        // insert data into branch choicebox 
        ArrayList<Branch> all_branches = null;
        try {
            all_branches = branchEntity.getAllBranches();
        } catch (SQLException ex) {
            Logger.getLogger(Admin_CardListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        all_branches.forEach(branch -> branches.getItems().add(branch.getBranch_name()));
        
        //show all card details
        try {
            showCard();
        } catch (SQLException ex) {
            Logger.getLogger(Admin_CardListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showCardDetail() throws SQLException {
        Custom_Card select_card = TvCardManager.getSelectionModel().getSelectedItem();
        User selected_user = userEntity.selectUserById(select_card.getUser_id());
        
        //show card details
        txtUserCode.setText(selected_user.getAccount_number());
        txtCardnumber.setText(select_card.getCard_number());
        txtBalance.setText(select_card.getCard_balance());
        txtNotice.setText(select_card.getNotice());
        txtStatus.setValue(select_card.getStatus());
        
        Branch selected_branch = branchEntity.selectBranchById(select_card.getBranch_id());
        branches.setValue(selected_branch.getBranch_name());
        
        findUser();
    }

    @FXML
    private void Save() throws SQLException, InterruptedException {
        //get branch and status data:
        String branch = branches.getValue();
        Branch selected_branch = branchEntity.selectBranchByName(branch);
        
        String Account_number = txtUserCode.getText();
        User selected_user = userEntity.selectUserByAccNumber(Account_number);
        
        String status = txtStatus.getValue();
        String Card_number = txtCardnumber.getText();
        String Card_balance = txtBalance.getText();
        String notice = txtNotice.getText();
        LocalDate current_date = LocalDate.now();
        
        Card card = new Card();
        card.setUser_id(selected_user.getUser_id());
        card.setBranch_id(selected_branch.getBranch_id());
        card.setCard_number(Card_number);
        card.setCard_balance(Double.parseDouble(Card_balance));
        card.setUpdated_at(current_date);
        card.setCreated_at(current_date);
        card.setNotice(notice);
        card.setStatus(status);
        
        Custom_Card selected_card = TvCardManager.getSelectionModel().getSelectedItem();
        //if the user select a card from the table, its update, otherwise its add.
        if (selected_card != null) {
            card.setCard_infor_id(selected_card.getCard_infor_id());
            
            //update card info
            cardEntity.updateCardInfo(card);
            //update card balance
            cardEntity.updateCardBalanceByCardId(card.getCard_infor_id(), card.getCard_balance());
            txtNotification.setText("Card Updated!");
            refresh();
        }
        else {
            //add
            cardEntity.addCard(card);
            txtNotification.setText("Card Added!");
            refresh();
        }
    }
    
    @FXML
    private void getRandomCardNumber(){
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        txtCardnumber.setText(String.format("%06d", number));
    }

    @FXML
    private void Reset() {
        clear();
    }
    
    @FXML
    private void refresh() throws SQLException{
        showCard();
        clear();
    }

    @FXML
    private void Find() throws SQLException {
        //get keyword
        String keyword = txtKeyword.getText();
        ArrayList<Card> data = null;
        
        //get card list data
        if (keyword.equals("")) {
            data = cardEntity.selectAllCards();
        }
        else {
            keyword = "%"+keyword+"%";  
            data = cardEntity.searchCards(keyword);
        }
        
        //convert data type of the list from Card to Custom_Card 
        ArrayList<Custom_Card> cardList = new ArrayList<>();
        for (Card card : data) {            
            Custom_Card custom_card = new Custom_Card();
            custom_card.setCard_infor_id(card.getCard_infor_id());
            custom_card.setUser_id(card.getUser_id());
            custom_card.setBranch_id(card.getBranch_id());
            
            User new_user = userEntity.selectUserById(card.getUser_id());
            custom_card.setAccount_number(new_user.getAccount_number());
            
            custom_card.setCard_number(card.getCard_number());
            custom_card.setCard_balance(String.format("%.0f",card.getCard_balance()));
            custom_card.setNotice(card.getNotice());
            custom_card.setStatus(card.getStatus());
            custom_card.setPin_number(new_user.getPin());
            custom_card.setCreated_at(card.getCreated_at().toString());
            custom_card.setUpdated_at(card.getUpdated_at().toString());
            
            cardList.add(custom_card);
        }
        
        //show list data
        ColUserCode.setCellValueFactory(new PropertyValueFactory<>("account_number"));
        ColCardnumber.setCellValueFactory(new PropertyValueFactory<>("card_number"));
        ColBalance.setCellValueFactory(new PropertyValueFactory<>("card_balance"));
        ColNotice.setCellValueFactory(new PropertyValueFactory<>("notice"));
        ColStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        TvCardManager.setItems(FXCollections.observableArrayList(cardList));
        
        txtCount.setText("Result: "+(cardList.size()));

    }

    @FXML
    private void Delete(ActionEvent event) throws SQLException {
        Custom_Card select_card = TvCardManager.getSelectionModel().getSelectedItem();
        
        if (select_card != null && !txtSelectedUser.getText().equals("")) {
            cardEntity.deleteCard(select_card.getCard_infor_id());
            Find();
            clear();
        }
        else if (select_card != null && txtSelectedUser.getText().equals("")) {
            cardEntity.deleteCard(select_card.getCard_infor_id());
            showCard();
            clear();
        }
        else if(select_card == null && !txtSelectedUser.getText().equals("")){
            Find();
        }
        else if(select_card == null && txtSelectedUser.getText().equals("")){
            showCard();
            clear();
        }
        txtNotification.setText("Card deleted!");
    }

    public void showCard() throws SQLException {
        ArrayList<Card> data = cardEntity.selectAllCards();
        ArrayList<Custom_Card> cardList = new ArrayList<>();
        for (Card card : data) {
            Custom_Card custom_card = new Custom_Card();
            custom_card.setCard_infor_id(card.getCard_infor_id());
            custom_card.setUser_id(card.getUser_id());
            custom_card.setBranch_id(card.getBranch_id());
            
            User new_user = userEntity.selectUserById(card.getUser_id());
            custom_card.setAccount_number(new_user.getAccount_number());
            
            custom_card.setCard_number(card.getCard_number());
            custom_card.setCard_balance(String.format("%.0f",card.getCard_balance()));
            custom_card.setNotice(card.getNotice());
            custom_card.setStatus(card.getStatus());
            custom_card.setPin_number(new_user.getPin());
            custom_card.setCreated_at(card.getCreated_at().toString());
            custom_card.setUpdated_at(card.getUpdated_at().toString());
            
            cardList.add(custom_card);
        }

        ColUserCode.setCellValueFactory(new PropertyValueFactory<>("account_number"));
        ColCardnumber.setCellValueFactory(new PropertyValueFactory<>("card_number"));
        ColBalance.setCellValueFactory(new PropertyValueFactory<>("card_balance"));
        ColNotice.setCellValueFactory(new PropertyValueFactory<>("notice"));
        ColStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        TvCardManager.setItems(FXCollections.observableArrayList(cardList));
        
        txtCount.setText("Result: "+(cardList.size()));

    }

    public void clear() {
        txtCardnumber.setText("");
        txtBalance.setText("");
        txtNotice.setText("");
        txtSelectedUser.setText("");
        txtStatus.setValue("");
        txtUserCode.setText("");
        txtFiindName.setText("");
        branches.setValue("");
        txtStatus.setValue("");
        txtNotification.setText("");
    }
    
    @FXML
    private void logout() throws IOException {
        App.setRoot("Login");
    }
    
    @FXML
    private void findUser() throws SQLException{
        String AccountNumber = txtUserCode.getText();
        User selected_user = userEntity.selectUserByAccNumber(AccountNumber);
        if(selected_user.getFull_name() != null) {
            txtSelectedUser.setText(selected_user.getFull_name());
        }
        else {
            txtSelectedUser.setText("Not Found!");
        }
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
