package DAO;

import Control.Controller;
import Entity.User;
import Utils.UserSingleton;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;

import static DAO.DB_Connection_Aule.conn_Aule;

public class DBInsert_Prof extends DB_Connection_Aule {

    public boolean insert(String nameAula, String tipoPrenota, String dataPrenota, LocalTime timeInizioPrenota,
                          LocalTime timeFinePrenota) {

        User user = UserSingleton.getInstance().getUser();
        Controller controller = new Controller();

        if (controller.emptyControl(nameAula)) {

            try {

                /*DB_Connection_Aule db_connection_aule = new DB_Connection_Aule();
                db_connection_aule.connect_Aule();*/

                Statement statement = conn_Aule.createStatement();

                String del = "DELETE FROM dbEsame.Aule WHERE nome='" + nameAula + "'AND tipopr IS NULL";
                statement.executeUpdate(del);

                String QUERYprof = "INSERT INTO dbEsame.Aule (nome, tipopr, datapr, inizio, fine, fromp) VALUES " + "('"
                        + nameAula + "','" + tipoPrenota + "','" + dataPrenota + "','" + timeInizioPrenota +
                        "','" + timeFinePrenota + "','" + user.getUsername() + "')";

                statement.executeUpdate(QUERYprof);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (controller.duplicateControl(dataPrenota, timeInizioPrenota, timeFinePrenota)){
            System.out.println("duplicate");
            return false;
        }else {

            try {
                Statement statement = conn_Aule.createStatement();

                String insertSecretary;
                insertSecretary = "INSERT INTO dbEsame.Aule (nome, tipopr, datapr, inizio, fine, fromp) " +
                        "VALUES " + "('" + nameAula + "','" + tipoPrenota + "','" + dataPrenota + "','"
                        + timeInizioPrenota + "','" + timeFinePrenota + "','" + user.getUsername() + "')";
                statement.executeUpdate(insertSecretary);
                statement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;

    }
}    
