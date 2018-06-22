package DAO;

import Entity.User;
import Utils.Query;
import Utils.SendMail;
import Utils.UserSingleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;

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

            SendMail sendMail = new SendMail();

            //--------------TROVO GLI UTENTI A CUI SONO VERRANNO CANCELLATE LE PRENOTAZIONI-------------------//

            String us = String.format(Query.emailInfo_deleteThenInsert, dataPrenota, nameAula, timeInizioPrenota, timeInizioPrenota,
                    timeFinePrenota, timeFinePrenota, timeInizioPrenota, timeFinePrenota);

            ResultSet rs = stmt.executeQuery(us);

            while (rs.next()){

                String Nome = rs.getString("Name");
                String Cognome = rs.getString("Surname");
                String email = rs.getString("Email");
                String nomeAula = rs.getString("nome");
                String data = rs.getString("datapr");
                String inizio = rs.getString("inizio");
                String fine = rs.getString("fine");

                String testo = "Signor " + Nome + " " + Cognome + " la prenotazione da " +
                        "lei inserita per l'" + nomeAula + " nel giorno " + data +
                        " dalle ore " + inizio + " alle ore " + fine + " è stata " +
                        " eliminata. Rivolgersi alla segreteria per maggiori informazioni";


                if (!(Nome + Cognome).equals(user.getUsername())){
                    sendMail.inviaMail(email, "Prenotazione Eliminata", testo);

                }
            }


            //----------------DUPLICATE ENTRY: SI CANCELLANO LE AULE CHE DANNO FASTIDIO ALLA NUOVA

            /*String deleteSecretary = "DELETE FROM dbEsame.Aule WHERE (datapr='" + dataPrenota + "' AND nome='" + nameAula + "')" +
                    "AND ((inizio<='" + timeInizioPrenota + "' AND fine>='" + timeInizioPrenota + "') " +
                    "OR (fine>='" + timeFinePrenota + "' AND inizio<='" + timeFinePrenota + "') " +
                    "OR (inizio>='" + timeInizioPrenota + "' AND fine<='" + timeFinePrenota + "') " +
                    "OR ((inizio<='" + timeInizioPrenota + "' AND fine>='" + timeInizioPrenota + "')" +
                    " AND (fine>='" + timeFinePrenota + "' AND inizio<='" + timeFinePrenota + "')))";*/

            String deleteSecretary = String.format(Query.deleteSecretary, dataPrenota, nameAula, timeInizioPrenota, timeInizioPrenota,
                    timeFinePrenota, timeFinePrenota, timeInizioPrenota, timeFinePrenota);

            System.out.println(deleteSecretary);

            //@TODO questa query è stata modificata rispetto alla Stand-Alone BISONGA MODIFCARLA IN ESSA!!!


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
