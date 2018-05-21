package DAO;

import Entity.User;
import Utils.Query;
import Utils.UserSingleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalTime;

public class DeleteThenInsert {

    public boolean deleteThenInsert(String nameAula, String tipoPrenota, String dataPrenota, LocalTime timeInizioPrenota,
                                    LocalTime timeFinePrenota){

        User user = UserSingleton.getInstance().getUser();

        Statement stmt = null;
        Connection conn = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(Query.DB_URL, Query.USER, Query.PASS);
            stmt = conn.createStatement();

            //----------------DUPLICATE ENTRY: SI CANCELLANO LE AULE CHE DANNO FASTIDIO ALLA NUOVA

            String deleteSecretary = "DELETE FROM dbEsame.Aule WHERE datapr='" + dataPrenota + "'" +
                    " AND ((inizio<='" + timeInizioPrenota + "' AND fine>='" + timeInizioPrenota + "') " +
                    "OR (fine>='" + timeFinePrenota + "' AND inizio<='" + timeFinePrenota + "') " +
                    "OR (inizio>='" + timeInizioPrenota + "' AND fine<='" + timeFinePrenota + "') " +
                    "OR ((inizio<='" + timeInizioPrenota + "' AND fine>='" + timeInizioPrenota + "')" +
                    " AND (fine>='" + timeFinePrenota + "' AND inizio<='" + timeFinePrenota + "')))";


            stmt.executeUpdate(deleteSecretary);

            //----------------INSERIMENTO PRENOTAZIONE SEGRETARIA-----------------------//

            String insertSecretary = String.format(Query.insert, nameAula, tipoPrenota, dataPrenota, timeInizioPrenota, timeFinePrenota, user.getUsername());
            stmt.executeUpdate(insertSecretary);

            stmt.close();

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
