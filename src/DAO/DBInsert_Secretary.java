package DAO;

import Entity.User;
import Utils.UserSingleton;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;

import static DAO.DB_Connection_Aule.conn_Aule;

public class DBInsert_Secretary {

    public boolean insert(String nameAula, String tipoPrenota, String dataPrenota, LocalTime timeInizioPrenota,
                          LocalTime timeFinePrenota){

        User user = UserSingleton.getInstance().getUser();

        String controlQuery = "SELECT nome FROM dbEsame.Aule " + "WHERE datapr='" + dataPrenota + "'" +  //Controlla se ci sono aule che vanno in conflitto con i criteri inseriti
                " AND (inizio<='" + timeInizioPrenota + "' AND fine>='" + timeInizioPrenota + "') " +
                "" + "OR (fine>='" + timeFinePrenota + "' AND inizio<='" + timeFinePrenota + "') " +
                "OR (inizio>='" + timeInizioPrenota + "' AND fine<='" + timeFinePrenota + "') " +
                "OR ((inizio<='" + timeInizioPrenota + "' AND fine>='" + timeInizioPrenota + "') " +
                "AND (fine>='" + timeFinePrenota + "' AND inizio<='" + timeFinePrenota + "'))";
        try {
            DB_Connection_Aule db_connection_aule = new DB_Connection_Aule();
            db_connection_aule.connect_Aule();
            Statement statement = conn_Aule.createStatement();
            ResultSet resultSet = statement.executeQuery(controlQuery);
            if (resultSet.next()){
                return false;
            }else {
                try {
                    /*DB_Connection_Aule db_connection_aule = new DB_Connection_Aule();
                    db_connection_aule.connect_Aule();
                    Statement statement = conn_Aule.createStatement();*/
                    //ResultSet resultSet = statement.executeQuery(controlQuery);
                    String insertSecretary = "INSERT INTO dbEsame.Aule (nome, tipopr, datapr, inizio, fine, fromp) " +
                            "VALUES " + "('" + nameAula + "','" + tipoPrenota + "','" + dataPrenota + "','"
                            + timeInizioPrenota + "','" + timeFinePrenota + "','" + user.getUsername() + "')";
                    statement.executeUpdate(insertSecretary);

                } catch (SQLException ex) {
                    return false;
                }

            }
        }catch (SQLException ex1){
            System.out.println(ex1);
        }
        return true;
    }
}
