/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Models.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;

/**
 *
 * @author tuan
 */
public class RoleEntity extends BaseEntity{
    static Connection conn = null;
    static PreparedStatement statement = null;
    
    public ArrayList<Role> selectAllRoles() throws SQLException{
        ArrayList<Role> RoleList = new ArrayList<>();
        
        //start connection
        conn = BaseEntity.open();
        
        //prepare query:
        statement = conn.prepareStatement("select * from role;");
        
        //execute query
        ResultSet roles = statement.executeQuery();
//        ObservableList data = FXCollections.observableArrayList();
        
        while(roles.next()){
            Role role = new Role(roles.getInt("Role_id"),roles.getString("Role_Name"));
            RoleList.add(role);
        }
        
        //close connection
        BaseEntity:close();
        
        //return result
        return RoleList;
    }
    
    public Role selectRoleById(int id) throws SQLException{
        //start connection
        conn = BaseEntity.open();
        
        Role role = new Role();
        
        //prepare query
        statement = conn.prepareStatement("select * from role where Role_id=?;");
        statement.setInt(1, id);
        
        //execute query
        ResultSet data = statement.executeQuery();
        
        while(data.next()){
            role = new Role(data.getInt("Role_id"),data.getString("Role_Name"));
            break;
        }
        
        //close connection
        BaseEntity:close();
        
        return role;
    }
    
    public Role selectRoleByName(String name) throws SQLException{
        //start connection
        conn = BaseEntity.open();
        
        Role role = new Role();
        
        //prepare query
        statement = conn.prepareStatement("select * from role where Role_Name=?;");
        statement.setString(1, name);
        
        //execute query
        ResultSet data = statement.executeQuery();
        
        while(data.next()){
            role = new Role(data.getInt("Role_id"),data.getString("Role_Name"));
            break;
        }
        
        //close connection
        BaseEntity:close();
        
        return role;
    }
    
    public void addRole(String role_name) throws SQLException {
        //start connection
        conn = BaseEntity.open();
                
        //prepare query
        statement = conn.prepareStatement("INSERT INTO role(Role_Name) VALUES(?);");
        statement.setString(1, role_name);
        
        //execute query
        statement.executeUpdate();
        
         //close connection
        BaseEntity:close();
    }
    
    public void deleteRole(int id) throws SQLException {
        //start connection
        conn = BaseEntity.open();
        
        //prepare query
        statement = conn.prepareStatement("delete from role where id=?;");
        statement.setInt(1, id);
        
        //execute query
        statement.executeUpdate();
        
        //close connection
        BaseEntity:close();
    }
    
    public void editRole(Role role) throws SQLException{
        //start connection
        conn = BaseEntity.open();
        
        //prepare query
        statement = conn.prepareStatement("UPDATE role set Role_Name = ? where Role_id=?;");
        statement.setString(1, role.getName());
        statement.setInt(2, role.getRole_id());
        
        //execute query
        statement.executeUpdate();
        
         //close connection
        BaseEntity:close();
    }
    
    public ArrayList<Role> searchRolesByName(String role_name) throws SQLException {
        //start connection
        conn = BaseEntity.open();
        ArrayList<Role> RoleList = new ArrayList<>();

        
         //prepare query
        statement = conn.prepareStatement("select * from role where Role_Name Like ?;");
        statement.setString(1, role_name);
        
        //execute query
        ResultSet data = statement.executeQuery();
        
        while(data.next()){
            Role role = new Role();
            role.setRole_id(data.getInt("Role_id"));
            role.setName(data.getString("Role_Name"));
            RoleList.add(role);
        }
        
        //close connection
        BaseEntity:close();
        
        return RoleList;
    }
    
    
}
