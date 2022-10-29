/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aptech.mavenproject2;

import Entities.BranchEntity;
import Entities.CardEntity;
import Entities.ReportEntity;
import Entities.Report_TypeEntity;
import Entities.RoleEntity;
import Entities.UserEntity;
import Models.Report;
import Models.Report_Type;
import Models.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author tuan
 */
public class Customer_ReportController implements Initializable{
    private final BranchEntity branchEntity = new BranchEntity();
    private final RoleEntity roleEntity = new RoleEntity();
    private final UserEntity userEntity = new UserEntity();
    private final CardEntity cardEntity = new CardEntity();
    private final Report_TypeEntity report_TypeEntity = new Report_TypeEntity();
    private final ReportEntity reportEntity = new ReportEntity();
    User user = User.getInstance();
    
    @FXML
    private ChoiceBox<String> reportTypesChoiceBox;

    @FXML
    private Label txtFullname_header;
    
    @FXML
    private TextField txtTitle;
    
    @FXML
    private TextArea txtContent;
    
    @FXML
    private Label txtFullname;
    
    @FXML 
    private Label txtNotification;
    
    @FXML
    private Label ValidateTitle;
    
    @FXML
    private Label ValidateContent;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //show user's infor
        txtFullname_header.setText("Hi, "+user.getFull_name());
        txtFullname.setText(user.getFull_name());
        
        ArrayList<Report_Type> types = new ArrayList<>();
        try {
            types = report_TypeEntity.selectAllReportTypes();
        } catch (SQLException ex) {
            Logger.getLogger(Customer_WithdrawController.class.getName()).log(Level.SEVERE, null, ex);
        }
        types.forEach(type -> reportTypesChoiceBox.getItems().add(type.getReport_type_name()));
    }    
    
    @FXML
    private int sendReport() throws IOException, SQLException{
        int validate = 1;
        
        //get data
        String report_type_name = reportTypesChoiceBox.getValue();
        String report_title = txtTitle.getText();
        String report_content = txtContent.getText();
        
        if (report_title.equals("")) {
            ValidateTitle.setText("Empty Title!");
            validate =0;
        }
        
        if (report_content.equals("")) {
            ValidateTitle.setText("Empty Title!");
            validate =0;
        }
        
        if (validate == 0) {
            return 0;
        }
        
        //add report
        Report_Type selectedReport_Type = report_TypeEntity.selectReportTypeByName(report_type_name);
        
        Report report = new Report();
        report.setUser_id(user.getUser_id());
        report.setReport_type_id(selectedReport_Type.getReport_type_id());
        report.setReport_title(report_title);
        report.setReport_content(report_content);
        report.setReport_status("Active");
        
        if(reportEntity.addReport(report)) {
            txtNotification.setText("Report Submited!");
        } else {
          txtNotification.setText("Error!");
        }
        return 1;
    }
    
    @FXML
    private void logout() throws IOException {
        App.setRoot("Login");
    }
    
    @FXML
    private void home() throws IOException {
        App.setRoot("Customer_Home");
    }
    
    @FXML
    private void transfer() throws IOException {
        App.setRoot("Customer_Transfer");
    }
    
    @FXML
    private void showHistory() throws IOException {
        App.setRoot("Customer_History");
    }
    
    @FXML
    private void showInfor() throws IOException {
        App.setRoot("Customer_Infor");
    }
    
    @FXML
    private void showBranches() throws IOException {
        App.setRoot("Customer_Branches");
    }
    
    @FXML
    private void report() throws IOException {
        App.setRoot("Customer_Report");
    }
    
    @FXML
    private void withdraw() throws IOException {
        App.setRoot("Customer_Withdraw");
    }
}
