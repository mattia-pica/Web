package Control;

import Bean.Disponible_RoomBean;
import DAO.DBInsert;
import DAO.LoginDB;
import DAO.ShowDatabase_Prof;
import Entity.User;
import Utils.UserSingleton;

import java.time.LocalTime;

public class Controller {

    public User check(String username, String Password){
        LoginDB loginDB = new LoginDB();
        User u = loginDB.findByNameAndPassword(username, Password);
        return u;

    }

    public Disponible_RoomBean show_p(LocalTime timeInizio, LocalTime timeFine, String dateSearch) {
        //ArrayList<Disponible_RoomBean> R;
        Disponible_RoomBean showDatabase_prof = ShowDatabase_Prof.show_prof(timeInizio, timeFine, dateSearch);

        /*ShowAule_Professore showAule_professore = new ShowAule_Professore();
        for (Disponible_RoomBean room : R){
            showAule_professore.parseRoom(room.getNome());

        }*/
        return showDatabase_prof;
    }

    public void createSingleton(User u){
        UserSingleton singleton = UserSingleton.getInstance();
        singleton.setUser(u);
    }

    public boolean newP(String nameAula, String tipoPrenota, String dataPrenota, LocalTime timeInizioPrenota,
                     LocalTime timeFinePrenota, boolean a) {
        DBInsert dbInsert = new DBInsert();
        boolean Response = dbInsert.insert(nameAula, tipoPrenota, dataPrenota, timeInizioPrenota, timeFinePrenota, a);
        if (Response){
            return true;
        }else return false;
    }


}
