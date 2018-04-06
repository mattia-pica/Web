package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;

import static DAO.DB_Connection_Aule.conn_Aule;

public class EntryController {


    public boolean emptyController(String name){

        try {

            DB_Connection_Aule connection = new DB_Connection_Aule();
            Connection connection2 = connection.connect_Aule();

            Statement statement = connection2.createStatement();
            String emptyControl = "SELECT * FROM dbEsame.Aule WHERE nome='" + name + "' AND tipopr IS NULL";
            ResultSet resultSet = statement.executeQuery(emptyControl);

            if (resultSet.next()){

                return true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean duplicateController(String dataPrenota, LocalTime timeInizioPrenota, LocalTime timeFinePrenota){

        //Controlla se ci sono aule che vanno in conflitto con i criteri inseriti

        String duplicateControl = "SELECT nome FROM dbEsame.Aule " + "WHERE datapr='" + dataPrenota + "'" +
                " AND (inizio<='" + timeInizioPrenota + "' AND fine>='" + timeInizioPrenota + "') " +
                "" + "OR (fine>='" + timeFinePrenota + "' AND inizio<='" + timeFinePrenota + "') " +
                "OR (inizio>='" + timeInizioPrenota + "' AND fine<='" + timeFinePrenota + "') " +
                "OR ((inizio<='" + timeInizioPrenota + "' AND fine>='" + timeInizioPrenota + "') " +
                "AND (fine>='" + timeFinePrenota + "' AND inizio<='" + timeFinePrenota + "'))";

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
