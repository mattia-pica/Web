package DAO;

import Control.Controller;
import Entity.User;
import Utils.Query;
import Utils.UserSingleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalTime;

public class Insert_Secretary {

    public boolean insert(String nameAula, String tipoPrenota, String dataPrenota, LocalTime timeInizioPrenota,
                          LocalTime timeFinePrenota){

        User user = UserSingleton.getInstance().getUser();
        Controller controller = new Controller();

        try {

            Statement stmt = null;
            Connection conn = null;

            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(Query.DB_URL, Query.USER, Query.PASS);

            stmt = conn.createStatement();


            if (controller.emptyControl(nameAula)) {

                //Se esistono aule con valore nullo (cioè mai prenotate) cancella quell'entry nel db e
                //inserisce quella nuova, altrimenti
                //ce ne sarebbero state due nel DB!

                String del = String.format(Query.deleteEmpty, nameAula);
                stmt.executeUpdate(del);

                String insertSecretary = String.format(Query.insert, nameAula, tipoPrenota, dataPrenota, timeInizioPrenota, timeFinePrenota, user.getUsername());

                stmt.executeUpdate(insertSecretary);
                String PrenotationInfo = "Signor " + user.getName() + " " + user.getSurname() + " la prenotazione da " +
                        "lei inserita per l'" + nameAula + " nel giorno " + dataPrenota +
                        " dalle ore " + timeInizioPrenota + " alle ore " + timeFinePrenota + " è stata " +
                        " inserita con successo! ";

                controller.deletedEmail(user.getMail(), "Prenotazione effettuata", PrenotationInfo);
                stmt.close();
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
