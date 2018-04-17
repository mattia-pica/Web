package DAO;

import Control.Controller;
import Entity.User;
import Utils.Query;
import Utils.UserSingleton;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;

import static DAO.DB_Connection.conn_Aule;

public class Insert_Secretary {

    public boolean insert(String nameAula, String tipoPrenota, String dataPrenota, LocalTime timeInizioPrenota,
                          LocalTime timeFinePrenota){

        User user = UserSingleton.getInstance().getUser();
        Controller controller = new Controller();

        if (controller.emptyControl(nameAula)){
            try {

                Statement statement = conn_Aule.createStatement();

                //Se esistono aule con valore nullo (cioè mai prenotate) cancella quell'entry nel db e
                //inserisce quella nuova, altrimenti
                //ce ne sarebbero state due nel DB!

                String del = String.format(Query.deleteEmpty);
                statement.executeUpdate(del);

                String insertSecretary = String.format(Query.insert, nameAula, tipoPrenota, dataPrenota, timeInizioPrenota, timeFinePrenota, user.getUsername());

                statement.executeUpdate(insertSecretary);
                String PrenotationInfo = "Signor " + user.getName() + " " + user.getSurname() +  " la prenotazione da " +
                        "lei inserita per l'" + nameAula + " nel giorno " + dataPrenota +
                        " dalle ore " + timeInizioPrenota + " alle ore " + timeFinePrenota + " è stata " +
                        " inserita con successo! ";

                controller.deletedEmail(user.getMail(), "Prenotazione effettuata", PrenotationInfo);
                statement.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
            return true;
        }
        if (!controller.duplicateControl(nameAula, dataPrenota, timeInizioPrenota, timeFinePrenota)){
            System.out.println("duplicate");
            return false;

        }else {

            try {
                Statement statement = conn_Aule.createStatement();

                String insertSecretary = String.format(Query.insert, nameAula, tipoPrenota, dataPrenota, timeInizioPrenota, timeFinePrenota, user.getUsername());

                statement.executeUpdate(insertSecretary);
                String PrenotationInfo = "Signor " + user.getName() + " " + user.getSurname() +  " la prenotazione da " +
                        "lei inserita per l'" + nameAula + " nel giorno " + dataPrenota +
                        " dalle ore " + timeInizioPrenota + " alle ore " + timeFinePrenota + " è stata " +
                        " inserita con successo! ";

                controller.deletedEmail(user.getMail(), "Prenotazione effettuata", PrenotationInfo);
                statement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
