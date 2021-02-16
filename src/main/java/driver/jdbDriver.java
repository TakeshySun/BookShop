package driver;

import java.sql.*;

public class jdbDriver {

    private Connection con;

    public void setUpJDBC() {
        //Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"
        String dbUrl = "jdbc:mysql://localhost:3306/new_schema";
        //Database Username
        String username = "root";
        //Database Password
        String password = "root";

        //Load mysql jdbc driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException | IllegalAccessError throwable) {
            System.out.println("--------->MySQL JDBC Connection Error--------->");
            throwable.printStackTrace();
        }

        //Create Connection to DB
        try {
            con = DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException throwable) {
            System.out.println("--------->MySQL SQL Exception cannot connect to DB--------->");
            throwable.printStackTrace();
        }
    }

    public Connection getConnection() {
        return con;
    }

    public void closeConnection() throws SQLException {
        // closing DB Connection
        if (con != null) {
            con.close();
        }
    }

}
