package Control;

import Bean.Disponible_RoomBean;
import DAO.LoginDB;
import DAO.ShowDatabase_Prof;
import Entity.User;

import java.time.LocalTime;
import java.util.ArrayList;

public class Controller {

    public User check(String username, String Password){
        LoginDB loginDB = new LoginDB();
        User u = loginDB.findByNameAndPassword(username, Password);
        return u;

    }

    public void show_p(LocalTime timeInizio, LocalTime timeFine, String dateSearch) {
        //ArrayList<Disponible_RoomBean> R;
        Disponible_RoomBean showDatabase_prof = ShowDatabase_Prof.show_prof(timeInizio, timeFine, dateSearch);

        /*ShowAule_Professore showAule_professore = new ShowAule_Professore();
        for (Disponible_RoomBean room : R){
            showAule_professore.parseRoom(room.getNome());

        }*/
    }
}
