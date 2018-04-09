package DAO;

import Entity.Room;
import Entity.User;
import Utils.Query;
import Utils.UserSingleton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

public class ShowDatabase_Secr {

    public static ArrayList<Room> show_secr() {

        DB_Connection_Aule connection = new DB_Connection_Aule();
        Connection connection3 = connection.connect_Aule();
        ArrayList<Room> Classrooms = new ArrayList<>();

        User user = UserSingleton.getInstance().getUser();


        //-----------------MODIFICABILITÀ DELLE CELLE

        /*tableUser.setEditable(true);
        columnOra.setCellFactory(TextFieldTableCell.forTableColumn());
        columnData.setCellFactory(TextFieldTableCell.forTableColumn());
        columnTipo.setCellFactory(TextFieldTableCell.forTableColumn());
        columnStato.setCellFactory(TextFieldTableCell.forTableColumn());*/

        //---------------------------------------------

        try {

            /*ResultSet rs = connection3.createStatement().executeQuery("SELECT * FROM dbEsame.Aule WHERE fromp='"
            + user.getUsername() + "'");*/
            String completeDB = String.format(Query.completeDB, user.getUsername());
            ResultSet rs = connection3.createStatement().executeQuery(completeDB);
            while (rs.next()) {
                Room room = new Room();

                room.setNome(rs.getString("nome"));
                room.setTipopr(rs.getString("tipopr"));
                room.setDatapr(rs.getString("datapr"));
                room.setInizio(rs.getString("inizio"));
                room.setFine(rs.getString("fine"));
                room.setFromp(rs.getString("fromp"));
                Classrooms.add(room);
            }
            connection3.close();
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }

        return Classrooms;

    }
}


