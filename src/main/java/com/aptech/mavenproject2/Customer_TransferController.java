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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
import javafx.util.Duration;

/**
 *
 * @author tuan
 */
public class Customer_TransferController implements Initializable{
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
    private TextField txtTransferAmount;
    
    @FXML
    private Label txtBalance;
        
    @FXML   
    private Label txtReceiverName;
    
    @FXML 
    private TextField txtReceiverCardNumber;
    
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
            Logger.getLogger(Customer_TransferController.class.getName()).log(Level.SEVERE, null, ex);
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
        txtTransferAmount.setText("");
        txtNote.setText("");
        txtReceiverCardNumber.setText("");
        txtReceiverName.setText("");
    }
    
    @FXML
    private int findAccount() throws SQLException{
        String card_number = cardNumberChoiceBox.getValue();
        if (card_number == null) {
            txtReceiverName.setText("Please select your card first!");
            return 0;
        }
        
        String receiverAccount = txtReceiverCardNumber.getText();
        if (receiverAccount.equals("")) {
            txtReceiverName.setText("Please input your receiver card number!");
            return 0;
        }
        
        Card card = cardEntity.selectCardByCardNumber(receiverAccount);
        
        if(card_number.equalsIgnoreCase(receiverAccount)){
            txtReceiverName.setText("It is you, please try again");
            return 0;
        }
        
        User receiver = userEntity.selectUserById(card.getUser_id());
        if (receiver.getAccount_number() != null) {
            txtReceiverName.setText(receiver.getFull_name());
        }
        else {
            txtReceiverName.setText("Not Found!");
            return 0;
        }
        return 1;
    }
    
    @FXML 
    private void getAmount() throws SQLException{
        //get data
        String card_number = cardNumberChoiceBox.getValue();
        if (card_number != null) {
            Card card_request = cardEntity.selectCardByCardNumber(card_number);
            txtBalance.setText(String.format("%.0f",card_request.getCard_balance()));
        }
        
    }
    
    @FXML 
    private int transferMoney() throws SQLException, InterruptedException, IOException{
        //validate receiver card number
        int pass = findAccount();
        if(pass == 1){
            //get data
            String card_number = cardNumberChoiceBox.getValue();
            Card card_request= cardEntity.selectCardByCardNumber(card_number);
            
            Double amount = Double.parseDouble(txtTransferAmount.getText());
            if (amount >= (card_request.getCard_balance() - 50000)) {
                ValidateAmount.setText("Exceed transferable amount!");
                return 0;
            }
            
            String note = txtNote.getText();
            int transaction_type= 3;
            
            User user_request = userEntity.selectUserById(card_request.getUser_id());

            String receiverAccount = txtReceiverCardNumber.getText();
            Card card_receiver = cardEntity.selectCardByCardNumber(receiverAccount);
            User user_receiver = userEntity.selectUserById(card_receiver.getUser_id());

            //execute transaction

            //add transaction 
            Transaction transaction = new Transaction();
            transaction.setTransaction_type_id(transaction_type);
            transaction.setCard_id_request(card_request.getCard_infor_id());
            transaction.setCard_id_receiver(card_receiver.getCard_infor_id());
            transaction.setAcc_number_request(user_request.getAccount_number());
            transaction.setAcc_number_receiver(user_receiver.getAccount_number());  
            transaction.setTransaction_amount(amount);
            transaction.setNew_balance_request(card_request.getCard_balance() - amount);
            transaction.setNew_balance_receiver(card_receiver.getCard_balance() + amount);
            transaction.setNotice(note);
            transaction.setTransaction_date(LocalDate.now());
            transactionEntity.addTransaction(transaction);

            //update card_infor balance
            cardEntity.updateCardBalanceByCardId(card_request.getCard_infor_id(), card_request.getCard_balance() - amount);
            cardEntity.updateCardBalanceByCardId(card_receiver.getCard_infor_id(), card_receiver.getCard_balance() + amount);

            //display result
            card_request= cardEntity.selectCardByCardNumber(card_number);
            txtNotification.setText("Transfer completed!");
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
            App.setRoot("Transaction_TransferBill");
        }
        else {
            txtNotification.setText("Unqualified receiver card number!");
            return 0;
        }
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
    private void withdraw() throws IOException {
        App.setRoot("Customer_Withdraw");
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
