package DAO;

import Entity.Room;
import Utils.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static DAO.DB_Connection.conn_Aule;

public class All_Prenotation {

    public static ArrayList<Room> showAll(){

        /*DB_Connection connection = new DB_Connection();
        Connection connection2 = connection.connect_Aule();*/

        ArrayList<Room> room = new ArrayList<>();

        try {

            String query = String.format(Query.allPrenotation);

            Statement statement = conn_Aule.createStatement();

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
            statement.close();

        }catch (SQLException e){
                System.err.println(e);
            }

        return room;
    }
}
