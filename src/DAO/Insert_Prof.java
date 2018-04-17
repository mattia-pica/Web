package DAO;

import Control.Controller;
import Entity.User;
import Utils.Query;
import Utils.UserSingleton;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;


public class Insert_Prof extends DB_Connection {

    public boolean insert(String nameAula, String tipoPrenota, String dataPrenota, LocalTime timeInizioPrenota,
                          LocalTime timeFinePrenota) {

        User user = UserSingleton.getInstance().getUser();
        Controller controller = new Controller();

        if (!controller.duplicateControl(nameAula, dataPrenota, timeInizioPrenota, timeFinePrenota)){
            System.out.println("duplicate");
            return false;
        }

        if (controller.emptyControl(nameAula)) {

            try {

                Statement statement = conn_Aule.createStatement();

                String del = String.format(Query.deleteEmpty, nameAula);
                statement.executeUpdate(del);

                String QUERYprof = String.format(Query.insert, nameAula, tipoPrenota, dataPrenota, timeInizioPrenota, timeFinePrenota, user.getUsername());

                statement.executeUpdate(QUERYprof);

                String PrenotationInfo = "Signor " + user.getName() + " " + user.getSurname() +  " la prenotazione da " +
                        "lei inserita per l'" + nameAula + " nel giorno " + dataPrenota +
                        " dalle ore " + timeInizioPrenota + " alle ore " + timeFinePrenota + " è stata " +
                        " inserita con successo! ";

                controller.deletedEmail(user.getMail(), "Prenotazione effettuata", PrenotationInfo);
                statement.close();



            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }else {

            try {
                Statement statement = conn_Aule.createStatement();

                String insertProf = String.format(Query.insert, nameAula, tipoPrenota, dataPrenota, timeInizioPrenota, timeFinePrenota, user.getUsername());

                String PrenotationInfo = "Signor " + user.getName() + " " + user.getSurname() +  " la prenotazione da " +
                        "lei inserita per l'" + nameAula + " nel giorno " + dataPrenota +
                        " dalle ore " + timeInizioPrenota + " alle ore " + timeFinePrenota + " è stata " +
                        " inserita con successo! ";

                controller.deletedEmail(user.getMail(), "Prenotazione effettuata", PrenotationInfo);
                statement.executeUpdate(insertProf);
                statement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;

    }
}    
