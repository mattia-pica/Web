package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;



public class DB_Connection {

    static Connection conn_Aule;
    public Connection connect_Aule() { //copia quel cazzo di

        final String url = "jdbc:mysql://localhost:3306/dbEsame";
        final String user = "root";
        final String password = "password";

            try {
                Class.forName("com.mysql.jdbc.Driver");
                DB_Connection.conn_Aule = DriverManager.getConnection(url, user, password);
                System.out.println("CONNESSO");
            } catch (Exception e) {
                Logger.getLogger(DB_Connection.class.getName()).log(Level.SEVERE, null, e);
            }
            //NEL VIDEO retun null, ma così da problemi il controller che riceve null e non può andare avanti!
            return conn_Aule;
    }


}
