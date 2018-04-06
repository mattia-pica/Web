package DAO;

import Entity.Room;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class All_Prenotation {

    public static ArrayList<Room> showAll(){

        DB_Connection_Aule connection = new DB_Connection_Aule();
        Connection connection2 = connection.connect_Aule();

        ArrayList<Room> room = new ArrayList<>();

        try {

            String query = "SELECT * FROM dbEsame.Aule WHERE tipopr IS NOT NULL";

            Statement statement = connection2.createStatement();

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {

                Room rooms = new Room();

                rooms.setNome(rs.getString("nome"));
                rooms.setTipopr(rs.getString("tipopr"));
                rooms.setDatapr(rs.getString("datapr"));
                rooms.setInizio(rs.getString("inizio"));
                rooms.setFine(rs.getString("fine"));
                rooms.setFromp(rs.getString("fromp"));
                rooms.setID(rs.getInt("ID"));
                room.add(rooms);
            }
            connection2.close();

        }catch (SQLException e){
                System.err.println(e);
            }

        return room;
    }
}
