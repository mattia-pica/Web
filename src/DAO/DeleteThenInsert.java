package DAO;

import Entity.User;
import Utils.UserSingleton;

import java.sql.Statement;
import java.time.LocalTime;

import static DAO.DB_Connection_Aule.conn_Aule;

public class DeleteThenInsert {

    public boolean deleteThenInsert(String nameAula, String tipoPrenota, String dataPrenota, LocalTime timeInizioPrenota,
                                    LocalTime timeFinePrenota){

        User user = UserSingleton.getInstance().getUser();

        try {

            //----------------DUPLICATE ENTRY: SI CANCELLANO LE AULE CHE DANNO FASTIDIO ALLA NUOVA

            String deleteSecretary = "DELETE FROM dbEsame.Aule WHERE datapr='" + dataPrenota + "'" +
                    " AND ((inizio<='" + timeInizioPrenota + "' AND fine>='" + timeInizioPrenota + "') " +
                    "OR (fine>='" + timeFinePrenota + "' AND inizio<='" + timeFinePrenota + "') " +
                    "OR (inizio>='" + timeInizioPrenota + "' AND fine<='" + timeFinePrenota + "') " +
                    "OR ((inizio<='" + timeInizioPrenota + "' AND fine>='" + timeInizioPrenota + "')" +
                    " AND (fine>='" + timeFinePrenota + "' AND inizio<='" + timeFinePrenota + "')))";

            DB_Connection_Aule db_connection_aule1 = new DB_Connection_Aule();
            db_connection_aule1.connect_Aule();
            Statement statement1 = conn_Aule.createStatement();
            statement1.executeUpdate(deleteSecretary);

            //----------------PRENOTAZIONE SEGRETARIA-----------------------//

            String insertSecretary = "INSERT INTO dbEsame.Aule (nome, tipopr, datapr, inizio, fine, fromp) " +
                    "VALUES " + "('" + nameAula + "','" + tipoPrenota + "','" + dataPrenota + "','"
                    + timeInizioPrenota + "','" + timeFinePrenota + "','" + user.getUsername() + "')";
            DB_Connection_Aule db_connection_aule2 = new DB_Connection_Aule();
            db_connection_aule2.connect_Aule();
            Statement statement2 = conn_Aule.createStatement();
            statement2.executeUpdate(insertSecretary);

            statement1.close();
            statement2.close();

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
