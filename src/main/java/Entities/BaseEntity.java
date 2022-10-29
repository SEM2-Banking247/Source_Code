package Entities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tuan
 */
public class BaseEntity {
    protected static final String DATABASE = "banking247";
    protected static final String USERNAME = "root";
    protected static final String PASSWORD = "";

    static Connection conn = null;
    static PreparedStatement statement = null;

    protected static Connection open() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(BaseEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    protected static void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(BaseEntity.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(BaseEntity.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        conn = null;
        statement = null;
    }
}
