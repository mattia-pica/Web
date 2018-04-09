package DAO;

import Entity.User;
import Utils.Query;
import Utils.UserSingleton;

import java.sql.Statement;
import java.time.LocalTime;

import static DAO.DB_Connection_Aule.conn_Aule;

public class DeleteThenInsert {

    public boolean deleteThenInsert(String nameAula, String tipoPrenota, String dataPrenota, LocalTime timeInizioPrenota,
                                    LocalTime timeFinePrenota){

        User user = UserSingleton.getInstance().getUser();

        try {

            Statement statement = conn_Aule.createStatement();

            //----------------DUPLICATE ENTRY: SI CANCELLANO LE AULE CHE DANNO FASTIDIO ALLA NUOVA

            String deleteSecretary = "DELETE FROM dbEsame.Aule WHERE datapr='" + dataPrenota + "'" +
                    " AND ((inizio<='" + timeInizioPrenota + "' AND fine>='" + timeInizioPrenota + "') " +
                    "OR (fine>='" + timeFinePrenota + "' AND inizio<='" + timeFinePrenota + "') " +
                    "OR (inizio>='" + timeInizioPrenota + "' AND fine<='" + timeFinePrenota + "') " +
                    "OR ((inizio<='" + timeInizioPrenota + "' AND fine>='" + timeInizioPrenota + "')" +
                    " AND (fine>='" + timeFinePrenota + "' AND inizio<='" + timeFinePrenota + "')))";


            statement.executeUpdate(deleteSecretary);

            //----------------INSERIMENTO PRENOTAZIONE SEGRETARIA-----------------------//

            /*String insertSecretary = "INSERT INTO dbEsame.Aule (nome, tipopr, datapr, inizio, fine, fromp) " +
                    "VALUES " + "('" + nameAula + "','" + tipoPrenota + "','" + dataPrenota + "','"
                    + timeInizioPrenota + "','" + timeFinePrenota + "','" + user.getUsername() + "')";*/

            String insertSecretary = String.format(Query.insert, nameAula, tipoPrenota, dataPrenota, timeInizioPrenota, timeFinePrenota, user.getUsername());
            statement.executeUpdate(insertSecretary);

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
