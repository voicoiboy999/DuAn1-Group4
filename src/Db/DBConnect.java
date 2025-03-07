package Db;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ADMIN
 */
public class DBConnect {
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "123456";
    private static final String SERVER = "localhost";
    private static final String PORT = "1433";
    private static final String DATABASE_NAME = "Pro1041_Nhom4";
    private static final boolean USING_SSL = true;
    private static String CONNECT_STRING;
    private static Connection conn;
   
    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           
            StringBuilder connectStringBuilder = new StringBuilder();
            connectStringBuilder.append("jdbc:sqlserver://")
                    .append(SERVER).append(":").append(PORT).append(";")
                    .append("databaseName=").append(DATABASE_NAME).append(";")
                    .append("user=").append(USERNAME).append(";")
                    .append("password=").append(PASSWORD).append(";")
                    ;
            if (USING_SSL) {
                connectStringBuilder.append("encrypt=true;trustServerCertificate=true;");
            }
            CONNECT_STRING = connectStringBuilder.toString();
            System.out.println("Connect String có dạng: " + CONNECT_STRING);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public static Connection getConnection()  {
         try {
             if (conn == null || conn.isClosed()) {
                 conn=DriverManager.getConnection(CONNECT_STRING);        
             }
             return conn; 
         } catch (SQLException ex) {
             ex.printStackTrace();
             Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
             return null;
         }
    }
   
    public static void main(String[] args) throws Exception {
        Connection conn = getConnection();
        DatabaseMetaData dbmt = conn.getMetaData();
        System.out.println(dbmt.getDriverName());
        System.out.println(dbmt.getDatabaseProductName());
        System.out.println(dbmt.getDatabaseProductVersion());
    }
}
