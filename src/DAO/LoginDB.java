package DAO;

import Entity.User;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDB {
    private static String PASS = "trottola12";
    private static String USER = "root";
    private static String DB_URL = "jdbc:mysql://localhost/Users";

    public static User findByNameAndPassword(String u_name, String p) {
        // STEP 1: dichiarazioni
        java.sql.Statement stmt = null;
        Connection conn = null;
        User u = null;
        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName("com.mysql.jdbc.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement();
            String QUERY = "SELECT * FROM Users.users WHERE Username =" + "'" + u_name + "'" +
                    " AND Password=" + "'" + p + "'";
            ResultSet rs = stmt.executeQuery(QUERY);

            if(rs.next()){

                String nome = rs.getString("Name");
                String cognome = rs.getString(2);
                String usernameLoaded = rs.getString(3);
                String password = rs.getString(4);
                String type = rs.getString(5);
                u = new User(nome, cognome, usernameLoaded, password, type);
            }

            // STEP 6: Clean-up dell'ambiente
            rs.close();
            //stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
            se.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return u;
    }

    /*public static Utente findByNameAndPasswordMockup(String username, String password) {
        if ("myusername".equals(username) && "mypassword".equals(password))
            return new Utente("myusername", "", "Tizio","Caio");
        else return null;
    }*/
}
