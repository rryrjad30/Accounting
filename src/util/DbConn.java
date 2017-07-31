package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbConn {

    public static final String JDBC_CLASS = "com.mysql.jdbc.Driver";
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/akuntansi";
    public static final String JDBC_USERNAME = "root";
    public static final String JDBC_PASSWORD = "root";
    
     public static Statement getStatement() {
        Statement st;
        try {
            Connection connection = getConnection();
            st = connection.createStatement();
            return st;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
     }
        
        public static Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
}
}

