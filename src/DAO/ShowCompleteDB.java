package DAO;

import Entity.Room;
import Entity.User;
import Utils.DATABASE_Utils;
import Utils.UserSingleton;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static DAO.DB_Connection.conn_Aule;


public class ShowCompleteDB {

    public static ArrayList<Room> show_completeDB(){

        User user = UserSingleton.getInstance().getUser();


        ArrayList<Room> rooms = new ArrayList<>();


        try {

            Statement statement = conn_Aule.createStatement();
            ResultSet rs = statement.executeQuery(String.format(DATABASE_Utils.completeDB, user.getUsername()));
            while (rs.next()){

                Room room = new Room();

                room.setNome(rs.getString("nome"));
                room.setTipopr(rs.getString("tipopr"));
                room.setDatapr(rs.getString("datapr"));
                room.setInizio(rs.getString("inizio"));
                room.setFine(rs.getString("fine"));
                room.setID(rs.getInt("ID"));

                rooms.add(room);
            }

            statement.close();
            }catch (SQLException e) {
            System.err.println("Error" + e);
        }

        return rooms;
    }

}
