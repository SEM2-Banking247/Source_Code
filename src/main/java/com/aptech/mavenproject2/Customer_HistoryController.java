/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aptech.mavenproject2;

import Entities.CardEntity;
import Models.Card;
import Entities.RoleEntity;
import Entities.TransactionEntity;
import Entities.UserEntity;
import Models.Custom_Transaction;
import Models.Transaction;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author tuan
 */
public class Customer_HistoryController implements Initializable{
    private final TransactionEntity transactionEntity = new TransactionEntity();
    private final RoleEntity roleEntity = new RoleEntity();
    private final UserEntity userEntity = new UserEntity();
    private final CardEntity cardEntity = new CardEntity();
    User user = User.getInstance();
    Custom_Transaction custom_Transaction = Custom_Transaction.getInstance();
    
    @FXML
    private ChoiceBox<String> cardNumberChoiceBox;
        
    @FXML
    private Label txtFullname_header;
   
    @FXML
    private TableView<Custom_Transaction> transactionList;
    
    @FXML
    private TableColumn<Custom_Transaction, Integer> transaction_id;
    
    @FXML
    private TableColumn<Custom_Transaction, String> amount;
    
    @FXML
    private TableColumn<Custom_Transaction, String> balance;

    @FXML
    private TableColumn<Custom_Transaction, String> notice;
        
    @FXML
    private TableColumn<Custom_Transaction, String> transaction_date;
    
    @FXML
    private Label txtNotification;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //add account card's number into choiceBox
        ArrayList<Card> cards = new ArrayList<>();
        try {
            cards = cardEntity.selectCardsByUserId(user.getUser_id());
        } catch (SQLException ex) {
            Logger.getLogger(Customer_WithdrawController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cards.forEach(card -> cardNumberChoiceBox.getItems().add(card.getCard_number()));
        
        //show user's infor
        txtFullname_header.setText("Hi, "+user.getFull_name());
    }    
    
    @FXML
    private void showBranches() throws IOException {
        App.setRoot("Customer_Branches");
    }
    
    @FXML
    private void exportHistory() throws SQLException{
        String card_number = cardNumberChoiceBox.getValue();
        Card card = cardEntity.selectCardByCardNumber(card_number);
        
        //add card transaction's data into tableView
        ArrayList<Custom_Transaction> custom_transactions = new ArrayList<>();
        ArrayList<Transaction> transactions = new ArrayList<>();
        try {
            transactions = transactionEntity.searchTransactionsByCardID(card.getCard_infor_id());
            for(Transaction transaction : transactions){
                Custom_Transaction custom_transaction = new Custom_Transaction();
                
                custom_transaction.setTransaction_id(transaction.getTransaction_id());
                //if its a withdraw transaction
//                transaction.getCard_id_request()== card.getCard_infor_id() || transaction.getCard_id_receiver() == transaction.getCard_id_request()
                if (transaction.getTransaction_type_id() == 1) {
                    custom_transaction.setTransaction_amount(String.format("-"+"%.0f",transaction.getTransaction_amount()));
                    custom_transaction.setNew_balance(String.format("%.0f",transaction.getNew_balance_request()));
                }
                //if its an insert money or transfer transaction
//                transaction.getCard_id_receiver() == card.getCard_infor_id() && transaction.getCard_id_receiver() != transaction.getCard_id_request()
                else{
                    custom_transaction.setTransaction_amount(String.format("+"+"%.0f",transaction.getTransaction_amount()));
                    custom_transaction.setNew_balance(String.format("%.0f",transaction.getNew_balance_receiver()));
                }
                custom_transaction.setNotice(transaction.getNotice());
                custom_transaction.setTransaction_date(transaction.getTransaction_date().toString());
                
                custom_transactions.add(custom_transaction);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customer_HistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        transaction_id.setCellValueFactory(new PropertyValueFactory<>("Transaction_id"));
        amount.setCellValueFactory(new PropertyValueFactory<>("transaction_amount"));
        balance.setCellValueFactory(new PropertyValueFactory<>("new_balance"));
        notice.setCellValueFactory(new PropertyValueFactory<>("Notice"));
        transaction_date.setCellValueFactory(new PropertyValueFactory<>("transaction_date"));
        transactionList.setItems(FXCollections.observableArrayList(custom_transactions));
    }
    
    @FXML
    private void details() throws SQLException, IOException{
        //get selected transaction
        Custom_Transaction selectedCustomTransaction = transactionList.getSelectionModel().getSelectedItem();
        if (selectedCustomTransaction == null) {
            txtNotification.setText("Select a transaction please!");
        }
        else {
            Transaction selectedTransaction = transactionEntity.searchTransactionByID(selectedCustomTransaction.getTransaction_id());
        
            //get selected card
            String card_number = cardNumberChoiceBox.getValue();
            Card card = cardEntity.selectCardByCardNumber(card_number);

            //get selected transaction's data and transfer it to bill page
            custom_Transaction.setTransaction_id(selectedTransaction.getTransaction_id());
            custom_Transaction.setTransaction_type_id(selectedTransaction.getTransaction_type_id());
            custom_Transaction.setCard_id_request(selectedTransaction.getCard_id_request());
            custom_Transaction.setCard_id_receiver(selectedTransaction.getCard_id_receiver());
            custom_Transaction.setAcc_number_request(selectedTransaction.getAcc_number_request());
            custom_Transaction.setAcc_number_receiver(selectedTransaction.getAcc_number_receiver());
            custom_Transaction.setTransaction_amount(String.format("%.0f",selectedTransaction.getTransaction_amount()));

            if (selectedTransaction.getTransaction_type_id() == 1 || selectedTransaction.getTransaction_type_id() == 2) {
                custom_Transaction.setNew_balance(String.format("%.0f",selectedTransaction.getNew_balance_request()));
            }

            if (selectedTransaction.getTransaction_type_id() == 3 && card.getCard_infor_id() == selectedTransaction.getCard_id_request()) {
                custom_Transaction.setNew_balance(String.format("%.0f",selectedTransaction.getNew_balance_request()));
            }

            if (selectedTransaction.getTransaction_type_id() == 3 && card.getCard_infor_id() == selectedTransaction.getCard_id_receiver()) {
                custom_Transaction.setNew_balance(String.format("%.0f",selectedTransaction.getNew_balance_receiver()));
            }

            custom_Transaction.setTransaction_date(selectedTransaction.getTransaction_date().toString());
            custom_Transaction.setNotice(selectedTransaction.getNotice());

            //redirecting to bill page
            if (selectedTransaction.getTransaction_type_id() == 1) {
                App.setRoot("Transaction_WithdrawBill");
            }
            if (selectedTransaction.getTransaction_type_id() == 2) {
                App.setRoot("Transaction_InsertBill");
            }
            if (selectedTransaction.getTransaction_type_id() == 3) {
                App.setRoot("Transaction_TransferBill");
            }
        }
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
}
