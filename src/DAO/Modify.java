package DAO;

import Utils.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;

import static DAO.DB_Connection.conn_Aule;

public class Modify {

    public boolean modify(String id, LocalTime start, LocalTime end, String date, String type) {

        /*String modify = "UPDATE Aule SET inizio='" + start + "', fine='" + end + "', datapr='" + date + "', tipopr='"
                + type + "' WHERE ID='" + id + "'";*/
        String modify = String.format(Query.modify, start, end, date, type, id);

            String controlQuery = "SELECT nome FROM dbEsame.Aule WHERE datapr='" + date + "' AND ID!='" + id +"' AND((inizio<='" + start +
                    "' AND fine>='" + start + "')"
                    + " OR(fine>='" + end + "' AND inizio<='" + end + "') " +
                    " OR(inizio>='" + start + "' AND fine<='" + end + "')"
                    + " OR(inizio<='" + start + "'AND fine>='" + end + "'))";

            try {
    
                /*DB_Connection db_connection_ = new DB_Connection();
                db_connection_.connect_Aule();*/
                Statement stat = conn_Aule.createStatement();
                ResultSet control;
                control = stat.executeQuery(controlQuery);

                if (control.next()) {
                    return false;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {

                Statement statement = conn_Aule.createStatement();
                statement.executeUpdate(modify);
                statement.close();

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        return true;
    }
}
