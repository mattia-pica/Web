package DAO;

import Entity.Room;
import Entity.User;
import Utils.Query;
import Utils.UserSingleton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShowCompleteDB {

    public static ArrayList<Room> show_completeDB(){

        User user = UserSingleton.getInstance().getUser();
        DB_Connection_Aule connection = new DB_Connection_Aule();
        Connection connection2 = connection.connect_Aule();

        ArrayList<Room> rooms = new ArrayList<>();

        //String query ="SELECT Nome,TipoPr,DataPr,Inizio,Fine FROM dati WHERE Aule.dati.FromP='"+professore.getUsername()+"'";

        try {
            /*ResultSet rs = connection2.createStatement().executeQuery("SELECT * FROM dbEsame.Aule WHERE fromp='"+
            user.getUsername() + "'");*/
            ResultSet rs = connection2.createStatement().executeQuery(String.format(Query.completeDB, user.getUsername()));
            while (rs.next()){

                Room room = new Room();

                room.setNome(rs.getString("nome"));
                room.setTipopr(rs.getString("tipopr"));
                room.setDatapr(rs.getString("datapr"));
                room.setInizio(rs.getString("inizio"));
                room.setFine(rs.getString("fine"));
                room.setID(rs.getInt("ID"));
                //room.setFromp(user.getUsername());

                rooms.add(room);

            }
            /*data = FXCollections.observableArrayList();
            Statement statement = connection2.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                data.add(new Classroom_ProfComplete(rs.getString("Nome"),rs.getString("TipoPr"),
                        rs.getString("DataPr"), rs.getString("Inizio"),
                        rs.getString("Fine")));*/

            connection2.close();
            }catch (SQLException e) {
            System.err.println("Error" + e);
        }

        return rooms;
    }

}
