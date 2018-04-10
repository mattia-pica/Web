package DAO;

import Utils.DATABASE_Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;

import static DAO.DB_Connection.conn_Aule;

public class EntryController {


    public boolean emptyController(String name){

        try {

            Statement statement = conn_Aule.createStatement();

            ResultSet resultSet = statement.executeQuery(String.format(DATABASE_Utils.emptyControl, name));

            if (resultSet.next()){

                return true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean duplicateController(String name, String dataPrenota, LocalTime timeInizioPrenota, LocalTime timeFinePrenota){

        //Controlla se ci sono aule che vanno in conflitto con i criteri inseriti

        String duplicateControl = "SELECT nome FROM dbEsame.Aule " + "WHERE datapr='" + dataPrenota + "' AND (inizio<='"
                + timeInizioPrenota + "' AND fine>='" + timeInizioPrenota + "' AND nome='" + name + "') " + "" + "OR (fine>='"
                + timeFinePrenota + "' AND inizio<='" + timeFinePrenota + "'AND nome='" + name + "') " + "OR (inizio>='"
                + timeInizioPrenota + "' AND fine<='" + timeFinePrenota + "' AND nome='" + name + "') " + "OR ((inizio<='"
                + timeInizioPrenota + "' AND fine>='" + timeInizioPrenota + "' AND nome='" + name + "') " + "AND (fine>='"
                + timeFinePrenota + "' AND inizio<='" + timeFinePrenota + "'AND nome='" + name + "'))";

        try {
            Statement statement = conn_Aule.createStatement();
            ResultSet resultSet = statement.executeQuery(duplicateControl);
            if (resultSet.next()){
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }


}
