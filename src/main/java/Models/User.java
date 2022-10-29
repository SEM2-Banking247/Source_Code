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
public class User {
    protected int User_id;
    protected int Role_id;
    protected String Account_number;
    protected String User_name;
    protected String Password;
    protected String email;
    protected String address;
    protected String full_name;
    protected String phone_number;
    protected String CCCD;
    protected String gender;
    protected LocalDate update_at;
    protected LocalDate create_at;
    protected String DOB;
    protected String Pin;

    private static final User instance = new User();
    
    public static User getInstance() {
        return instance;
    }
    
    public User(int User_id, int Role_id, String Account_number, String User_name, String Password, String email, String address, String full_name, String phone_number, String CCCD, String gender, LocalDate update_at, LocalDate create_at, String DOB, String Pin) {
        this.User_id = User_id;
        this.Role_id = Role_id;
        this.Account_number = Account_number;
        this.User_name = User_name;
        this.Password = Password;
        this.email = email;
        this.address = address;
        this.full_name = full_name;
        this.phone_number = phone_number;
        this.CCCD = CCCD;
        this.gender = gender;
        this.update_at = update_at;
        this.create_at = create_at;
        this.DOB = DOB;
        this.Pin = Pin;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int User_id) {
        this.User_id = User_id;
    }

    public String getPin() {
        return Pin;
    }

    public void setPin(String Pin) {
        this.Pin = Pin;
    }

    public User() {
    }

    public int getRole_id() {
        return Role_id;
    }

    public void setRole_id(int Role_id) {
        this.Role_id = Role_id;
    }

    public String getAccount_number() {
        return Account_number;
    }

    public void setAccount_number(String Account_number) {
        this.Account_number = Account_number;
    }

    public String getUser_name() {
        return User_name;
    }

    public void setUser_name(String User_name) {
        this.User_name = User_name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(LocalDate update_at) {
        this.update_at = update_at;
    }

    public LocalDate getCreate_at() {
        return create_at;
    }

    public void setCreate_at(LocalDate create_at) {
        this.create_at = create_at;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }
    
    
}
