package DAO;

import Bean.Disponible_RoomBean;
import Utils.DATABASE_Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

public class Prof_DisponibleRooms {



    public static Disponible_RoomBean show_prof(LocalTime timeInizio, LocalTime timeFine, String dateSearch){

        /*DB_Connection connection = new DB_Connection();
        Connection connection1 = connection.connect_Aule();*/
        java.sql.Statement stmt = null;
        Connection conn = null;

        ArrayList<Disponible_RoomBean> Classrooms = new ArrayList<Disponible_RoomBean>();
        Disponible_RoomBean disponible_roomBean = null;
        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName("com.mysql.jdbc.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DATABASE_Utils.DB_URL, DATABASE_Utils.USER, DATABASE_Utils.PASS);

            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement();
            String query ="SELECT DISTINCT nome FROM dbEsame.Aule WHERE nome NOT IN (SELECT nome FROM dbEsame.Aule WHERE datapr='"
                    +dateSearch+"' AND ((inizio<'"+timeInizio+"' AND fine>'"+timeInizio+"') "+"OR (fine>'"+timeFine
                    +"' AND inizio<'"+timeFine+"') "+"OR (inizio>'"+timeInizio+"' AND fine<'"+timeFine+"')))";
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<String> rooms = new ArrayList<>();
            while (rs.next()){
               rooms.add(rs.getString(1));
            }

            disponible_roomBean = new Disponible_RoomBean(rooms);

            // STEP 6: Clean-up dell'ambiente
            rs.close();
            //stmt.close();
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

        return disponible_roomBean;
    }

    }

