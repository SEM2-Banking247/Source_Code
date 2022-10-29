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
import Models.Custom_Transaction;
import Models.User;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author tuan
 */
public class Transaction_TransferBillController implements Initializable{
    private final BranchEntity branchEntity = new BranchEntity();
    private final RoleEntity roleEntity = new RoleEntity();
    private final UserEntity userEntity = new UserEntity();
    private final CardEntity cardEntity = new CardEntity();
    User user = User.getInstance();
    Custom_Transaction custom_Transaction = Custom_Transaction.getInstance();
    
    @FXML
    private Label txtFullname_header;
    
    @FXML
    private Label txtCardNumberRequest;
    
    @FXML 
    private Label txtCardNumberReceiver;
    
    @FXML
    private Label txtBankRequest;
    
    @FXML 
    private Label txtBankReceiver;
    
    @FXML
    private Label txtCurrency;
    
    @FXML 
    private Label txtAmount;
    
    @FXML
    private Label txtTransactionDate;
    
    @FXML
    private Label txtFullnameRequest;
    
    @FXML 
    private Label txtFullnameReceiver;
    
    @FXML
    private Label txtDescription;
        
    @FXML
    private Label txtNote;
    
    @FXML
    private Label txtTransactionId;
    
    @FXML
    private Label txtNotification;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtAmount.setText(custom_Transaction.getTransaction_amount());
        txtTransactionDate.setText(custom_Transaction.getTransaction_date());
                
        int transaction_type_id = custom_Transaction.getTransaction_type_id();
        
        //show transaction data based on its type id
        switch (transaction_type_id) {
            //rut tien
            case 1: {
                txtCurrency.setText("-VND");
                txtFullnameRequest.setText(user.getFull_name());
                txtCardNumberRequest.setText(custom_Transaction.getAcc_number_request());
                txtBankRequest.setText("Ecombank");
                txtDescription.setText("Rut tien tai ATM vao ngay "+custom_Transaction.getTransaction_date());
                txtTransactionId.setText(Integer.toString(custom_Transaction.getTransaction_id()));
                break;
            }
            
            //nap tien
            case 2:{
                txtCurrency.setText("+VND");
                txtFullnameRequest.setText(user.getFull_name());
                txtCardNumberRequest.setText(custom_Transaction.getAcc_number_request());
                txtBankRequest.setText("Ecombank");
                txtDescription.setText("Nap tien tai ATM vao ngay "+custom_Transaction.getTransaction_date());
                txtTransactionId.setText(Integer.toString(custom_Transaction.getTransaction_id()));
                break;
            }
            
            //chuyen tien
            case 3: {
                
                Card cardRequest = new Card();
                try {
                    cardRequest = cardEntity.selectCardById(custom_Transaction.getCard_id_request());
                } catch (SQLException ex) {
                    Logger.getLogger(Transaction_TransferBillController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Card cardReceiver = new Card();
                try {
                    cardReceiver = cardEntity.selectCardById(custom_Transaction.getCard_id_receiver());
                } catch (SQLException ex) {
                    Logger.getLogger(Transaction_TransferBillController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //chuyen tien giua cac card trong cung tk
                if (cardReceiver.getUser_id() == cardRequest.getUser_id()) {
                    txtCurrency.setText("VND");
                    txtFullnameRequest.setText(user.getFull_name());
                    txtCardNumberRequest.setText(cardRequest.getCard_number());
                    txtBankRequest.setText("Ecombank");
                    txtDescription.setText("Giao dich dien ra ngay "+custom_Transaction.getTransaction_date());
                    txtTransactionId.setText(Integer.toString(custom_Transaction.getTransaction_id()));
                    txtNote.setText(custom_Transaction.getNotice());
                    txtCardNumberReceiver.setText(cardReceiver.getCard_number());
                    txtFullnameReceiver.setText(user.getFull_name());
                    txtBankReceiver.setText("Ecombank");
                }
                
                
                //chuyen tien den tk khac
                if (cardRequest.getUser_id() == user.getUser_id() &&  cardReceiver.getUser_id() != cardRequest.getUser_id()) {
                    txtCurrency.setText("-VND");
                    txtFullnameRequest.setText(user.getFull_name());
                    txtCardNumberRequest.setText(cardRequest.getCard_number());
                    txtBankRequest.setText("Ecombank");
                    txtDescription.setText("Giao dich dien ra ngay "+custom_Transaction.getTransaction_date());
                    txtTransactionId.setText(Integer.toString(custom_Transaction.getTransaction_id()));
                    txtNote.setText(custom_Transaction.getNotice());
                    txtCardNumberReceiver.setText(cardReceiver.getCard_number());
                    
                    User receiver = new User();
                    try {
                        receiver = userEntity.selectUserById(cardReceiver.getUser_id());
                    } catch (SQLException ex) {
                        Logger.getLogger(Transaction_TransferBillController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    txtFullnameReceiver.setText(receiver.getFull_name());
                    txtBankReceiver.setText("Ecombank");
                }
                
                //nhan tien tu tk khac
                if (cardReceiver.getUser_id() == user.getUser_id() &&  cardReceiver.getUser_id() != cardRequest.getUser_id()) {
                    txtCurrency.setText("+VND");
                    User request = new User();
                    try {
                        request = userEntity.selectUserById(cardReceiver.getUser_id());
                    } catch (SQLException ex) {
                        Logger.getLogger(Transaction_TransferBillController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    txtFullnameRequest.setText(request.getFull_name());
                    txtCardNumberRequest.setText(cardRequest.getCard_number());
                    txtBankRequest.setText("Ecombank");
                    txtDescription.setText("Giao dich dien ra ngay "+custom_Transaction.getTransaction_date());
                    txtTransactionId.setText(Integer.toString(custom_Transaction.getTransaction_id()));
                    txtNote.setText(custom_Transaction.getNotice());
                    txtCardNumberReceiver.setText(cardReceiver.getCard_number());
                    txtFullnameReceiver.setText(user.getFull_name());
                    txtBankReceiver.setText("Ecombank");
                }
                break;
            }
        }
    }
    
    @FXML
    private void printBill() throws FileNotFoundException, IOException{
        try (PDDocument pdfdoc = new PDDocument()) {
            Card cardRequest = new Card();
            try {
                cardRequest = cardEntity.selectCardById(custom_Transaction.getCard_id_request());
            } catch (SQLException ex) {
                Logger.getLogger(Transaction_TransferBillController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Card cardReceiver = new Card();
            try {
                cardReceiver = cardEntity.selectCardById(custom_Transaction.getCard_id_receiver());
            } catch (SQLException ex) {
                Logger.getLogger(Transaction_TransferBillController.class.getName()).log(Level.SEVERE, null, ex);
            }
               
            pdfdoc.addPage(new PDPage());
            PDPage page = pdfdoc.getPage(0);
            try (PDPageContentStream contentStream = new PDPageContentStream(pdfdoc, page)) {
    
                //Setting the font to the Content stream
                contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
                
                String amount = null;
                String userRequestFullName = null;
                String userReceiverFullName = null;        
                
                //chuyen tien giua cac the cung tk
                if (cardReceiver.getUser_id() == cardRequest.getUser_id()) {
                    amount = "VND  "+ custom_Transaction.getTransaction_amount();
                    userRequestFullName = user.getFull_name();
                    userReceiverFullName = user.getFull_name();
                }
                
                //chuyen tien den 1 tk khac
                if (cardRequest.getUser_id() == user.getUser_id() &&  cardReceiver.getUser_id() != cardRequest.getUser_id()) {
                    amount = "-VND  "+ custom_Transaction.getTransaction_amount();
                    userRequestFullName = user.getFull_name();
                    User receiver = new User();
                    try {
                        receiver = userEntity.selectUserById(cardReceiver.getUser_id());
                    } catch (SQLException ex) {
                        Logger.getLogger(Transaction_TransferBillController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    userReceiverFullName = receiver.getFull_name();
                }
                
                //nhan tien tu 1 tai khoan khac
                if (cardReceiver.getUser_id() == user.getUser_id() &&  cardReceiver.getUser_id() != cardRequest.getUser_id()) {
                    amount = "+VND  "+ custom_Transaction.getTransaction_amount();
                    User request = new User();
                    try {
                        request = userEntity.selectUserById(cardRequest.getUser_id());
                    } catch (SQLException ex) {
                        Logger.getLogger(Transaction_TransferBillController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    userRequestFullName = request.getFull_name();
                    userReceiverFullName = user.getFull_name();
                }
                
                //add bank name
                contentStream.beginText();
                contentStream.newLineAtOffset(30, 560);
                String bankname = "EcomBank";
                contentStream.showText(bankname);
                contentStream.endText();
                
                //add transaction successful text
                contentStream.beginText();
                contentStream.newLineAtOffset(200, 530);
                String text = "Transaction Successful!";
                contentStream.showText(text);
                contentStream.endText();
                
                //add amount
                contentStream.beginText();
                contentStream.newLineAtOffset(30, 500);
                contentStream.showText(amount);
                contentStream.endText();
                    
                //add date
                contentStream.beginText();
                contentStream.newLineAtOffset(30, 480);
                String date = custom_Transaction.getTransaction_date();
                contentStream.showText(date);
                contentStream.endText();

                //add from text
                contentStream.beginText();
                contentStream.newLineAtOffset(30, 450);
                String from = "From";
                contentStream.showText(from);
                contentStream.endText();

                //add request name
                contentStream.beginText();
                contentStream.newLineAtOffset(30, 430);
                contentStream.showText(userRequestFullName);
                contentStream.endText();

                //add card request number
                contentStream.beginText();
                contentStream.newLineAtOffset(30, 410);
                String cardRequestNumber = cardRequest.getCard_number();
                contentStream.showText(cardRequestNumber);
                contentStream.endText();

                //add bank name request
                contentStream.beginText();
                contentStream.newLineAtOffset(30, 390);
                String bankName = "EcomBank";
                contentStream.showText(bankName);
                contentStream.endText();
                
                //add to text
                contentStream.beginText();
                contentStream.newLineAtOffset(30, 360);
                String to = "To";
                contentStream.showText(to);
                contentStream.endText();

                //add receiver name
                contentStream.beginText();
                contentStream.newLineAtOffset(30, 340);
                contentStream.showText(userReceiverFullName);
                contentStream.endText();

                //add card receiver number
                contentStream.beginText();
                contentStream.newLineAtOffset(30, 320);
                String cardReceivertNumber = cardReceiver.getCard_number();
                contentStream.showText(cardReceivertNumber);
                contentStream.endText();

                //add bank name request
                contentStream.beginText();
                contentStream.newLineAtOffset(30, 300);
                String bankNameReceiver = "EcomBank";
                contentStream.showText(bankNameReceiver);
                contentStream.endText();
               
                //add description text
                contentStream.beginText();
                contentStream.newLineAtOffset(30, 270);
                String description_Text = "Description:";
                contentStream.showText(description_Text);
                contentStream.endText();
                
                //add description
                contentStream.beginText();
                contentStream.newLineAtOffset(30, 250);
                String description = "Giao dich dien ra ngay "+custom_Transaction.getTransaction_date();
                contentStream.showText(description);
                contentStream.endText();
                
                //add note text
                contentStream.beginText();
                contentStream.newLineAtOffset(30, 220);
                String note_Text = "Note:";
                contentStream.showText(note_Text);
                contentStream.endText();
                
                //add note
                contentStream.beginText();
                contentStream.newLineAtOffset(30, 200);
                String note = custom_Transaction.getNotice();
                contentStream.showText(note );
                contentStream.endText();
                
                //add transaction id
                contentStream.beginText();
                contentStream.newLineAtOffset(30, 170);
                String transaction_id = "Transaction ID: " + Integer.toString(custom_Transaction.getTransaction_id());
                contentStream.showText(transaction_id);
                contentStream.endText();
      
                //Closing the content stream
                contentStream.close();  
            }  
            
            //path where the PDF file will be store
            pdfdoc.save("/Users/tuan/NetBeansProjects/banking247/TransferBill_id"+custom_Transaction.getTransaction_id()+".pdf");
            
            //close file
            pdfdoc.close();
        }  
    }
    
    @FXML
    private void cancel() throws IOException{
        if (user.getRole_id() == 3) {
            App.setRoot("Customer_History");
        }
        if (user.getRole_id() ==2) {
            App.setRoot("Employee_TransactionHistory");
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
}
