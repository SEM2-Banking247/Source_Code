/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author tuan
 */
public class Card {
    int card_infor_id;
    int user_id;
    int branch_id;
    String card_number;
    Double card_balance;
    String notice;
    LocalDate created_at;
    String status;
    LocalDate updated_at;
    
    public Card(int card_infor_id, int user_id, int branch_id, String card_number, Double card_balance, String notice, String Pin_number, String status) {
        LocalDate date = LocalDate.now(); 
        
        this.card_infor_id = card_infor_id;
        this.user_id = user_id;
        this.branch_id = branch_id;
        this.card_number = card_number;
        this.card_balance = card_balance;
        this.notice = notice;
        this.status = status;
        this.created_at = date;
        this.updated_at = date;
    }

    public int getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(int branch_id) {
        this.branch_id = branch_id;
    }

    public int getCard_infor_id() {
        return card_infor_id;
    }

    public void setCard_infor_id(int card_infor_id) {
        this.card_infor_id = card_infor_id;
    }
    
    private static final Card instance = new Card();
    
    public static Card getInstance() {
        return instance;
    }

    public Card() {
    }
    
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public Double getCard_balance() {
        return card_balance;
    }

    public void setCard_balance(Double card_balance) {
        this.card_balance = card_balance;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
      
        this.created_at = created_at;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDate updated_at) {
        
        this.updated_at = updated_at;
    }
}
