package DAO;

import Control.Controller;
import Entity.User;
import Utils.UserSingleton;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;

import static DAO.DB_Connection_Aule.conn_Aule;

public class DBInsert_Secretary {

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
                String del = "DELETE FROM dbEsame.Aule WHERE nome='" + nameAula + "'AND tipopr IS NULL";
                statement.executeUpdate(del);

                String insertSecretary = "INSERT INTO dbEsame.Aule (nome, tipopr, datapr, inizio, fine, fromp) " +
                        "VALUES " + "('" + nameAula + "','" + tipoPrenota + "','" + dataPrenota + "','"
                        + timeInizioPrenota + "','" + timeFinePrenota + "','" + user.getUsername() + "')";
                statement.executeUpdate(insertSecretary);

                statement.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return true;
        }
        if (!controller.duplicateControl(dataPrenota, timeInizioPrenota, timeFinePrenota)){
            System.out.println("duplicate");
            return false;

        }else {

            try {
                Statement statement = conn_Aule.createStatement();

                String insertSecretary = "INSERT INTO dbEsame.Aule (nome, tipopr, datapr, inizio, fine, fromp) " +
                        "VALUES " + "('" + nameAula + "','" + tipoPrenota + "','" + dataPrenota + "','"
                        + timeInizioPrenota + "','" + timeFinePrenota + "','" + user.getUsername() + "')";
                statement.executeUpdate(insertSecretary);
                statement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
