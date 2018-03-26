package DAO;

import Control.Controller;
import Utils.UserSingleton;

import java.lang.annotation.Documented;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;

import static DAO.DB_Connection_Users.conn_Users;

public class DBInsert extends DB_Connection_Aule {

        public boolean insert(String nameAula, String tipoPrenota, String dataPrenota, LocalTime timeInizioPrenota,
                           LocalTime timeFinePrenota, boolean a) {

            //boolean Response = true;
            //---------------------PROFESSORE------------------//

            if (!a) {
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
                        /*Controller controller = new Controller();
                        controller.duplicate();*/
                        //@TODO Gestione Entry duplicata nel database
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


                        //-----------STAMPA FILE CON DATI PRENOTAZIONE-------------\\


                        /*String nameProf = "";
                        String surnameProf = "";

                        String getProfName = "SELECT users.Name FROM users WHERE Username='" + userSingleton.getUser().getUsername() + "'";
                        String getProfSurname = "SELECT users.Surname FROM users WHERE Username='" + userSingleton.getUser().getUsername() + "'";
                        DB_Connection_Users db_connection_users = new DB_Connection_Users();
                        db_connection_users.connect_Users();
                        Statement statement3 = conn_Users.createStatement();
                        ResultSet rsName = statement3.executeQuery(getProfName);
                        if (rsName.next()) {
                            nameProf = rsName.getString("Name");
                        }
                        statement.close();
                        statement3.close();
                        Statement statement1 = conn_Users.createStatement();
                        ResultSet rsSurname = statement1.executeQuery(getProfSurname);
                        if (rsSurname.next()) {
                            surnameProf = rsSurname.getString("Surname");
                            statement1.close();
                        }
                        statement1.close();*/

                        //@TODO A prenotazione inserita mostrare un alert con messaggio di successo
                        return true;
                    }
                } catch (Exception ex) {
                    System.err.println(ex);
                }

                //------------------SEGRETARIA--------------------//


            } else {

                //@TODO Controllare prenotazione segretaria

                String controlQuery = "SELECT DISTINCT nome FROM dbEsame.Aule WHERE nome NOT IN (SELECT nome FROM dbEsame.Aule " +
                        "WHERE datapr='" + dataPrenota + "'" +
                        " AND ((inizio<='" + timeInizioPrenota + "' AND fine>='" + timeInizioPrenota + "') " +
                        "" + "OR (fine>='" + timeFinePrenota + "' AND inizio<='" + timeFinePrenota + "') " +
                        "OR (inizio>='" + timeInizioPrenota + "' AND fine<='" + timeFinePrenota + "') " +
                        "OR ((inizio<='" + timeInizioPrenota + "' AND fine>='" + timeInizioPrenota + "') " +
                        "AND (fine>='" + timeFinePrenota + "' AND fine<='" + timeFinePrenota + "'))))";
                try {
                    DB_Connection_Aule db_connection_aule = new DB_Connection_Aule();
                    db_connection_aule.connect_Aule();
                    Statement statement = conn_Aule.createStatement();
                    ResultSet resultSet = statement.executeQuery(controlQuery);
                    if (!resultSet.wasNull()) {



                        //----------------DUPLICATE ENTRY: SI CANCELLANO LE AULE CHE DANNO FASTIDIO ALLA NUOVA
                        //----------------PRENOTAZIONE-----------------------//

                        try {
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
                            statement1.close();
                            statement.close();
                            String insertSecretary = "INSERT INTO dbEsame.Aule (nome, tipopr, datapr, inizio, fine, fromp) " +
                                    "VALUES " + "('" + nameAula + "','" + tipoPrenota + "','" + dataPrenota + "','"
                                    + timeInizioPrenota + "','" + timeFinePrenota + "','Secretary')";
                            DB_Connection_Aule db_connection_aule2 = new DB_Connection_Aule();
                            db_connection_aule2.connect_Aule();
                            Statement statement2 = conn_Aule.createStatement();
                            statement2.executeUpdate(insertSecretary);
                            statement2.close();


                        } catch (Exception ex1) {
                            ex1.printStackTrace();
                        }
                    }
                } catch (Exception ex) {
                    System.err.println(ex);
                }
            }
            return true;
        }
    }
