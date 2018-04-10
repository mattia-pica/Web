package DAO;

import Control.Controller;
import Entity.User;
import Utils.DATABASE_Utils;
import Utils.UserSingleton;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;


public class DBInsert_Prof extends DB_Connection {

    public boolean insert(String nameAula, String tipoPrenota, String dataPrenota, LocalTime timeInizioPrenota,
                          LocalTime timeFinePrenota) {

        User user = UserSingleton.getInstance().getUser();
        Controller controller = new Controller();

        if (controller.emptyControl(nameAula)) {

            try {

                /*DB_Connection db_connection_aule = new DB_Connection();
                db_connection_aule.connect_Aule();*/

                Statement statement = conn_Aule.createStatement();

                String del = String.format(DATABASE_Utils.deleteEmpty, nameAula);
                statement.executeUpdate(del);

                /*String QUERYprof = "INSERT INTO dbEsame.Aule (nome, tipopr, datapr, inizio, fine, fromp) VALUES " + "('"
                        + nameAula + "','" + tipoPrenota + "','" + dataPrenota + "','" + timeInizioPrenota +
                        "','" + timeFinePrenota + "','" + user.getUsername() + "')";*/

                String QUERYprof = String.format(DATABASE_Utils.insert, nameAula, tipoPrenota, dataPrenota, timeInizioPrenota, timeFinePrenota, user.getUsername());

                statement.executeUpdate(QUERYprof);
                statement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (controller.duplicateControl(nameAula, dataPrenota, timeInizioPrenota, timeFinePrenota)){
            System.out.println("duplicate");
            return false;
        }
        return true;

    }
}    
