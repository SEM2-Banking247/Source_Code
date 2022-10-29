/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author tuan
 */
public class Custom_Transaction{
    protected int Transaction_id;
    protected int Transaction_type_id;
    protected int card_id_request;
    protected int card_id_receiver;
    protected String acc_number_request;
    protected String acc_number_receiver;
    protected String transaction_amount;
    protected String new_balance;
    protected String transaction_date;
    protected String Notice;
    protected String card_number_request;
    protected String card_number_receiver;

    
    private static final Custom_Transaction instance = new Custom_Transaction();
    
    public static Custom_Transaction getInstance() {
        return instance;
    }

    public Custom_Transaction(int Transaction_id, int Transaction_type_id, int card_id_request, int card_id_receiver, String acc_number_request, String acc_number_receiver, String transaction_amount, String new_balance, String transaction_date, String Notice, String card_number_request, String card_number_receiver) {
        this.Transaction_id = Transaction_id;
        this.Transaction_type_id = Transaction_type_id;
        this.card_id_request = card_id_request;
        this.card_id_receiver = card_id_receiver;
        this.acc_number_request = acc_number_request;
        this.acc_number_receiver = acc_number_receiver;
        this.transaction_amount = transaction_amount;
        this.new_balance = new_balance;
        this.transaction_date = transaction_date;
        this.Notice = Notice;
        this.card_number_request = card_number_request;
        this.card_number_receiver = card_number_receiver;
    }

    

    public Custom_Transaction() {
    }

    public String getCard_number_request() {
        return card_number_request;
    }

    public void setCard_number_request(String card_number_request) {
        this.card_number_request = card_number_request;
    }

    public String getCard_number_receiver() {
        return card_number_receiver;
    }

    public void setCard_number_receiver(String card_number_receiver) {
        this.card_number_receiver = card_number_receiver;
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

    public String getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(String transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public String getNew_balance() {
        return new_balance;
    }

    public void setNew_balance(String new_balance) {
        this.new_balance = new_balance;
    }



    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getNotice() {
        return Notice;
    }

    public void setNotice(String Notice) {
        this.Notice = Notice;
    }
    
    
}
