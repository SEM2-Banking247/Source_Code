/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.time.LocalDate;

/**
 *
 * @author tuan
 */
public class Transaction{
    protected int Transaction_id;
    protected int Transaction_type_id;
    protected int card_id_request;
    protected int card_id_receiver;
    protected String acc_number_request;
    protected String acc_number_receiver;
    protected double transaction_amount;
    protected double new_balance_request;
    protected double new_balance_receiver;
    protected LocalDate transaction_date;
    protected String Notice;

    
    private static final Transaction instance = new Transaction();
    
    public static Transaction getInstance() {
        return instance;
    }
    
    public Transaction(int Transaction_id, int Transaction_type_id, int card_id_request, int card_id_receiver, String acc_number_request, String acc_number_receiver, double transaction_amount, double new_balance_request, double new_balance_receiver, LocalDate transaction_date, String Notice) {
        this.Transaction_id = Transaction_id;
        this.Transaction_type_id = Transaction_type_id;
        this.card_id_request = card_id_request;
        this.card_id_receiver = card_id_receiver;
        this.acc_number_request = acc_number_request;
        this.acc_number_receiver = acc_number_receiver;
        this.transaction_amount = transaction_amount;
        this.new_balance_request = new_balance_request;
        this.new_balance_receiver = new_balance_receiver;
        this.transaction_date = transaction_date;
        this.Notice = Notice;
    }

    public Transaction() {
    }

    public double getNew_balance_receiver() {
        return new_balance_receiver;
    }

    public void setNew_balance_receiver(double new_balance_receiver) {
        this.new_balance_receiver = new_balance_receiver;
    }

    public int getTransaction_id() {
        return Transaction_id;
    }

    public void setTransaction_id(int Transaction_id) {
        this.Transaction_id = Transaction_id;
    }

    public int getTransaction_type_id() {
        return Transaction_type_id;
    }

    public void setTransaction_type_id(int Transaction_type_id) {
        this.Transaction_type_id = Transaction_type_id;
    }

    public int getCard_id_request() {
        return card_id_request;
    }

    public void setCard_id_request(int card_id_request) {
        this.card_id_request = card_id_request;
    }

    public int getCard_id_receiver() {
        return card_id_receiver;
    }

    public void setCard_id_receiver(int card_id_receiver) {
        this.card_id_receiver = card_id_receiver;
    }

    public String getAcc_number_request() {
        return acc_number_request;
    }

    public void setAcc_number_request(String acc_number_request) {
        this.acc_number_request = acc_number_request;
    }

    public String getAcc_number_receiver() {
        return acc_number_receiver;
    }

    public void setAcc_number_receiver(String acc_number_receiver) {
        this.acc_number_receiver = acc_number_receiver;
    }

    public double getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(double transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public double getNew_balance_request() {
        return new_balance_request;
    }

    public void setNew_balance_request(double new_balance_request) {
        this.new_balance_request = new_balance_request;
    }

    public LocalDate getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(LocalDate transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getNotice() {
        return Notice;
    }

    public void setNotice(String Notice) {
        this.Notice = Notice;
    }

}
