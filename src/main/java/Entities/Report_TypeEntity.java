/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Models.Report_Type;
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
public class Report_TypeEntity extends BaseEntity{
    static Connection conn = null;
    static PreparedStatement statement = null;
    
    public ArrayList<Report_Type> selectAllReportTypes() throws SQLException{
        ArrayList<Report_Type> dataList = new ArrayList<>();
        
        //start connection
        conn = BaseEntity.open();
        
        //prepare query:
        statement = conn.prepareStatement("select * from report_types;");
        
        //execute query
        ResultSet data = statement.executeQuery();
//        ObservableList data = FXCollections.observableArrayList();
        
        while(data.next()){
            Report_Type report = new Report_Type(data.getInt("report_type_id"),data.getString("report_type_name"));
            dataList.add(report);
        }
        
        //close connection
        BaseEntity:close();
        
        //return result
        return dataList;
    }
    
    public Report_Type selectReportTypeById(int id) throws SQLException{
        //start connection
        conn = BaseEntity.open();
        
        Report_Type report = new Report_Type();
        
        //prepare query
        statement = conn.prepareStatement("select * from report_types where report_type_id=?;");
        statement.setInt(1, id);
        
        //execute query
        ResultSet data = statement.executeQuery();
        
        while(data.next()){
            report = new Report_Type(data.getInt("report_type_id"),data.getString("report_type_name"));
            break;
        }
        
        //close connection
        BaseEntity:close();
        
        return report;
    }
    
    public Report_Type selectReportTypeByName(String name) throws SQLException{
        //start connection
        conn = BaseEntity.open();
        
        Report_Type report = new Report_Type();
        
        //prepare query
        statement = conn.prepareStatement("select * from report_types where report_type_name=?;");
        statement.setString(1, name);
        
        //execute query
        ResultSet data = statement.executeQuery();
        
        while(data.next()){
            report = new Report_Type(data.getInt("report_type_id"),data.getString("report_type_name"));
            break;
        }
        
        //close connection
        BaseEntity:close();
        
        return report;
    }
    
    public void addReportType(String report_type_name) throws SQLException {
        //start connection
        conn = BaseEntity.open();
                
        //prepare query
        statement = conn.prepareStatement("INSERT INTO report_types(report_type_name) VALUES(?);");
        statement.setString(1, report_type_name);
        
        //execute query
        statement.executeUpdate();
        
         //close connection
        BaseEntity:close();
    }
    
    public void deleteReportType(int id) throws SQLException {
        //start connection
        conn = BaseEntity.open();
        
        //prepare query
        statement = conn.prepareStatement("delete from report_types where report_type_id=?;");
        statement.setInt(1, id);
        
        //execute query
        statement.executeUpdate();
        
        //close connection
        BaseEntity:close();
    }
    
    public void editReportType(Report_Type report_type) throws SQLException{
        //start connection
        conn = BaseEntity.open();
        
        //prepare query
        statement = conn.prepareStatement("UPDATE report_types set report_type_name = (?) where report_type_id=(?);");
        statement.setString(1, report_type.getReport_type_name());
        statement.setInt(2, report_type.getReport_type_id());
        
        //execute query
        statement.executeUpdate();
        
         //close connection
        BaseEntity:close();
    }
    
    public ArrayList<Report_Type> searchReportTypesByName(String report_type_name) throws SQLException {
        //start connection
        conn = BaseEntity.open();
        ArrayList<Report_Type> dataList = new ArrayList<>();

        
         //prepare query
        statement = conn.prepareStatement("select * from report_types where report_type_name Like ?;");
        statement.setString(1, report_type_name);
        
        //execute query
        ResultSet data = statement.executeQuery();
        
        while(data.next()){
            Report_Type report_type = new Report_Type();
            report_type.setReport_type_id(data.getInt("report_type_id"));
            report_type.setReport_type_name(data.getString("report_type_name"));
            dataList.add(report_type);
        }
        
        //close connection
        BaseEntity:close();
        
        return dataList;
    }
}
