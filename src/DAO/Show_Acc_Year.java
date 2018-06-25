package DAO;

import Bean.Accademic_Year;
import Utils.Query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Show_Acc_Year {

    public ArrayList<Accademic_Year> show() {

        ArrayList<Accademic_Year> year = new ArrayList<>();

        try {

            Statement stmt = null;
            Connection conn = null;

            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(Query.DB_URL, Query.USER, Query.PASS);

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM anni_accademici");

            while (rs.next()) {

                Accademic_Year accademic_year = new Accademic_Year();

                accademic_year.setDataInizio(rs.getString("datainizio"));
                accademic_year.setDataFine(rs.getString("datafine"));
                accademic_year.setNome(rs.getString("nome"));
                year.add(accademic_year);

            }

            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return year;
    }
}
