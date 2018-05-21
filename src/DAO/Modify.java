package DAO;

import Utils.Query;

import java.sql.*;
import java.time.LocalTime;

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

                Statement stmt = null;
                Connection conn = null;

                Class.forName("com.mysql.jdbc.Driver");

                conn = DriverManager.getConnection(Query.DB_URL, Query.USER, Query.PASS);

                stmt = conn.createStatement();
    

                ResultSet control;
                control = stmt.executeQuery(controlQuery);

                if (control.next()) {
                    return false;
                }
                                stmt.executeUpdate(modify);
                stmt.close();

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        return true;
    }
}
