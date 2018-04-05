package DAO;

import Utils.UserSingleton;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalTime;

import static DAO.DB_Connection_Users.conn_Users;

public class DBInsert_Prof extends DB_Connection_Aule {

    public boolean insert(String nameAula, String tipoPrenota, String dataPrenota, LocalTime timeInizioPrenota,
                          LocalTime timeFinePrenota) {

        //---------------------PROFESSORE------------------//

        try {
            String controlQuery = "SELECT nome FROM dbEsame.Aule WHERE nome='" + nameAula + "' AND((datapr='" + dataPrenota
                    + "' AND inizio<='" + timeInizioPrenota + "' AND fine>='" + timeInizioPrenota + "')"
                    + "OR(datapr='" + dataPrenota + "' AND fine>='" + timeFinePrenota + "' AND inizio<='" + timeFinePrenota + "') " +
                    "OR(datapr='" + dataPrenota + "' AND inizio>='" + timeInizioPrenota + "' AND fine<='" + timeFinePrenota + "')"
                    + "OR(datapr='" + dataPrenota + "'AND inizio<='" + timeInizioPrenota + "'AND fine>='" + timeFinePrenota + "'))";
            DB_Connection_Aule db_connection_aule = new DB_Connection_Aule();
            db_connection_aule.connect_Aule();
            Statement statement = conn_Aule.createStatement();
            ResultSet rs = statement.executeQuery(controlQuery);
            if (rs.next()) {
                System.out.println("duplicate");
                return false;
            } else {

                //Se esistono aule con valore nullo (cioè mai prenotate) cancella quell'entry nel db e inserisce quella nuova, altrimenti
                //ce ne sarebbero state due nel DB!
                String del = "DELETE FROM dbEsame.Aule WHERE nome='" + nameAula + "'AND tipopr IS NULL";
                statement.executeUpdate(del);

                UserSingleton userSingleton = UserSingleton.getInstance();
                String QUERYprof = "INSERT INTO dbEsame.Aule (nome, tipopr, datapr, inizio, fine, fromp) VALUES " + "('"
                        + nameAula + "','" + tipoPrenota + "','" + dataPrenota + "','" + timeInizioPrenota +
                        "','" + timeFinePrenota + "','" + userSingleton.getUser().getUsername() + "')";
                DB_Connection_Aule db_connection_aule1 = new DB_Connection_Aule();
                db_connection_aule1.connect_Aule();
                statement.executeUpdate(QUERYprof);
                return true;
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return true;
    }
}    
