package DAO;

import Entity.Room;
import Entity.User;
import Utils.Query;
import Utils.UserSingleton;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static DAO.DB_Connection.conn_Aule;

public class ActivePrenotation {

    public static ArrayList<Room> show(){

        /*DB_Connection connection = new DB_Connection();
        Connection connection2 = connection.connect_Aule();*/

        ArrayList<Room> room = new ArrayList<>();
        User user = UserSingleton.getInstance().getUser();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();

        String date = dtf.format(localDate);

        try {

            String query = String.format(Query.activePrenotation, user.getUsername(), date);

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
