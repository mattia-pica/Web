package DAO;

import Utils.Query;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;

import static DAO.DB_Connection_Aule.conn_Aule;


public class DeleteThenUpdate {

    public boolean deletethenUpdate(String id, LocalTime start, LocalTime end, String date, String type){

        try {
            String deleteQuery = "DELETE FROM dbEsame.Aule WHERE datapr='" + date + "' AND((inizio<='" + start +
                    "' AND fine>='" + start + "' AND ID !='" + id + "')"
                    + " OR(fine>='" + end + "' AND inizio<='" + end + "'AND ID !='" +id +"') " +
                    " OR(inizio>='" + start + "' AND fine<='" + end + "'AND ID !='" +id +"')"
                    + " OR(inizio<='" + start + "'AND fine>='" + end + "'AND ID !='" +id +"'))";

            /*String modify = "UPDATE Aule SET inizio='" + start + "', fine='" + end + "', datapr='" + date + "', tipopr='"
                    + type + "' WHERE ID='" + id + "'";*/

            String modify = String.format(Query.modify, start, end, date, type, id);

            Statement statement = conn_Aule.createStatement();
            statement.executeUpdate(deleteQuery);
            statement.executeUpdate(modify);
            statement.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
}
