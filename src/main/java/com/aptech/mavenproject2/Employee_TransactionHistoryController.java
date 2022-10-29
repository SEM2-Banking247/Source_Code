/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aptech.mavenproject2;

import Entities.CardEntity;
import Models.Card;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author tuan
 */
public class Employee_TransactionHistoryController implements Initializable{
    private final TransactionEntity transactionEntity = new TransactionEntity();
    private final UserEntity userEntity = new UserEntity();
    private final CardEntity cardEntity = new CardEntity();
    User user = User.getInstance();
    Custom_Transaction custom_Transaction = Custom_Transaction.getInstance();
    
    @FXML
    private TextField txtCardNumber;
        
    @FXML
    private Label txtFullname_header;
   
    @FXML
    private TableView<Custom_Transaction> transactionList;
    
    @FXML
    private TableColumn<Custom_Transaction, Integer> transaction_id;
    
    @FXML
    private TableColumn<Custom_Transaction, String> amount;
    
    @FXML
    private TableColumn<Custom_Transaction, String> card_number_request;

    @FXML
    private TableColumn<Custom_Transaction, String> card_number_receiver;
        
    @FXML
    private TableColumn<Custom_Transaction, String> transaction_date;
    
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
        //show user's infor
        txtFullname_header.setText("Hi, "+user.getFull_name());
        
        ArrayList<Custom_Transaction> custom_transactions = new ArrayList<>();
        ArrayList<Transaction> transactions = new ArrayList<>();
        try {
            transactions = transactionEntity.selectAllTransactions();
            for(Transaction transaction : transactions){
                Custom_Transaction custom_transaction = new Custom_Transaction();
                Card card_request = cardEntity.selectCardById(transaction.getCard_id_request());
                Card card_receiver = cardEntity.selectCardById(transaction.getCard_id_receiver());
                
                custom_transaction.setTransaction_id(transaction.getTransaction_id());
                custom_transaction.setTransaction_amount(String.format("%.0f",transaction.getTransaction_amount()));
                custom_transaction.setCard_number_request(card_request.getCard_number());
                custom_transaction.setCard_number_receiver(card_receiver.getCard_number());
                custom_transaction.setTransaction_date(transaction.getTransaction_date().toString());
                
                custom_transactions.add(custom_transaction);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Employee_TransactionHistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        transaction_id.setCellValueFactory(new PropertyValueFactory<>("Transaction_id"));
        amount.setCellValueFactory(new PropertyValueFactory<>("transaction_amount"));
        card_number_request.setCellValueFactory(new PropertyValueFactory<>("card_number_request"));
        card_number_receiver.setCellValueFactory(new PropertyValueFactory<>("card_number_receiver"));
        transaction_date.setCellValueFactory(new PropertyValueFactory<>("transaction_date"));
        
        transactionList.setItems(FXCollections.observableArrayList(custom_transactions));
        
        txtCount.setText("Result: "+custom_transactions.size());
    }    
    
    @FXML
    private void searchTransactions() throws SQLException{
        String card_number = txtCardNumber.getText();
        Card card = cardEntity.selectCardByCardNumber(card_number);
        
        //add card transaction's data into tableView
        ArrayList<Custom_Transaction> custom_transactions = new ArrayList<>();
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        try {
            transactions = transactionEntity.searchTransactionsByCardID(card.getCard_infor_id());
            for(Transaction transaction : transactions){
                Custom_Transaction custom_transaction = new Custom_Transaction();
                Card card_request = cardEntity.selectCardById(transaction.getCard_id_request());
                Card card_receiver = cardEntity.selectCardById(transaction.getCard_id_receiver());
                
                custom_transaction.setTransaction_id(transaction.getTransaction_id());
                custom_transaction.setTransaction_amount(String.format("%.0f",transaction.getTransaction_amount()));
                custom_transaction.setCard_number_request(card_request.getCard_number());
                custom_transaction.setCard_number_receiver(card_receiver.getCard_number());
                custom_transaction.setTransaction_date(transaction.getTransaction_date().toString());
                
                custom_transactions.add(custom_transaction);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Employee_TransactionHistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        transaction_id.setCellValueFactory(new PropertyValueFactory<>("Transaction_id"));
        amount.setCellValueFactory(new PropertyValueFactory<>("transaction_amount"));
        card_number_request.setCellValueFactory(new PropertyValueFactory<>("card_number_request"));
        card_number_receiver.setCellValueFactory(new PropertyValueFactory<>("card_number_receiver"));
        transaction_date.setCellValueFactory(new PropertyValueFactory<>("transaction_date"));
        transactionList.setItems(FXCollections.observableArrayList(custom_transactions));
        
        txtCount.setText("Result: "+custom_transactions.size());
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
            String card_number = txtCardNumber.getText();
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
    private void refresh(){
        ArrayList<Custom_Transaction> custom_transactions = new ArrayList<>();
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        try {
            transactions = transactionEntity.selectAllTransactions();
            for(Transaction transaction : transactions){
                Custom_Transaction custom_transaction = new Custom_Transaction();
                Card card_request = cardEntity.selectCardById(transaction.getCard_id_request());
                Card card_receiver = cardEntity.selectCardById(transaction.getCard_id_receiver());
                
                custom_transaction.setTransaction_id(transaction.getTransaction_id());
                custom_transaction.setTransaction_amount(String.format("%.0f",transaction.getTransaction_amount()));
                custom_transaction.setCard_number_request(card_request.getCard_number());
                custom_transaction.setCard_number_receiver(card_receiver.getCard_number());
                custom_transaction.setTransaction_date(transaction.getTransaction_date().toString());
                
                custom_transactions.add(custom_transaction);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Employee_TransactionHistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        transaction_id.setCellValueFactory(new PropertyValueFactory<>("Transaction_id"));
        amount.setCellValueFactory(new PropertyValueFactory<>("transaction_amount"));
        card_number_request.setCellValueFactory(new PropertyValueFactory<>("card_number_request"));
        card_number_receiver.setCellValueFactory(new PropertyValueFactory<>("card_number_receiver"));
        transaction_date.setCellValueFactory(new PropertyValueFactory<>("transaction_date"));
        
        transactionList.setItems(FXCollections.observableArrayList(custom_transactions));
        
        txtCount.setText("Result: "+custom_transactions.size());
    }
    
    @FXML
    private void logout() throws IOException {
        App.setRoot("Login");
    }
    
    @FXML
    private void home() throws IOException {
        App.setRoot("Employee_Home");
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
}
