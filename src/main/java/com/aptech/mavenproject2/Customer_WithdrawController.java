/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aptech.mavenproject2;

import Entities.CardEntity;
import Entities.TransactionEntity;
import Entities.UserEntity;
import Models.Card;
import Models.Custom_Transaction;
import Models.Transaction;
import Models.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import javafx.util.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author tuan
 */
public class Customer_WithdrawController implements Initializable{
    ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    private final UserEntity userEntity = new UserEntity();
    private final CardEntity cardEntity = new CardEntity();
    private final TransactionEntity transactionEntity = new TransactionEntity();
    User user = User.getInstance();
    Custom_Transaction custom_Transaction = Custom_Transaction.getInstance();


    @FXML
    private ChoiceBox<String> cardNumberChoiceBox;
        
    @FXML
    private Label txtFullname_header;
    
    @FXML
    private TextField txtWithdrawAmount;
    
    @FXML
    private Label txtBalance;
        
    @FXML
    private TextArea txtNote;
    
    @FXML 
    private Label txtNotification;
    
    @FXML
    private Label ValidateAmount;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    @SuppressWarnings("unchecked")
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Card> cards = new ArrayList<>();
        try {
            cards = cardEntity.selectCardsByUserId(user.getUser_id());
        } catch (SQLException ex) {
            Logger.getLogger(Customer_WithdrawController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cards.forEach(card -> cardNumberChoiceBox.getItems().add(card.getCard_number()));
        txtFullname_header.setText("Hi, "+user.getFull_name());
        
        //get user card's balance every 1s
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> {
            try {
                getAmount();
            } catch (SQLException ex) {
                Logger.getLogger(Customer_WithdrawController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();
    }    
    
    @FXML
    private void reset(){
        txtWithdrawAmount.setText("");
        txtNote.setText("");
    }
    
    @FXML 
    private void getAmount() throws SQLException{
        //get data
        String card_number = cardNumberChoiceBox.getValue();
        Card card_request = cardEntity.selectCardByCardNumber(card_number);
        txtBalance.setText(String.format("%.0f",card_request.getCard_balance()));
    }
    
    @FXML 
    private int withdrawMoney() throws SQLException, IOException, InterruptedException{
        //get data
        String card_number = cardNumberChoiceBox.getValue();
        Card card_request= cardEntity.selectCardByCardNumber(card_number);
        
        Double amount = Double.parseDouble(txtWithdrawAmount.getText());
        if (amount >= (card_request.getCard_balance() - 50000)) {
            ValidateAmount.setText("Amount exceeded withdrawable amount!");
            return 0;
        }
        
        String note = txtNote.getText();
        int transaction_type= 1;
        
        User user_request = userEntity.selectUserById(card_request.getUser_id());
        
        //execute transaction
        //add transaction 
        Transaction transaction = new Transaction();
        transaction.setTransaction_type_id(transaction_type);
        transaction.setCard_id_request(card_request.getCard_infor_id());
        transaction.setCard_id_receiver(card_request.getCard_infor_id());
        transaction.setAcc_number_request(user_request.getAccount_number());
        transaction.setAcc_number_receiver(user_request.getAccount_number());  
        transaction.setTransaction_amount(amount);
        transaction.setNew_balance_request(card_request.getCard_balance() - amount);
        transaction.setNew_balance_receiver(card_request.getCard_balance() - amount);
        transaction.setNotice(note);
        transaction.setTransaction_date(LocalDate.now());
        transactionEntity.addTransaction(transaction);
        
        //update card_infor balance
        cardEntity.updateCardBalanceByCardId(card_request.getCard_infor_id(), card_request.getCard_balance() - amount);
        
        card_request= cardEntity.selectCardByCardNumber(card_number);
        
        //display result
        txtNotification.setText("Withdraw completed!");
        txtBalance.setText(String.format("%.0f",card_request.getCard_balance()));
        
        //get transaction's data and transfer it to bill page
        custom_Transaction.setTransaction_type_id(transaction.getTransaction_type_id());
        custom_Transaction.setCard_id_request(transaction.getCard_id_request());
        custom_Transaction.setCard_id_receiver(transaction.getCard_id_receiver());
        custom_Transaction.setAcc_number_request(transaction.getAcc_number_request());
        custom_Transaction.setAcc_number_receiver(transaction.getAcc_number_receiver());
        custom_Transaction.setTransaction_amount(String.format("%.0f",transaction.getTransaction_amount()));     
        custom_Transaction.setNew_balance(String.format("%.0f",transaction.getNew_balance_request()));
        custom_Transaction.setTransaction_date(transaction.getTransaction_date().toString());
        custom_Transaction.setNotice(transaction.getNotice());
        
        
        Thread.sleep(3000);
        App.setRoot("Transaction_WithdrawBill");
        return 1;
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

    
}
