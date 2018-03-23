package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB_Connection_Users {

    public static Connection conn_Users;

    public Connection connect_Users(){
        final String url = "jdbc:mysql://localhost:3306/Users";
        final String user = "root";
        final String password = "trottola12";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            DB_Connection_Users.conn_Users = DriverManager.getConnection(url, user, password);
            System.out.println("CONNESSO");
        } catch (Exception e) {
            Logger.getLogger(DB_Connection_Users.class.getName()).log(Level.SEVERE, null, e);
        }

        return conn_Users;
    }

}
