/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Models.Report;
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
public class ReportEntity extends BaseEntity{
    private final UserEntity userEntity = new UserEntity();
    private final Report_TypeEntity report_TypeEntity = new Report_TypeEntity();
    
    static Connection conn = null;
    static PreparedStatement statement = null;
    
    public ArrayList<Report> selectAllReports() throws SQLException{
        ArrayList<Report> ReportList = new ArrayList<>();
        
        //start connection
        conn = BaseEntity.open();
        
        //prepare query:
        statement = conn.prepareStatement("select * from reports;");
        
        //execute query
        ResultSet data = statement.executeQuery();
        
        while(data.next()){
            Report report = new Report();
            report.setReport_id(data.getInt("report_id"));
            report.setReport_type_id(data.getInt("report_type_id"));
            report.setReport_type(report_TypeEntity.selectReportTypeById(data.getInt("report_type_id")).getReport_type_name());
            report.setUser_id(data.getInt("user_id"));
            report.setAccount_number(userEntity.selectUserById(data.getInt("user_id")).getAccount_number());
            report.setReport_title(data.getString("report_title"));
            report.setReport_content(data.getString("report_content"));
            report.setReport_status(data.getString("report_status"));
            report.setCreate_Date(LocalDate.parse(data.getString("create_date")));
            report.setUpdate_date(LocalDate.parse(data.getString("update_date")));
            ReportList.add(report);
        }
        
        //close connection
        BaseEntity:close();
        
        //return result
        return ReportList;
    }
    
    public Report selectReportById(int id) throws SQLException{
        //start connection
        conn = BaseEntity.open();
        
        Report report = new Report();
        
        //prepare query
        statement = conn.prepareStatement("select * from reports where report_id=?;");
        statement.setInt(1, id);
        
        //execute query
        ResultSet data = statement.executeQuery();
        
        while(data.next()){
            report.setReport_id(data.getInt("report_id"));
            report.setReport_type_id(data.getInt("report_type_id"));
            report.setReport_type(report_TypeEntity.selectReportTypeById(data.getInt("report_type_id")).getReport_type_name());
            report.setUser_id(data.getInt("user_id"));
            report.setAccount_number(userEntity.selectUserById(data.getInt("user_id")).getAccount_number());
            report.setReport_title(data.getString("report_title"));
            report.setReport_content(data.getString("report_content"));
            report.setReport_status(data.getString("report_status"));
            report.setCreate_Date(LocalDate.parse(data.getString("create_date")));
            report.setUpdate_date(LocalDate.parse(data.getString("update_date")));
            break;
        }
        
        //close connection
        BaseEntity:close();
        
        return report;
    }
    
    public boolean addReport(Report report) throws SQLException {
        //start connection
        conn = BaseEntity.open();
                
        //prepare query
        statement = conn.prepareStatement("INSERT INTO reports(report_type_id, user_id,report_title,report_content,report_status,create_date,update_date) VALUES(?,?,?,?,?,?,?);");
        statement.setInt(1, report.getReport_type_id());
        statement.setInt(2, report.getUser_id());
        statement.setString(3, report.getReport_title());
        statement.setString(4, report.getReport_content());
        statement.setString(5, report.getReport_status());
        statement.setDate(6, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        statement.setDate(7, new java.sql.Date(Calendar.getInstance().getTime().getTime()));

        
        //execute query
        if(statement.executeUpdate() == 0) {
            BaseEntity:close();
            return false;
        }
        
         //close connection
        BaseEntity:close();
        
        return true;
    }
    
    public void deleteReport(int id) throws SQLException {
        //start connection
        conn = BaseEntity.open();
        
        //prepare query
        statement = conn.prepareStatement("delete from reports where report_id=?;");
        statement.setInt(1, id);
        
        //execute query
        statement.executeUpdate();
        
        //close connection
        BaseEntity:close();
    }
    
    public void editReportStatus(Report report) throws SQLException{
        //start connection
        conn = BaseEntity.open();
        
        //prepare query
        statement = conn.prepareStatement("UPDATE reports set report_status = ?, update_date = ? where report_id=(?);");
        statement.setString(1, report.getReport_status());
        statement.setDate(2, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        statement.setInt(3, report.getReport_id());
        
        //execute query
        statement.executeUpdate();
        
         //close connection
        BaseEntity:close();
    }
    
    public ArrayList<Report> searchReports(String keyword) throws SQLException {
        //start connection
        conn = BaseEntity.open();
        ArrayList<Report> ReportList = new ArrayList<>();

        int number = 0;
        
         //prepare query
        statement = conn.prepareStatement("select * from reports where report_title Like ? or report_content Like ? or report_status Like ?;");
        statement.setString(1, keyword);
        statement.setString(2, keyword);
        statement.setString(3, keyword);
        
        //execute query
        ResultSet data = statement.executeQuery();
        
        while(data.next()){
            Report report = new Report();
            report.setReport_id(data.getInt("report_id"));
            report.setReport_type_id(data.getInt("report_type_id"));
            report.setReport_type(report_TypeEntity.selectReportTypeById(data.getInt("report_type_id")).getReport_type_name());
            report.setUser_id(data.getInt("user_id"));
            report.setAccount_number(userEntity.selectUserById(data.getInt("user_id")).getAccount_number());
            report.setReport_title(data.getString("report_title"));
            report.setReport_content(data.getString("report_content"));
            report.setReport_status(data.getString("report_status"));
            report.setCreate_Date(LocalDate.parse(data.getString("create_date")));
            report.setUpdate_date(LocalDate.parse(data.getString("update_date")));
            ReportList.add(report);
            number ++;
        }
        
        //close connection
        BaseEntity:close();
        
        return ReportList;
    }
}
