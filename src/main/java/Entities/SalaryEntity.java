/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import static Entities.BaseEntity.close;
import Models.Salary;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author tuan
 */
public class SalaryEntity extends BaseEntity{
    static Connection conn = null;
    static PreparedStatement statement = null;
    
    public ArrayList<Salary> selectAllSalaries() throws SQLException{
        
        ArrayList<Salary> SalaryList = new ArrayList<>();
        
        //start connection
        conn = BaseEntity.open();
        
        //prepare query:
        statement = conn.prepareStatement("select * from salarys;");
        
        //execute query
        ResultSet data = statement.executeQuery();
        
        while(data.next()){
            Salary salary = new Salary();
            salary.setSalary_id(data.getInt("Salary_id"));
            salary.setUser_id(data.getInt("User_id"));
            salary.setSalary_money(data.getFloat("Salary_money"));
            salary.setUpdate_date(LocalDate.parse(data.getString("Update_date")));
            salary.setCreate_date(LocalDate.parse(data.getString("Create_date")));
            salary.setHr_date(data.getString("Hr_date"));
            SalaryList.add(salary);
        }
        
        //close connection
        BaseEntity:close();
        
        //return result
        return SalaryList;
    }
    
    public Salary selectSalaryByUserID(int id) throws SQLException{
        //start connection
        conn = BaseEntity.open();
        
        Salary salary = new Salary();
        
        //prepare query
        statement = conn.prepareStatement("select * from salarys where User_id=?;");
        statement.setInt(1, id);
        
        //execute query
        ResultSet data = statement.executeQuery();
        
        while(data.next()){
            salary.setSalary_id(data.getInt("Salary_id"));
            salary.setUser_id(data.getInt("User_id"));
            salary.setSalary_money(data.getFloat("Salary_money"));
            salary.setUpdate_date(LocalDate.parse(data.getString("Update_date")));
            salary.setCreate_date(LocalDate.parse(data.getString("Create_date")));
            salary.setHr_date(data.getString("Hr_date"));
            break;
        }
        
        //close connection
        BaseEntity:close();
        
        return salary;
    }
    
    public void addSalary(Salary salary) throws SQLException {
        //start connection
        conn = BaseEntity.open();
        
        //prepare query
        statement = conn.prepareStatement("INSERT INTO salarys(User_id,Salary_money,Update_date,Create_date,Hr_date) VALUES(?,?,?,?,?);");
        statement.setInt(1, salary.getUser_id());
        statement.setDouble(2, salary.getSalary_money());
        statement.setDate(3, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        statement.setDate(4, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        statement.setString(5, salary.getHr_date());
        
        //execute query
        statement.executeUpdate();

        //close connection
        BaseEntity:close();
    }
    
    public void deleteSalary(int id) throws SQLException {
        //start connection
        conn = BaseEntity.open();
        
        //prepare query
        statement = conn.prepareStatement("delete from salarys where Salary_id=?;");
        statement.setInt(1, id);
        
        //execute query
        statement.executeUpdate();
        
        //close connection
        BaseEntity:close();
    }
    
    public void updateSalaryByUserID(int id, float amount) throws SQLException{
        //start connection
        conn = BaseEntity.open();
        
        //prepare query
        statement = conn.prepareStatement("UPDATE salarys set Salary_money = ?, Update_date = ? where User_id=?;");
        statement.setFloat(1, amount);
        
        //transform from String to Date:
        statement.setDate(2, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        statement.setInt(3, id);
        
        //execute query:
        statement.executeUpdate();
        
        //close connection
        BaseEntity:close();
    }
    
    public ArrayList<Salary> searchSalaryByUserName(String User_name) throws SQLException {
        //start connection
        conn = BaseEntity.open();
        ArrayList<Salary> salaryList = new ArrayList<>();

        
         //prepare query
        statement = conn.prepareStatement("SELECT salarys.* from salarys JOIN users ON salarys.User_id = users.User_id WHERE users.User_name LIKE ?;");
        statement.setString(1, User_name);
        
        //execute query
        ResultSet data = statement.executeQuery();
        
        while(data.next()){
            Salary salary = new Salary();
            salary.setSalary_id(data.getInt("Salary_id"));
            salary.setUser_id(data.getInt("User_id"));
            salary.setSalary_money(data.getFloat("Salary_money"));
            salary.setUpdate_date(LocalDate.parse(data.getString("Update_date")));
            salary.setCreate_date(LocalDate.parse(data.getString("Create_date")));
            salary.setHr_date(data.getString("Hr_date"));
            salaryList.add(salary);
        }
        
        //close connection
        BaseEntity:close();
        
        return salaryList;
    }
}
