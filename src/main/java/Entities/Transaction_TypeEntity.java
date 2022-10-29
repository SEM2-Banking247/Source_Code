/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Models.Transaction_Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author tuan
 */
public class Transaction_TypeEntity extends BaseEntity{
   static Connection conn = null;
    static PreparedStatement statement = null;
    
    public ArrayList<Transaction_Type> selectAllTransaction_Types() throws SQLException{
        ArrayList<Transaction_Type> Transaction_TypeList = new ArrayList<>();
        
        //start connection
        conn = BaseEntity.open();
        
        //prepare query:
        statement = conn.prepareStatement("select * from transaction_types;");
        
        //execute query
        ResultSet data = statement.executeQuery();
        
        while(data.next()){
            Transaction_Type Transaction_Type = new Transaction_Type(data.getInt("Transaction_type_id"),data.getString("Transaction_type_name"));
            Transaction_TypeList.add(Transaction_Type);
        }
        
        //close connection
        BaseEntity:close();
        
        //return result
        return Transaction_TypeList;
    }
    
    public Transaction_Type selectTransaction_TypeById(int id) throws SQLException{
        //start connection
        conn = BaseEntity.open();
        
        Transaction_Type Transaction_type = new Transaction_Type();
        
        //prepare query
        statement = conn.prepareStatement("select * from transaction_types where Transaction_type_id=?;");
        statement.setInt(1, id);
        
        //execute query
        ResultSet data = statement.executeQuery();
        
        while(data.next()){
            Transaction_type.setTransaction_type_id(data.getInt("Transaction_type_id"));
            Transaction_type.setTransaction_type_name("Transaction_type_name");
            break;
        }
        
        //close connection
        BaseEntity:close();
        
        return Transaction_type;
    }
    
    public void addTransaction_type(String transaction_name) throws SQLException {
        //start connection
        conn = BaseEntity.open();
                
        //prepare query
        statement = conn.prepareStatement("INSERT INTO transaction_types(Transaction_type_name) VALUES(?);");
        statement.setString(1, transaction_name);
        
        //execute query
        statement.executeUpdate();
        
         //close connection
        BaseEntity:close();
    }
    
    public void deleteTransaction_type(int id) throws SQLException {
        //start connection
        conn = BaseEntity.open();
        
        //prepare query
        statement = conn.prepareStatement("delete from transaction_types where Transaction_type_id=?;");
        statement.setInt(1, id);
        
        //execute query
        statement.executeUpdate();
        
        //close connection
        BaseEntity:close();
    }
    
    public void editTransaction_type(Transaction_Type trans) throws SQLException{
        //start connection
        conn = BaseEntity.open();
        
        //prepare query
        statement = conn.prepareStatement("UPDATE transaction_types set Transaction_type_name = (?) where Transaction_type_id=(?);");
        statement.setString(1, trans.getTransaction_type_name());
        statement.setInt(2, trans.getTransaction_type_id());
        
        //execute query
        statement.executeUpdate();
        
         //close connection
        BaseEntity:close();
    }
    
    public ArrayList<Transaction_Type> searchTypesByName(String role_name) throws SQLException {
        //start connection
        conn = BaseEntity.open();
        ArrayList<Transaction_Type> TypeList = new ArrayList<>();

        
         //prepare query
        statement = conn.prepareStatement("select * from transaction_types where Transaction_type_name Like ?;");
        statement.setString(1, role_name);
        
        //execute query
        ResultSet data = statement.executeQuery();
        
        while(data.next()){
            Transaction_Type type = new Transaction_Type();
            type.setTransaction_type_id(data.getInt("Transaction_type_id"));
            type.setTransaction_type_name(data.getString("Transaction_type_name"));
            TypeList.add(type);
        }
        
        //close connection
        BaseEntity:close();
        
        return TypeList;
    }
}
