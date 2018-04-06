package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;

import static DAO.DB_Connection_Aule.conn_Aule;

public class Modify {

    public boolean modify(String id, LocalTime start, LocalTime end, String date, String type) {

        String modify = "UPDATE Aule SET inizio='" + start + "', fine='" + end + "', datapr='" + date + "', tipopr='"
                + type + "' WHERE ID='" + id + "'";




            String controlQuery = "SELECT nome FROM dbEsame.Aule WHERE datapr='" + date + "' AND((inizio<='" + start +
                    "' AND fine>='" + start + "')"
                    + " OR(fine>='" + end + "' AND inizio<='" + end + "') " +
                    " OR(inizio>='" + start + "' AND fine<='" + end + "')"
                    + " OR(inizio<='" + start + "'AND fine>='" + end + "'))";

            try {
    
                DB_Connection_Aule db_connection_aule = new DB_Connection_Aule();
                db_connection_aule.connect_Aule();
                Statement statement = conn_Aule.createStatement();
                ResultSet control = statement.executeQuery(controlQuery);

                if (control.next()) {
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                DB_Connection_Aule db_connection_aule = new DB_Connection_Aule();
                db_connection_aule.connect_Aule();
                Statement statement1 = conn_Aule.createStatement();
                statement1.executeUpdate(modify);

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        return true;
    }
}
