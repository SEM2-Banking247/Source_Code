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
public class Salary {
    protected int Salary_id;
    protected int User_id;
    protected float Salary_money;
    protected LocalDate Update_date;
    protected String Hr_date;
    protected LocalDate Create_date;
    
    private static final Salary instance = new Salary();
    
    public static Salary getInstance() {
        return instance;
    }

    public Salary() {
    }

    public Salary(int Salary_id, int User_id, float Salary_money, LocalDate Update_date, String Hr_date, LocalDate Create_date) {
        this.Salary_id = Salary_id;
        this.User_id = User_id;
        this.Salary_money = Salary_money;
        this.Update_date = Update_date;
        this.Hr_date = Hr_date;
        this.Create_date = Create_date;
    }

    public int getSalary_id() {
        return Salary_id;
    }

    public void setSalary_id(int Salary_id) {
        this.Salary_id = Salary_id;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int User_id) {
        this.User_id = User_id;
    }

    public float getSalary_money() {
        return Salary_money;
    }

    public void setSalary_money(float Salary_money) {
        this.Salary_money = Salary_money;
    }

    public LocalDate getUpdate_date() {
        return Update_date;
    }

    public void setUpdate_date(LocalDate Update_date) {
        this.Update_date = Update_date;
    }

    public String getHr_date() {
        return Hr_date;
    }

    public void setHr_date(String Hr_date) {
        this.Hr_date = Hr_date;
    }

    public LocalDate getCreate_date() {
        return Create_date;
    }

    public void setCreate_date(LocalDate Create_date) {
        this.Create_date = Create_date;
    }
    
    
    
}
