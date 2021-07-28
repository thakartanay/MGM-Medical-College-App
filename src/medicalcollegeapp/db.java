/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalcollegeapp;

/**
 *
 * @author Tanay Thakar
 */

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Tanay Thakar
 */

public class db {
    static Connection conn = null;
    
    public static Connection connect()
    {
        try
        {
            String drivername = "org.sqlite.JDBC";
            Class.forName(drivername);
            String currentDir=System.getProperty("user.dir");
            conn = DriverManager.getConnection("JDBC:sqlite:"+currentDir+"\\Database\\MedicalClgDB.sqlite");
            conn.setAutoCommit(true);
            return conn;
           
        }
        catch(HeadlessException | ClassNotFoundException | SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Database Error");
            e.printStackTrace();
            return null;
        }
    }
}