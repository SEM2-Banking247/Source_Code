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
public class Report {
    private int report_id;
    private int report_type_id;
    private String report_type;
    private int user_id;
    private String account_number;
    private String report_title;
    private String report_content;
    private String report_status;
    private LocalDate update_date;
    private LocalDate create_Date;
    
    private static final Report instance = new Report();
    
    public static Report getInstance() {
        return instance;
    }

    public Report(int report_id, int report_type_id, String report_type, int user_id, String account_number, String report_title, String report_content, String report_status, LocalDate update_date, LocalDate create_Date) {
        this.report_id = report_id;
        this.report_type_id = report_type_id;
        this.report_type = report_type;
        this.user_id = user_id;
        this.account_number = account_number;
        this.report_title = report_title;
        this.report_content = report_content;
        this.report_status = report_status;
        this.update_date = update_date;
        this.create_Date = create_Date;
    }

    public Report() {
    }

    public String getReport_type() {
        return report_type;
    }

    public void setReport_type(String report_type) {
        this.report_type = report_type;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }
    
    

    public int getReport_type_id() {
        return report_type_id;
    }

    public void setReport_type_id(int report_type_id) {
        this.report_type_id = report_type_id;
    }

    public LocalDate getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(LocalDate update_date) {
        this.update_date = update_date;
    }

    public LocalDate getCreate_Date() {
        return create_Date;
    }

    public void setCreate_Date(LocalDate create_Date) {
        this.create_Date = create_Date;
    }
    
    

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getReport_title() {
        return report_title;
    }

    public void setReport_title(String report_title) {
        this.report_title = report_title;
    }

    public String getReport_content() {
        return report_content;
    }

    public void setReport_content(String report_content) {
        this.report_content = report_content;
    }

    public String getReport_status() {
        return report_status;
    }

    public void setReport_status(String report_status) {
        this.report_status = report_status;
    }
   
    
}
