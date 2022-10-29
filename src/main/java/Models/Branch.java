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
public class Branch {
    int branch_id;
    public String branch_name;
    public String branch_address;
    LocalDate updated_at;
    LocalDate created_at;
    public String branch_phone;

    private static final Branch instance = new Branch();
    
    public static Branch getInstance() {
        return instance;
    }

    public Branch(int branch_id, String branch_name, String branch_address, LocalDate updated_at, LocalDate created_at, String branch_phone) {
        this.branch_id = branch_id;
        this.branch_name = branch_name;
        this.branch_address = branch_address;
        this.updated_at = updated_at;
        this.created_at = created_at;
        this.branch_phone = branch_phone;
    }

    public Branch() {
    }

    public String getBranch_phone() {
        return branch_phone;
    }

    public void setBranch_phone(String branch_phone) {
        this.branch_phone = branch_phone;
    }
    
    

    public int getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(int branch_id) {
        this.branch_id = branch_id;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getBranch_address() {
        return branch_address;
    }

    public void setBranch_address(String branch_address) {
        this.branch_address = branch_address;
    }

    public LocalDate getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDate updated_at) {
        this.updated_at = updated_at;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }


    
    
}
