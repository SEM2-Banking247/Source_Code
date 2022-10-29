/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Models.Card;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.Date;
//import java.sql.Date;
//import java.text.SimpleDateFormat;
import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 *
 * @author tuan
 */
public class CardEntity extends BaseEntity{
    static Connection conn = null;
    static PreparedStatement statement = null;
    
    public ArrayList<Card> selectAllCards() throws SQLException{
        ArrayList<Card> CardList = new ArrayList<>();
        
        //start connection
        conn = BaseEntity.open();
        
        //prepare query:
        statement = conn.prepareStatement("select * from card_infor;");

        //execute query
        ResultSet cards = statement.executeQuery();

        while(cards.next()){
            Card card = new Card();
            card.setCard_infor_id(cards.getInt("Card_infor_id"));
            card.setUser_id(cards.getInt("User_id"));
            card.setBranch_id(cards.getInt("branch_id"));
            card.setCard_number(cards.getString("Card_number"));
            card.setCard_balance(Double.parseDouble(cards.getString("Card_balance")));
            card.setNotice(cards.getString("Notice"));
            card.setStatus(cards.getString("Status"));
            card.setCreated_at(LocalDate.parse(cards.getString("Create_date")));
            card.setUpdated_at(LocalDate.parse(cards.getString("Update_date")));

            CardList.add(card);
        }
        
        //close connection
        BaseEntity:close();
        
        //return result
        return CardList;
    }
    
    public Card selectCardById(int id) throws SQLException{
        //start connection
        conn = BaseEntity.open();
        
        Card card = new Card();
        
        //prepare query
        statement = conn.prepareStatement("select * from card_infor where Card_infor_id=? and Status = ?;");
        statement.setInt(1, id);
        statement.setString(2, "Active");
        
        //execute query
        ResultSet data = statement.executeQuery();
        while(data.next()){
            card.setCard_infor_id(data.getInt("Card_infor_id"));
            card.setUser_id(data.getInt("User_id"));
            card.setBranch_id(data.getInt("branch_id"));
            card.setCard_number(data.getString("Card_number"));
            card.setCard_balance(Double.parseDouble(data.getString("Card_balance")));
            card.setNotice(data.getString("Notice"));
            card.setStatus(data.getString("Status"));
            card.setCreated_at(LocalDate.parse(data.getString("Create_date")));
            card.setUpdated_at(LocalDate.parse(data.getString("Update_date")));
            break;
        }
        
        //close connection
        BaseEntity:close();
        
        return card;
    }
    
    public Card selectCardByCardNumber(String number) throws SQLException{
        //start connection
        conn = BaseEntity.open();
        
        Card card = new Card();
        
        //prepare query
        statement = conn.prepareStatement("select * from card_infor where Card_number=? and Status=?;");
        statement.setString(1, number);
        statement.setString(2, "Active");

        //execute query
        ResultSet data = statement.executeQuery();
        while(data.next()){
            card.setCard_infor_id(data.getInt("Card_infor_id"));
            card.setUser_id(data.getInt("User_id"));
            card.setBranch_id(data.getInt("branch_id"));
            card.setCard_number(data.getString("Card_number"));
            card.setCard_balance(Double.parseDouble(data.getString("Card_balance")));
            card.setNotice(data.getString("Notice"));
            card.setStatus(data.getString("Status"));
            card.setCreated_at(LocalDate.parse(data.getString("Create_date")));
            card.setUpdated_at(LocalDate.parse(data.getString("Update_date")));
            break;
        }
        
        //close connection
        BaseEntity:close();
        
        return card;
    }
    
    public void addCard(Card card) throws SQLException {
        //start connection
        conn = BaseEntity.open();
        
        //prepare query
        statement = conn.prepareStatement("INSERT INTO card_infor(User_id,branch_id,Card_number,Card_balance,Create_date,Update_date,Notice,Status) VALUES(?,?,?,?,?,?,?,?);");
        statement.setInt(1, card.getUser_id());
        statement.setInt(2, card.getBranch_id());
        statement.setString(3, card.getCard_number());
        statement.setDouble(4, card.getCard_balance());
        statement.setDate(5, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        statement.setDate(6, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        statement.setString(7, card.getNotice());
        statement.setString(8, card.getStatus());
        
        //execute query
        statement.executeUpdate();

        //close connection
        BaseEntity:close();
    }
    
    public void deleteCard(int id) throws SQLException {
        //start connection
        conn = BaseEntity.open();
        
        //prepare query
        statement = conn.prepareStatement("delete from card_infor where Card_infor_id=?;");
        statement.setInt(1, id);
        
        //execute query
        statement.executeUpdate();
        
        //close connection
        BaseEntity:close();
    }
    
    public void updateCardInfo(Card card) throws SQLException{
        //start connection
        conn = BaseEntity.open();
        
        //prepare query
        statement = conn.prepareStatement("UPDATE card_infor set User_id = ?,branch_id=?, Card_number = ?, Update_date = ?, Notice = ?, Status = ? where Card_infor_id = ?;");
        statement.setInt(1, card.getUser_id());
        statement.setInt(2, card.getBranch_id());
        statement.setString(3, card.getCard_number());
        //transform from String to Date:
        statement.setDate(4, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        
        statement.setString(5, card.getNotice());
        statement.setString(6, card.getStatus());
        statement.setInt(7, card.getCard_infor_id());
        
        //execute query:
        statement.executeUpdate();
        
        //close connection
        BaseEntity:close();
    }
    
    public ArrayList<Card> searchCards(String keyword) throws SQLException {
        //start connection
        conn = BaseEntity.open();
        ArrayList<Card> CardList = new ArrayList<>();
        
        //prepare query
        statement = conn.prepareStatement("SELECT card_infor.* FROM card_infor JOIN users ON card_infor.User_id=users.User_id where users.Account_number LIKE ? or users.User_name LIKE ? or card_infor.Notice LIKE ? or card_infor.Status Like ?;");
        statement.setString(1, keyword);
        statement.setString(2, keyword);
        statement.setString(3, keyword);
        statement.setString(4, keyword);
        
        //execute query
        ResultSet cards = statement.executeQuery();
//        ObservableList data = FXCollections.observableArrayList();

        while(cards.next()){
            Card card = new Card();
            card.setCard_infor_id(cards.getInt("Card_infor_id"));
            card.setUser_id(cards.getInt("User_id"));
            card.setBranch_id(cards.getInt("branch_id"));
            card.setCard_number(cards.getString("Card_number"));
            card.setCard_balance(Double.parseDouble(cards.getString("Card_balance")));
            card.setNotice(cards.getString("Notice"));
            card.setStatus(cards.getString("Status"));
            card.setCreated_at(LocalDate.parse(cards.getString("Create_date")));
            card.setUpdated_at(LocalDate.parse(cards.getString("Update_date")));
            CardList.add(card);
        }
        
        //close connection
        BaseEntity:close();
        
        //return result
        return CardList;
    }
    
    public void updateCardBalanceByCardId(int id, double newBalance) throws SQLException{
         //start connection
        conn = BaseEntity.open();
        
        //prepare query
        statement = conn.prepareStatement("UPDATE card_infor set Card_balance = ? where Card_infor_id=?;");
        statement.setDouble(1, newBalance);
        statement.setInt(2, id);

        
        //execute query:
        statement.executeUpdate();
        
        //close connection
        BaseEntity:close();
    }
    
    public ArrayList<Card> selectCardsByUserId(int id) throws SQLException {
        //start connection
        conn = BaseEntity.open();
        ArrayList<Card> dataList = new ArrayList<>();
        
        //prepare query
        statement = conn.prepareStatement("select * from card_infor where User_id = ? and Status=?;");
        statement.setInt(1, id);
        statement.setString(2, "Active");

        //execute query
        ResultSet data = statement.executeQuery();
//        ObservableList data = FXCollections.observableArrayList();

        while(data.next()){
            Card card = new Card();
            card.setCard_infor_id(data.getInt("Card_infor_id"));
            card.setUser_id(data.getInt("User_id"));
            card.setBranch_id(data.getInt("branch_id"));
            card.setCard_number(data.getString("Card_number"));
            card.setCard_balance(Double.parseDouble(data.getString("Card_balance")));
            card.setNotice(data.getString("Notice"));
            card.setStatus(data.getString("Status"));
            card.setCreated_at(LocalDate.parse(data.getString("Create_date")));
            card.setUpdated_at(LocalDate.parse(data.getString("Update_date")));
            dataList.add(card);
        }
        
        //close connection
        BaseEntity:close();
        
        //return result
        return dataList;
    }
    
    public void updateCardStatusByID(int id, String status) throws SQLException{
        //start connection
        conn = BaseEntity.open();

           //prepare query
           statement = conn.prepareStatement("UPDATE card_infor set Status = ? where Card_infor_id=?;");
           statement.setString(1, status);
           statement.setInt(2, id);


           //execute query:
           statement.executeUpdate();

           //close connection
           BaseEntity:close();
    }
}
