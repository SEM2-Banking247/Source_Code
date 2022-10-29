
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Models.Branch;
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
 */public class BranchEntity extends  BaseEntity{
    static Connection conn = null;
    static PreparedStatement statement = null;
    
    public ArrayList<Branch> getAllBranches() throws SQLException{
        ArrayList<Branch> BranchList = new ArrayList<>();
        
        //start connection
        conn = BaseEntity.open();
        
        //prepare query:
        statement = conn.prepareStatement("select * from branch_list;");
       
        //execute query
        ResultSet data = statement.executeQuery();
//        ObservableList data = FXCollections.observableArrayList();

        while(data.next()){
            Branch branch = new Branch();
            branch.setBranch_id(data.getInt("branch_id"));
            branch.setBranch_name(data.getString("branch_name"));
            branch.setBranch_address(data.getString("branch_address"));
            branch.setUpdated_at(LocalDate.parse(data.getString("update_date")));
            branch.setCreated_at(LocalDate.parse(data.getString("create_date")));
            branch.setBranch_phone(data.getString("branch_phone"));
            BranchList.add(branch);
        }
        
        //close connection
        BaseEntity:close();
        
        //return result
        return BranchList;
    } 
    
    public Branch selectBranchById(int id) throws SQLException{
        //start connection
        conn = BaseEntity.open();
        
        Branch branch = new Branch();
        
        //prepare query
        statement = conn.prepareStatement("select * from branch_list where branch_id = ?;");
        statement.setInt(1, id);
        
        //execute query
        ResultSet data = statement.executeQuery();
        while(data.next()){
            branch.setBranch_id(data.getInt("branch_id"));
            branch.setBranch_name(data.getString("branch_name"));
            branch.setBranch_address(data.getString("branch_address"));
            branch.setUpdated_at(LocalDate.parse(data.getString("update_date")));
            branch.setCreated_at(LocalDate.parse(data.getString("create_date")));
            branch.setBranch_phone(data.getString("branch_phone"));
            break;
        }
        
        //close connection
        BaseEntity:close();
        
        return branch;
    }
    
    public Branch selectBranchByName(String name) throws SQLException{
        //start connection
        conn = BaseEntity.open();
        
        Branch branch = new Branch();
        
        //prepare query
        statement = conn.prepareStatement("select * from branch_list where branch_name = ?;");
        statement.setString(1, name);
        
        //execute query
        ResultSet data = statement.executeQuery();
        while(data.next()){
            branch.setBranch_id(data.getInt("branch_id"));
            branch.setBranch_name(data.getString("branch_name"));
            branch.setBranch_address(data.getString("branch_address"));
            branch.setBranch_phone(data.getString("branch_phone"));
            branch.setUpdated_at(LocalDate.parse(data.getString("update_date")));
            branch.setCreated_at(LocalDate.parse(data.getString("create_date")));
            break;
        }
        
        //close connection
        BaseEntity:close();
        
        return branch;
    }
    
    public void addBranch(Branch branch) throws SQLException {
        //start connection
        conn = BaseEntity.open();
        
        //prepare query
        statement = conn.prepareStatement("INSERT INTO branch_list(branch_name,branch_address,branch_phone,create_date,update_date) VALUES(?,?,?,?);");
        statement.setString(1, branch.getBranch_name());
        statement.setString(2, branch.getBranch_address());
        statement.setString(3, branch.getBranch_phone());
        statement.setDate(4, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        statement.setDate(5, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        
        
        //execute query
        statement.executeUpdate();

        //close connection
        BaseEntity:close();
    }
    
    public void deleteBranch(int id) throws SQLException {
        //start connection
        conn = BaseEntity.open();
        
        //prepare query
        statement = conn.prepareStatement("delete from branch_list where branch_id=?;");
        statement.setInt(1, id);
        
        //execute query
        statement.executeUpdate();
        
        //close connection
        BaseEntity.close();
    }
    
    public void updateBranch(Branch branch) throws SQLException{
        //start connection
        conn = BaseEntity.open();
        
        //prepare query
        statement = conn.prepareStatement("UPDATE branch_list set branch_name = ?, branch_address = ?, branch_phone= ?, update_date = ? where branch_id=?;");
        statement.setString(1, branch.getBranch_name());
        statement.setString(2, branch.getBranch_address());
        statement.setString(3, branch.getBranch_phone());

        //transform from String to Date:
        statement.setDate(4, new java.sql.Date(Calendar.getInstance().getTime().getTime()));

        statement.setInt(5, branch.getBranch_id());
        
        //execute query:
        statement.executeUpdate();
        
        //close connection
        BaseEntity:close();
    }
    
    public ArrayList<Branch> searchBranches(String keyword) throws SQLException {
        //start connection
        conn = BaseEntity.open();
        ArrayList<Branch> BranchList = new ArrayList<>();
        
        //prepare query
        statement = conn.prepareStatement("select * from branch_list where branch_name LIKE ? or branch_address LIKE ?;");
        statement.setString(1, keyword);
        statement.setString(2, keyword);

        
        //execute query
        ResultSet data = statement.executeQuery();

        if (data != null) {
            while(data.next()){
                Branch branch = new Branch();
                branch.setBranch_id(data.getInt("branch_id"));
                branch.setBranch_name(data.getString("branch_name"));
                branch.setBranch_address(data.getString("branch_address"));
                branch.setBranch_phone(data.getString("branch_phone"));
                branch.setUpdated_at(LocalDate.parse(data.getString("update_date")));
                branch.setCreated_at(LocalDate.parse(data.getString("create_date")));
                BranchList.add(branch);
            }
        }
        
        //close connection
        BaseEntity:close();
        
        //return result
        return BranchList;
    }
}
