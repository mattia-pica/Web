package DAO;

import Entity.Room;
import Entity.User;
import Utils.DATABASE_Utils;
import Utils.UserSingleton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static DAO.DB_Connection.conn_Aule;

public class ShowDatabase_Secr {

    public static ArrayList<Room> show_secr() {

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

            String completeDB = String.format(DATABASE_Utils.completeDB, user.getUsername());
            Statement statement = conn_Aule.createStatement();
            ResultSet rs = statement.executeQuery(completeDB);
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
            statement.close();
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }

        return Classrooms;

    }
}


