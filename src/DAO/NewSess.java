package DAO;

import Utils.Query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class NewSess {

    public boolean insertSess(String datainizio, String datafine, String tipo, String accYear){

        Statement stmt = null;
        Connection conn = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(Query.DB_URL, Query.USER, Query.PASS);
            stmt = conn.createStatement();

            //---------------------CONTROLLO CHE LE DATE DELLA SESSIONE RIPSETTINO QUELLE DELL'ANNO ACCADEMICO SELEZIONATO------------//


            String query = String.format(Query.retrieveAccYear, accYear);

            ResultSet resultSet = stmt.executeQuery(query);   //Trovo i dati dell'anno selezionato

            String annoInizio = null;
            String annoFine = null;
            String annoNome = null;

            while (resultSet.next()){

                annoInizio=resultSet.getString("DataInizio");
                annoFine=resultSet.getString("DataFine");
                annoNome=resultSet.getString("Nome");

            }





            stmt.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
