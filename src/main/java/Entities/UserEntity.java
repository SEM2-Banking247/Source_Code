/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;
import static Entities.CardEntity.conn;
import static Entities.CardEntity.statement;
import Models.Custom_Salary;
import Models.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

/**
 *
 * @author tuan
 */
public class UserEntity extends BaseEntity{
    private final SalaryEntity salaryEntity = new SalaryEntity();
    
    public User signIn(String username, String password) throws SQLException {
        //start connection
        conn = BaseEntity.open();
        
        User user = new User();
        
        //prepare query
        statement = conn.prepareStatement("select * from users where User_name = ? and Password = ?;");
        statement.setString(1, username);
        statement.setString(2, password);
        
        //execute query
        ResultSet data = statement.executeQuery();
        while(data.next()){
            user.setUser_id(data.getInt("User_id"));
            user.setRole_id(data.getInt("Role_id"));
            user.setAccount_number(data.getString("Account_number"));
            user.setUser_name(data.getString("User_name"));
            user.setPassword(data.getString("Password"));
            user.setEmail(data.getString("Email"));
            user.setAddress(data.getString("Address"));
            user.setFull_name(data.getString("Full_name"));
            user.setPhone_number(data.getString("Phone_number"));
            user.setCCCD(data.getString("CCCD"));
            user.setGender(data.getString("Gender"));
            user.setDOB(data.getString("DOB"));
            user.setPin(data.getString("Pin"));
            //neu getString gia tri null thi se loi
            user.setCreate_at(LocalDate.parse(data.getString("Create_date")));
            user.setUpdate_at(LocalDate.parse(data.getString("Update_date")));
            break;
        }
        
        //close connection
        BaseEntity:close();
        
        return user;
    }
    
    public void addUser(User user) throws SQLException {
        Random rand = new Random();
        //start connection
        conn = BaseEntity.open();
        
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        
        //prepare query
        statement = conn.prepareStatement("INSERT INTO users(Role_id,Account_number,User_name,Email,Password,Full_name,Address,Phone_number,CCCD,Gender,Create_date,DOB,Pin,Update_date) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
        statement.setInt(1, user.getRole_id());
        statement.setString(2, String.format("%06d", number));
        statement.setString(3, user.getUser_name());
        statement.setString(4, user.getEmail());
        statement.setString(5, user.getPassword());
        statement.setString(6, user.getFull_name());
        statement.setString(7, user.getAddress());
        statement.setString(8, user.getPhone_number());
        statement.setString(9, user.getCCCD());
        statement.setString(10, user.getGender());
        statement.setDate(11, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        statement.setString(12, user.getDOB());
        statement.setString(13, Integer.toString(rand.nextInt((9999 - 100) + 1) + 10));
        statement.setDate(14, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        
        //execute query
        statement.executeUpdate();
        
        //close connection
        BaseEntity:close();
        
    }
    
     public void deleteUser(int id) throws SQLException {
        //start connection
        conn = BaseEntity.open();
        
        //prepare query
        statement = conn.prepareStatement("delete from users where User_id=?;");
        statement.setInt(1, id);
        
        //execute query
        statement.executeUpdate();
        
        //close connection
        BaseEntity.close();
    }
     
     public void updateUserInfor(User user) throws SQLException{
        //start connection
        conn = BaseEntity.open();
        
        //prepare query
        statement = conn.prepareStatement("UPDATE users set User_name = (?), Email = (?), Full_name = (?), Address = (?), Phone_number = (?), CCCD = (?), Gender = (?), DOB = (?), Update_date = (?) where User_id=(?);");
        statement.setString(1, user.getUser_name());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getFull_name());
        statement.setString(4, user.getAddress());
        statement.setString(5, user.getPhone_number());
        statement.setString(6, user.getCCCD());
        statement.setString(7, user.getGender());
        statement.setString(8, user.getDOB());
        statement.setDate(9, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        statement.setInt(10, user.getUser_id());
        
        //execute query:
        statement.executeUpdate();
        
        //close connection
        BaseEntity:close();
        
    }
     
    public void updatePinNumber(String newPinNumber, int id) throws SQLException {
         //start connection
        conn = BaseEntity.open();
        
        //prepare query
        statement = conn.prepareStatement("UPDATE users set Pin = ?, Update_date = ? where User_id=?;");
        statement.setString(1, newPinNumber);
        statement.setDate(2, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        statement.setInt(3, id);
        
        //execute query:
        statement.executeUpdate();
        
        //close connection
        BaseEntity:close();
    }
     
    public void updatePassword(String newPassword, int id) throws SQLException {
         //start connection
        conn = BaseEntity.open();
        
        //prepare query
        statement = conn.prepareStatement("UPDATE users set Password = ?, Update_date = ? where User_id=?;");
        statement.setString(1, newPassword);
        statement.setDate(2, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        statement.setInt(3, id);
        
        //execute query:
        statement.executeUpdate();
        
        //close connection
        BaseEntity:close();
    }
     
    public ArrayList<User> searchUserByName(String Name) throws  SQLException {
        //start connection
        conn = BaseEntity.open();
        ArrayList<User> UserList = new ArrayList<>();

        
        //prepare query
        statement = conn.prepareStatement("Select * from users where User_name LIKE ?;");
        statement.setString(1, Name);
        
        //execute query
        ResultSet data = statement.executeQuery();

        while(data.next()){
            User user = new User();
            user.setUser_id(data.getInt("User_id"));
            user.setRole_id(data.getInt("Role_id"));
            user.setAccount_number(data.getString("Account_number"));
            user.setUser_name(data.getString("User_name"));
            user.setEmail(data.getString("Email"));
            user.setPassword(data.getString("Password"));
            user.setFull_name(data.getString("Full_name"));
            user.setAddress(data.getString("Address"));
            user.setPhone_number(data.getString("Phone_number"));
            user.setCCCD(data.getString("CCCD"));
            user.setGender(data.getString("Gender"));
            user.setDOB(data.getString("DOB"));
            user.setPin(data.getString("Pin"));
            user.setCreate_at(LocalDate.parse(data.getString("Create_date")));
            user.setUpdate_at(LocalDate.parse(data.getString("Update_date")));
            UserList.add(user);
        }
        
        //close connection
        BaseEntity:close();
        
        //return result
        return UserList;
    }
    
    public ArrayList<User> selectUsersByRole(int role) throws  SQLException {
        //start connection
        conn = BaseEntity.open();
        ArrayList<User> UserList = new ArrayList<>();

        
        //prepare query
        statement = conn.prepareStatement("Select * from users where Role_id = ?;");
        statement.setInt(1, role);
        
        //execute query
        ResultSet data = statement.executeQuery();

        while(data.next()){
            User user = new User();
            user.setUser_id(data.getInt("User_id"));
            user.setRole_id(data.getInt("Role_id"));
            user.setAccount_number(data.getString("Account_number"));
            user.setUser_name(data.getString("User_name"));
            user.setEmail(data.getString("Email"));
            user.setPassword(data.getString("Password"));
            user.setFull_name(data.getString("Full_name"));
            user.setAddress(data.getString("Address"));
            user.setPhone_number(data.getString("Phone_number"));
            user.setCCCD(data.getString("CCCD"));
            user.setGender(data.getString("Gender"));
            user.setDOB(data.getString("DOB"));
            user.setPin(data.getString("Pin"));
            user.setCreate_at(LocalDate.parse(data.getString("Create_date")));
            user.setUpdate_at(LocalDate.parse(data.getString("Update_date")));
            UserList.add(user);
        }
        
        //close connection
        BaseEntity:close();
        
        //return result
        return UserList;
    }
    
    public ArrayList<User> selectAllUsers() throws  SQLException {
        //start connection
        conn = BaseEntity.open();
        ArrayList<User> UserList = new ArrayList<>();

        
        //prepare query
        statement = conn.prepareStatement("Select * from users;");
        
        //execute query
        ResultSet data = statement.executeQuery();

        while(data.next()){
            User user = new User();
            user.setUser_id(data.getInt("User_id"));
            user.setRole_id(data.getInt("Role_id"));
            user.setAccount_number(data.getString("Account_number"));
            user.setUser_name(data.getString("User_name"));
            user.setEmail(data.getString("Email"));
            user.setPassword(data.getString("Password"));
            user.setFull_name(data.getString("Full_name"));
            user.setAddress(data.getString("Address"));
            user.setPhone_number(data.getString("Phone_number"));
            user.setCCCD(data.getString("CCCD"));
            user.setGender(data.getString("Gender"));
            user.setDOB(data.getString("DOB"));
            user.setPin(data.getString("Pin"));
            user.setCreate_at(LocalDate.parse(data.getString("Create_date")));
            user.setUpdate_at(LocalDate.parse(data.getString("Update_date")));
            UserList.add(user);
        }
        
        //close connection
        BaseEntity:close();
        
        //return result
        return UserList;
    }
    
    public ArrayList<Custom_Salary> selectAllUsersPlusSalary() throws  SQLException {
        //start connection
        conn = BaseEntity.open();
        ArrayList<Custom_Salary> SalaryList = new ArrayList<>();

        
        //prepare query
        statement = conn.prepareStatement("SELECT users.User_id , users.Account_number, users.Full_name, salarys.Salary_id, salarys.Salary_money, salarys.Hr_date, salarys.Update_date, salarys.Create_date FROM users LEFT JOIN salarys ON users.User_id=salarys.User_id;");
        
        //execute query
        ResultSet data = statement.executeQuery();

        while(data.next()){
            Custom_Salary salary = new Custom_Salary();
            salary.setSalary_id(data.getInt("Salary_id"));
            salary.setUser_id(data.getInt("User_id"));
            salary.setAccount_number(data.getString("Account_Number"));
            salary.setFull_name(data.getString("Full_name"));
            if (data.getDate("Update_date") != null) {
                salary.setUpdate_date(data.getDate("Update_date").toString());
            }
            salary.setSalary_money(String.format("%.0f",data.getFloat("Salary_money")));
            salary.setHr_date(data.getString("Hr_date"));
            if (data.getDate("Create_date") != null) {
                salary.setCreate_date(data.getDate("Create_date").toString());
            }
            SalaryList.add(salary);
        }
        
        //close connection
        BaseEntity:close();
        
        //return result
        return SalaryList;
    }
    
    public ArrayList<User> selectAllCustomers() throws  SQLException {
        //start connection
        conn = BaseEntity.open();
        ArrayList<User> UserList = new ArrayList<>();

        
        //prepare query
        statement = conn.prepareStatement("Select * from users where Role_id= 3;");
        
        //execute query
        ResultSet data = statement.executeQuery();

        while(data.next()){
            User user = new User();
            user.setUser_id(data.getInt("User_id"));
            user.setRole_id(data.getInt("Role_id"));
            user.setAccount_number(data.getString("Account_number"));
            user.setUser_name(data.getString("User_name"));
            user.setEmail(data.getString("Email"));
            user.setPassword(data.getString("Password"));
            user.setFull_name(data.getString("Full_name"));
            user.setAddress(data.getString("Address"));
            user.setPhone_number(data.getString("Phone_number"));
            user.setCCCD(data.getString("CCCD"));
            user.setGender(data.getString("Gender"));
            user.setDOB(data.getString("DOB"));
            user.setPin(data.getString("Pin"));
            user.setCreate_at(LocalDate.parse(data.getString("Create_date")));
            user.setUpdate_at(LocalDate.parse(data.getString("Update_date")));
            UserList.add(user);
        }
        
        //close connection
        BaseEntity:close();
        
        //return result
        return UserList;
    }
    
    public ArrayList<Custom_Salary> searchUsersPlusSalary(String keyword) throws  SQLException {
        //start connection
        conn = BaseEntity.open();
        ArrayList<Custom_Salary> SalaryList = new ArrayList<>();

        
        //prepare query
        statement = conn.prepareStatement("SELECT users.User_id , users.Account_number, users.Full_name, salarys.Salary_id, salarys.Salary_money, salarys.Hr_date, salarys.Update_date, salarys.Create_date FROM users LEFT JOIN salarys ON users.User_id=salarys.User_id where users.Full_name LIKE ?;");
        statement.setString(1, keyword);
        
        //execute query
        ResultSet data = statement.executeQuery();

        while(data.next()){
            Custom_Salary salary = new Custom_Salary();
            salary.setSalary_id(data.getInt("Salary_id"));
            salary.setUser_id(data.getInt("User_id"));
            salary.setAccount_number(data.getString("Account_Number"));
            salary.setFull_name(data.getString("Full_name"));
            if (data.getDate("Update_date") != null) {
                salary.setUpdate_date(data.getDate("Update_date").toString());
            }
            salary.setSalary_money(String.format("%.0f",data.getFloat("Salary_money")));
            salary.setHr_date(data.getString("Hr_date"));
            if (data.getDate("Create_date") != null) {
                salary.setCreate_date(data.getDate("Create_date").toString());
            }
            SalaryList.add(salary);
        }
        
        //close connection
        BaseEntity:close();
        
        //return result
        return SalaryList;
    }
    
    public User selectUserById(int id) throws SQLException{
        //start connection
        conn = BaseEntity.open();
        
        User user = new User();
        
        //prepare query
        statement = conn.prepareStatement("select * from users where User_id=?;");
        statement.setInt(1, id);
        
        //execute query
        ResultSet data = statement.executeQuery();
        while(data.next()){
            user.setUser_id(data.getInt("User_id"));
            user.setRole_id(data.getInt("Role_id"));
            user.setAccount_number(data.getString("Account_number"));
            user.setUser_name(data.getString("User_name"));
            user.setEmail(data.getString("Email"));
            user.setPassword(data.getString("Password"));
            user.setFull_name(data.getString("Full_name"));
            user.setAddress(data.getString("Address"));
            user.setPhone_number(data.getString("Phone_number"));
            user.setCCCD(data.getString("CCCD"));
            user.setGender(data.getString("Gender"));
            user.setDOB(data.getString("DOB"));
            user.setPin(data.getString("Pin"));
            user.setCreate_at(LocalDate.parse(data.getString("Create_date")));
            user.setUpdate_at(LocalDate.parse(data.getString("Update_date")));
            break;
        }
        
        //close connection
        BaseEntity:close();
        
        return user;
    }
    
    public User selectUserByAccNumber(String number) throws SQLException{
        //start connection
        conn = BaseEntity.open();
        
        User user = new User();
        
        //prepare query
        statement = conn.prepareStatement("select * from users where Account_number=?;");
        statement.setString(1, number);
        
        //execute query
        ResultSet data = statement.executeQuery();
        while(data.next()){
            user.setUser_id(data.getInt("User_id"));
            user.setRole_id(data.getInt("Role_id"));
            user.setAccount_number(data.getString("Account_number"));
            user.setUser_name(data.getString("User_name"));
            user.setEmail(data.getString("Email"));
            user.setPassword(data.getString("Password"));
            user.setFull_name(data.getString("Full_name"));
            user.setAddress(data.getString("Address"));
            user.setPhone_number(data.getString("Phone_number"));
            user.setCCCD(data.getString("CCCD"));
            user.setGender(data.getString("Gender"));
            user.setDOB(data.getString("DOB"));
            user.setPin(data.getString("Pin"));
            user.setCreate_at(LocalDate.parse(data.getString("Create_date")));
            user.setUpdate_at(LocalDate.parse(data.getString("Update_date")));
            break;
        }
        
        //close connection
        BaseEntity:close();
        
        return user;
    }
    
    public User selectUserByEmail(String email) throws SQLException{
        //start connection
        conn = BaseEntity.open();
        
        User user = new User();
        
        //prepare query
        statement = conn.prepareStatement("select * from users where Email=?;");
        statement.setString(1, email);
        
        //execute query
        ResultSet data = statement.executeQuery();
        while(data.next()){
            user.setUser_id(data.getInt("User_id"));
            user.setRole_id(data.getInt("Role_id"));
            user.setAccount_number(data.getString("Account_number"));
            user.setUser_name(data.getString("User_name"));
            user.setEmail(data.getString("Email"));
            user.setPassword(data.getString("Password"));
            user.setFull_name(data.getString("Full_name"));
            user.setAddress(data.getString("Address"));
            user.setPhone_number(data.getString("Phone_number"));
            user.setCCCD(data.getString("CCCD"));
            user.setGender(data.getString("Gender"));
            user.setDOB(data.getString("DOB"));
            user.setPin(data.getString("Pin"));
            user.setCreate_at(LocalDate.parse(data.getString("Create_date")));
            user.setUpdate_at(LocalDate.parse(data.getString("Update_date")));
            break;
        }
        
        //close connection
        BaseEntity:close();
        
        return user;
    }
    
    public User selectUserByName(String name) throws SQLException{
        //start connection
        conn = BaseEntity.open();
        
        User user = new User();
        
        //prepare query
        statement = conn.prepareStatement("select * from users where User_name=?;");
        statement.setString(1, name);
        
        //execute query
        ResultSet data = statement.executeQuery();
        while(data.next()){
            user.setUser_id(data.getInt("User_id"));
            user.setRole_id(data.getInt("Role_id"));
            user.setAccount_number(data.getString("Account_number"));
            user.setUser_name(data.getString("User_name"));
            user.setEmail(data.getString("Email"));
            user.setPassword(data.getString("Password"));
            user.setFull_name(data.getString("Full_name"));
            user.setAddress(data.getString("Address"));
            user.setPhone_number(data.getString("Phone_number"));
            user.setCCCD(data.getString("CCCD"));
            user.setGender(data.getString("Gender"));
            user.setDOB(data.getString("DOB"));
            user.setPin(data.getString("Pin"));
            user.setCreate_at(LocalDate.parse(data.getString("Create_date")));
            user.setUpdate_at(LocalDate.parse(data.getString("Update_date")));
            break;
        }
        
        //close connection
        BaseEntity:close();
        
        return user;
    }
}
