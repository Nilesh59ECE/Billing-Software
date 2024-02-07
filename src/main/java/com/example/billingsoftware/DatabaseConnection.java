package com.example.billingsoftware;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    static Connection con;
    public static void startDatabaseConnection() throws SQLException {
     con = DriverManager.getConnection("jdbc:mysql://localhost:3306/janta_store", "root", "Nilesh123@");
    }
    static void closeConnection() throws  SQLException{
        con.close();
    }
    public static Connection getCon(){
        return con;
    }
}
