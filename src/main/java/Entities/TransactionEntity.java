/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import static Entities.BaseEntity.close;
import static Entities.BaseEntity.conn;
import static Entities.BaseEntity.statement;
import Models.Transaction;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author tuan
 */
public class TransactionEntity extends BaseEntity{
    public void addTransaction(Transaction transaction) throws SQLException {
        //start connection
        conn = BaseEntity.open();
        
        //prepare query
        statement = conn.prepareStatement("INSERT INTO transactions(Transaction_type_id,card_id_request,card_id_receiver,acc_number_request,acc_number_receiver,transaction_amount,New_balance_request,New_balance_receiver,transaction_date,Notice) VALUES(?,?,?,?,?,?,?,?,?,?);");
        statement.setInt(1, transaction.getTransaction_type_id());
        statement.setInt(2, transaction.getCard_id_request());
        statement.setInt(3, transaction.getCard_id_receiver());
        statement.setString(4, transaction.getAcc_number_request());
        statement.setString(5, transaction.getAcc_number_receiver());
        statement.setDouble(6, transaction.getTransaction_amount());
        statement.setDouble(7, transaction.getNew_balance_request());
        statement.setDouble(8, transaction.getNew_balance_receiver());
        statement.setDate(9, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        statement.setString(10, transaction.getNotice());
        
        //execute query
        statement.executeUpdate();

        //close connection
        BaseEntity:close();
    }
    
     public void deleteTransaction(int id) throws SQLException {
        //start connection
        conn = BaseEntity.open();
        
        //prepare query
        statement = conn.prepareStatement("delete from transactions where Transaction_id=?;");
        statement.setInt(1, id);
        
        //execute query
        statement.executeUpdate();
        
        //close connection
        BaseEntity.close();
    }
     
     public void updateTransaction(Transaction transaction) throws SQLException{
        //start connection
        conn = BaseEntity.open();
        
        //prepare query
        statement = conn.prepareStatement("UPDATE transactions set Transaction_type_id = (?), Card_id_request = (?), Card_id_receiver = (?), Acc_number_request = (?), Acc_number_receiver = (?), Transaction_amount = (?), New_balance_request = (?), New_balance_receiver=(?), Notice = (?) where Transaction_id=(?);");
        statement.setInt(1, transaction.getTransaction_type_id());
        statement.setInt(2, transaction.getCard_id_request());
        statement.setInt(3, transaction.getCard_id_receiver());
        statement.setString(4, transaction.getAcc_number_request());
        statement.setString(5, transaction.getAcc_number_receiver());
        statement.setDouble(6, transaction.getTransaction_amount());
        statement.setDouble(7, transaction.getNew_balance_request());
        statement.setDouble(8, transaction.getNew_balance_receiver());
        statement.setString(9, transaction.getNotice());
        statement.setInt(10, transaction.getTransaction_id());
        
        //execute query:
        statement.executeUpdate();
        
        //close connection
        BaseEntity:close();
    }
    
    public ArrayList<Transaction> selectAllTransactions() throws  SQLException {
        //start connection
        conn = BaseEntity.open();
        ArrayList<Transaction> Transactions = new ArrayList<>();

        
        //prepare query
        statement = conn.prepareStatement("Select * from transactions;");
        
        //execute query
        ResultSet data = statement.executeQuery();

        while(data.next()){
            Transaction transaction = new Transaction();
            transaction.setTransaction_id(data.getInt("Transaction_id"));
            transaction.setTransaction_type_id(data.getInt("Transaction_type_id"));
            transaction.setCard_id_request(data.getInt("Card_id_request"));
            transaction.setCard_id_receiver(data.getInt("Card_id_receiver"));
            transaction.setAcc_number_request(data.getString("Acc_number_request"));
            transaction.setAcc_number_receiver(data.getString("Acc_number_receiver"));
            transaction.setTransaction_amount(data.getDouble("Transaction_amount"));
            transaction.setNew_balance_request(data.getDouble("New_balance_request"));
            transaction.setNew_balance_receiver(data.getDouble("New_balance_receiver"));
            transaction.setNotice(data.getString("Notice"));
            transaction.setTransaction_date(LocalDate.parse(data.getString("Transaction_date")));
            Transactions.add(transaction);
        }
        
        //close connection
        BaseEntity:close();
        
        //return result
        return Transactions;
    }
    
    public ArrayList<Transaction> searchTransactionsByAccountNumber(String acc_number) throws  SQLException {
        //start connection
        conn = BaseEntity.open();
        ArrayList<Transaction> Transactions = new ArrayList<>();

        
        //prepare query
        statement = conn.prepareStatement("Select * from transactions where acc_number_receiver = ? or acc_number_request = ?;");
        statement.setString(1, acc_number);
        statement.setString(2, acc_number);
        
        //execute query
        ResultSet data = statement.executeQuery();

        while(data.next()){
            Transaction transaction = new Transaction();
            transaction.setTransaction_id(data.getInt("Transaction_id"));
            transaction.setTransaction_type_id(data.getInt("Transaction_type_id"));
            transaction.setCard_id_request(data.getInt("Card_id_request"));
            transaction.setCard_id_receiver(data.getInt("Card_id_receiver"));
            transaction.setAcc_number_request(data.getString("Acc_number_request"));
            transaction.setAcc_number_receiver(data.getString("Acc_number_receiver"));
            transaction.setTransaction_amount(data.getDouble("Transaction_amount"));
            transaction.setNew_balance_request(data.getDouble("New_balance_request"));
            transaction.setNew_balance_receiver(data.getDouble("New_balance_receiver"));
            transaction.setNotice(data.getString("Notice"));
            transaction.setTransaction_date(LocalDate.parse(data.getString("Transaction_date")));
           
            Transactions.add(transaction);
        }
        
        //close connection
        BaseEntity:close();
        
        //return result
        return Transactions;
    }
    
    public ArrayList<Transaction> searchTransactionsByCardID(int id) throws  SQLException {
        //start connection
        conn = BaseEntity.open();
        ArrayList<Transaction> Transactions = new ArrayList<>();

        
        //prepare query
        statement = conn.prepareStatement("Select * from transactions where card_id_request = ? or card_id_receiver = ?;");
        statement.setInt(1, id);
        statement.setInt(2, id);
        
        //execute query
        ResultSet data = statement.executeQuery();

        while(data.next()){
            Transaction transaction = new Transaction();
            transaction.setTransaction_id(data.getInt("Transaction_id"));
            transaction.setTransaction_type_id(data.getInt("Transaction_type_id"));
            transaction.setCard_id_request(data.getInt("Card_id_request"));
            transaction.setCard_id_receiver(data.getInt("Card_id_receiver"));
            transaction.setAcc_number_request(data.getString("Acc_number_request"));
            transaction.setAcc_number_receiver(data.getString("Acc_number_receiver"));
            transaction.setTransaction_amount(data.getDouble("Transaction_amount"));
            transaction.setNew_balance_request(data.getDouble("New_balance_request"));
            transaction.setNew_balance_receiver(data.getDouble("New_balance_receiver"));
            transaction.setNotice(data.getString("Notice"));
            transaction.setTransaction_date(LocalDate.parse(data.getString("Transaction_date")));
           
            Transactions.add(transaction);
        }
        
        //close connection
        BaseEntity:close();
        
        //return result
        return Transactions;
    }
    
    public Transaction searchTransactionByID(int id) throws  SQLException {
        //start connection
        conn = BaseEntity.open();
        Transaction transaction = new Transaction();

        
        //prepare query
        statement = conn.prepareStatement("Select * from transactions where Transaction_id = ?;");
        statement.setInt(1, id);
        
        //execute query
        ResultSet data = statement.executeQuery();

        while(data.next()){
            transaction.setTransaction_id(data.getInt("Transaction_id"));
            transaction.setTransaction_type_id(data.getInt("Transaction_type_id"));
            transaction.setCard_id_request(data.getInt("Card_id_request"));
            transaction.setCard_id_receiver(data.getInt("Card_id_receiver"));
            transaction.setAcc_number_request(data.getString("Acc_number_request"));
            transaction.setAcc_number_receiver(data.getString("Acc_number_receiver"));
            transaction.setTransaction_amount(data.getDouble("Transaction_amount"));
            transaction.setNew_balance_request(data.getDouble("New_balance_request"));
            transaction.setNew_balance_receiver(data.getDouble("New_balance_receiver"));
            transaction.setNotice(data.getString("Notice"));
            transaction.setTransaction_date(LocalDate.parse(data.getString("Transaction_date")));
            break;
        }
        
        //close connection
        BaseEntity:close();
        
        //return result
        return transaction;
    }
}
