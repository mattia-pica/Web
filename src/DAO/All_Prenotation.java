package DAO;

import Entity.Room;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class All_Prenotation {

    public static ArrayList<Room> showAll(){

        DB_Connection_Aule connection = new DB_Connection_Aule();
        Connection connection2 = connection.connect_Aule();

        ArrayList<Room> room = new ArrayList<>();

        try {

            ResultSet rs = connection2.createStatement().executeQuery("SELECT * FROM dbEsame.Aule");
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
