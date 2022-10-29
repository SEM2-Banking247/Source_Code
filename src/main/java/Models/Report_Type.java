/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author tuan
 */
public class Report_Type {
    private int Report_type_id;
    private String Report_type_name;
    
    private static final Report_Type instance = new Report_Type();
    
    public static Report_Type getInstance() {
        return instance;
    }

    public Report_Type(int Report_type_id ,String Report_type_name) {
        this.Report_type_id = Report_type_id;
        this.Report_type_name = Report_type_name;
    }

    public Report_Type() {
    }

    public int getReport_type_id() {
        return Report_type_id;
    }

    public void setReport_type_id(int Report_type_id) {
        this.Report_type_id = Report_type_id;
    }

    public String getReport_type_name() {
        return Report_type_name;
    }

    public void setReport_type_name(String Report_type_name) {
        this.Report_type_name = Report_type_name;
    }


}
