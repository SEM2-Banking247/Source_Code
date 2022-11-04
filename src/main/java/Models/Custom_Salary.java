/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author tuan
 */
public class Custom_Salary {
    protected int Salary_id;
    protected int User_id;
    protected String Full_name;
    protected String Account_number;
    protected String Salary_money;
    protected String Update_date;
    protected String Hr_date;
    protected String Create_date;
    
    private static final Custom_Salary instance = new Custom_Salary();
    
    public static Custom_Salary getInstance() {
        return instance;
    }

    public Custom_Salary() {
    }

    public Custom_Salary(int Salary_id, int User_id, String Full_name, String Account_number, String Salary_money, String Update_date, String Hr_date, String Create_date) {
        this.Salary_id = Salary_id;
        this.User_id = User_id;
        this.Full_name = Full_name;
        this.Account_number = Account_number;
        this.Salary_money = Salary_money;
        this.Update_date = Update_date;
        this.Hr_date = Hr_date;
        this.Create_date = Create_date;
    }

    public String getFull_name() {
        return Full_name;
    }

    public void setFull_name(String Full_name) {
        this.Full_name = Full_name;
    }

    public String getAccount_number() {
        return Account_number;
    }

    public void setAccount_number(String Account_number) {
        this.Account_number = Account_number;
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

    public String getSalary_money() {
        return Salary_money;
    }

    public void setSalary_money(String Salary_money) {
        this.Salary_money = Salary_money;
    }

    public String getUpdate_date() {
        return Update_date;
    }

    public void setUpdate_date(String Update_date) {
        this.Update_date = Update_date;
    }

    public String getHr_date() {
        return Hr_date;
    }

    public void setHr_date(String Hr_date) {
        this.Hr_date = Hr_date;
    }

    public String getCreate_date() {
        return Create_date;
    }

    public void setCreate_date(String Create_date) {
        this.Create_date = Create_date;
    }
    
    
    
}
