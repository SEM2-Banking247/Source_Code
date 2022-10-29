/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.aptech.mavenproject2;

import Entities.ReportEntity;
import Entities.Report_TypeEntity;
import Entities.UserEntity;
import Models.Report;
import Models.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class Employee_FeedbackController implements Initializable {
    private final UserEntity userEntity = new UserEntity();
    private final ReportEntity reportEntity = new ReportEntity();
    private final Report_TypeEntity report_TypeEntity = new Report_TypeEntity();
    User user = User.getInstance();
    
    @FXML
    private TableView<Report> ReportTableView;
    @FXML
    private TableColumn<Report, String> ColAccountNumber;
    @FXML
    private TableColumn<Report, String> ColReportType;
    @FXML
    private TableColumn<Report, String> ColReportTitle;
    @FXML
    private TableColumn<Report, String> ColReportContent;
    @FXML
    private TableColumn<Report, String> ColReportStatus;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnRefresh;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnFind;
    @FXML
    private Label txtAccountnumber;
    @FXML
    private Label txtUserName;
    @FXML
    private Label txtReportType;
    @FXML
    private ChoiceBox<String> txtStatus;
    @FXML
    private Label txtReportTitle;
    @FXML
    private Label txtReportContent;
    @FXML
    private Label txtFullname_header;
    @FXML
    private Label txtNotification;
    @FXML
    private Label txtCount;
    @FXML
    private TextField txtKeyword;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //show user name
        txtFullname_header.setText("Hi, "+user.getFull_name());
        
        //insert data into status choicebox
        txtStatus.setItems(FXCollections.observableArrayList("Active", "Resolved", "Pending"));
        
        // insert data into branch choicebox 
//        ArrayList<Report> all_reports = null;
//        try {
//            all_reports = reportEntity.selectAllReports();
//        } catch (SQLException ex) {
//            Logger.getLogger(Employee_FeedbackController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        all_reports.forEach(report -> branches.getItems().add(branch.getBranch_name()));
        
        //show all report details
        try {
            showReports();
        } catch (SQLException ex) {
            Logger.getLogger(Employee_FeedbackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showReportDetail() throws SQLException {
        Report select_report = ReportTableView.getSelectionModel().getSelectedItem();
        User selected_user = userEntity.selectUserById(select_report.getUser_id());
        
        //show card details
        txtAccountnumber.setText(selected_user.getAccount_number());
        txtUserName.setText(selected_user.getFull_name());
        txtReportType.setText(select_report.getReport_type());
        txtReportTitle.setText(select_report.getReport_title());
        txtReportContent.setText(select_report.getReport_content());
        txtStatus.setValue(select_report.getReport_status());
        
//        Branch selected_branch = branchEntity.selectBranchById(select_card.getBranch_id());
//        branches.setValue(selected_branch.getBranch_name());
//        
//        findUser();
    }

    @FXML
    private void Save() throws SQLException, InterruptedException {
        Report select_report = ReportTableView.getSelectionModel().getSelectedItem();
        //get branch and status data:        
        String status = txtStatus.getValue();
        
        
        Report report = new Report();
        report.setReport_id(select_report.getReport_id());
        report.setReport_status(status);
        
        //if the user select a card from the table, its update, otherwise its add.
        if (!txtAccountnumber.getText().equals("")) {
            //update report status
            reportEntity.editReportStatus(report);
            txtNotification.setText("Report Updated!");
            showReports();
        }
        else {
            txtNotification.setText("Select a Report first!");
            showReports();
        }
    }

    @FXML
    private void Reset() {
        clear();
    }
    
    @FXML
    private void refresh() throws SQLException{
        showReports();
        clear();
    }

    @FXML
    private void Find() throws SQLException {
        //get keyword
        String keyword = txtKeyword.getText();
        ArrayList<Report> data = new ArrayList<>();
        
        //get card list data
        if (keyword.equals("")) {
            data = reportEntity.selectAllReports();
        }
        else {
            keyword = "%"+keyword+"%";  
            data = reportEntity.searchReports(keyword);
        }
        
        //convert data type of the list from Card to Custom_Card 
//        ArrayList<Custom_Card> cardList = new ArrayList<>();
//        for (Card card : data) {            
//            Custom_Card custom_card = new Custom_Card();
//            custom_card.setCard_infor_id(card.getCard_infor_id());
//            custom_card.setUser_id(card.getUser_id());
//            custom_card.setBranch_id(card.getBranch_id());
//            
//            User new_user = userEntity.selectUserById(card.getUser_id());
//            custom_card.setAccount_number(new_user.getAccount_number());
//            
//            custom_card.setCard_number(card.getCard_number());
//            custom_card.setCard_balance(String.format("%.0f",card.getCard_balance()));
//            custom_card.setNotice(card.getNotice());
//            custom_card.setStatus(card.getStatus());
//            custom_card.setPin_number(new_user.getPin());
//            custom_card.setCreated_at(card.getCreated_at().toString());
//            custom_card.setUpdated_at(card.getUpdated_at().toString());
//            
//            cardList.add(custom_card);
//        }
        
        //show list data
        ColAccountNumber.setCellValueFactory(new PropertyValueFactory<>("account_number"));
        ColReportType.setCellValueFactory(new PropertyValueFactory<>("report_type"));
        ColReportTitle.setCellValueFactory(new PropertyValueFactory<>("report_title"));
        ColReportContent.setCellValueFactory(new PropertyValueFactory<>("report_content"));
        ColReportStatus.setCellValueFactory(new PropertyValueFactory<>("report_status"));
        
        ReportTableView.setItems(FXCollections.observableArrayList(data));
        
        txtCount.setText("Result: "+data.size());
    }

    public void showReports() throws SQLException {
        ArrayList<Report> data = reportEntity.selectAllReports();
//        ArrayList<Custom_Card> cardList = new ArrayList<>();
//        for (Report report : data) {
//            Custom_Card custom_card = new Custom_Card();
//            custom_card.setCard_infor_id(card.getCard_infor_id());
//            custom_card.setUser_id(card.getUser_id());
//            custom_card.setBranch_id(card.getBranch_id());
//            
//            User new_user = userEntity.selectUserById(card.getUser_id());
//            custom_card.setAccount_number(new_user.getAccount_number());
//            
//            custom_card.setCard_number(card.getCard_number());
//            custom_card.setCard_balance(String.format("%.0f",card.getCard_balance()));
//            custom_card.setNotice(card.getNotice());
//            custom_card.setStatus(card.getStatus());
//            custom_card.setPin_number(new_user.getPin());
//            custom_card.setCreated_at(card.getCreated_at().toString());
//            custom_card.setUpdated_at(card.getUpdated_at().toString());
//            
//            cardList.add(custom_card);
//        }

        ColAccountNumber.setCellValueFactory(new PropertyValueFactory<>("account_number"));
        ColReportType.setCellValueFactory(new PropertyValueFactory<>("report_type"));
        ColReportTitle.setCellValueFactory(new PropertyValueFactory<>("report_title"));
        ColReportContent.setCellValueFactory(new PropertyValueFactory<>("report_content"));
        ColReportStatus.setCellValueFactory(new PropertyValueFactory<>("report_status"));

        ReportTableView.setItems(FXCollections.observableArrayList(data));

        txtCount.setText("Result: "+data.size());
    }

    public void clear() {
        txtAccountnumber.setText("");
        txtKeyword.setText("");
        txtNotification.setText("");
        txtReportContent.setText("");
        txtReportTitle.setText("");
        txtReportType.setText("");
        txtStatus.setValue("");
        txtNotification.setText("");
        txtUserName.setText("");
    }
    
    @FXML
    private void logout() throws IOException {
        App.setRoot("Login");
    }
    
    @FXML
    private void showInfor() throws IOException{
        App.setRoot("Employee_Infor");
    }
    
    @FXML
    public void cardManager() throws IOException{
        App.setRoot("Employee_CardList");
    }
    
    @FXML
    public void userManager() throws IOException {
        App.setRoot("Employee_UserList");
    }
    
    @FXML
    public void TransactionManager() throws IOException{
        App.setRoot("Employee_TransactionHistory");
    }
    
    @FXML
    public void FeedbackManager() throws IOException{
        App.setRoot("Employee_FeedbackList");
    }
    
    @FXML
    public void home() throws IOException{
        App.setRoot("Employee_Home");
    }
}
