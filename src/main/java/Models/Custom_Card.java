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
public class Custom_Card {
    int card_infor_id;
    int user_id;
    int branch_id;
    String account_number;
    String branch_name;
    String card_number;
    String card_balance;
    String notice;
    String created_at;
    String status;
    String updated_at;
    String Pin_number;

    
    private static final Custom_Card instance = new Custom_Card();
    
    public static Custom_Card getInstance() {
        return instance;
    }

    public Custom_Card(int card_infor_id, int user_id, int branch_id, String account_number, String branch_name, String card_number, String card_balance, String notice, String created_at, String status, String updated_at, String Pin_number) {
        this.card_infor_id = card_infor_id;
        this.user_id = user_id;
        this.branch_id = branch_id;
        this.account_number = account_number;
        this.branch_name = branch_name;
        this.card_number = card_number;
        this.card_balance = card_balance;
        this.notice = notice;
        this.created_at = created_at;
        this.status = status;
        this.updated_at = updated_at;
        this.Pin_number = Pin_number;
    }
    
    

    public Custom_Card() {
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    
    
    public int getCard_infor_id() {
        return card_infor_id;
    }

    public void setCard_infor_id(int card_infor_id) {
        this.card_infor_id = card_infor_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(int branch_id) {
        this.branch_id = branch_id;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getCard_balance() {
        return card_balance;
    }

    public void setCard_balance(String card_balance) {
        this.card_balance = card_balance;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getPin_number() {
        return Pin_number;
    }

    public void setPin_number(String Pin_number) {
        this.Pin_number = Pin_number;
    }
    
    
}
