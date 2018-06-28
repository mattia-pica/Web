package DAO;

import Entity.Room;
import Utils.Query;
import Utils.UserSingleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PrenotationInAccademicYear {

    public ArrayList<Room> show(String dataInizio, String dataFine){

        ArrayList<Room> room = new ArrayList<>();

        Statement stmt = null;
        Connection conn = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(Query.DB_URL, Query.USER, Query.PASS);
            stmt = conn.createStatement();

            String query = String.format(Query.prenotationInAccYear, UserSingleton.getInstance().getUser().getUsername(), dataInizio, dataFine);

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){

                Room rooms = new Room();

                rooms.setNome(rs.getString("nome"));
                rooms.setDatapr(rs.getString("datapr"));
                rooms.setInizio(rs.getString("inizio"));
                rooms.setFine(rs.getString("fine"));
                rooms.setTipopr(rs.getString("tipopr"));
                rooms.setSessione(rs.getString("sessione"));

                room.add(rooms);

            }

            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return room;

    }

}
