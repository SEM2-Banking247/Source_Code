/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author tuan
 */
public class Transaction_Type {
    protected int Transaction_type_id;
    protected String Transaction_type_name;

    private static final Transaction_Type instance = new Transaction_Type();
    
    public static Transaction_Type getInstance() {
        return instance;
    }
    
    public Transaction_Type(int Transaction_type_id, String Transaction_type_name) {
        this.Transaction_type_id = Transaction_type_id;
        this.Transaction_type_name = Transaction_type_name;
    }

    public Transaction_Type() {
    }

    public int getTransaction_type_id() {
        return Transaction_type_id;
    }

    public void setTransaction_type_id(int Transaction_type_id) {
        this.Transaction_type_id = Transaction_type_id;
    }

    public String getTransaction_type_name() {
        return Transaction_type_name;
    }

    public void setTransaction_type_name(String Transaction_type_name) {
        this.Transaction_type_name = Transaction_type_name;
    }
    
    
}
