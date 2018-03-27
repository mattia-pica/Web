package Control;

import Bean.Disponible_RoomBean;
import DAO.*;
import Entity.Room;
import Entity.User;
import Utils.UserSingleton;

import java.time.LocalTime;
import java.util.ArrayList;

public class Controller {

    public User check(String username, String Password){
        LoginDB loginDB = new LoginDB();
        User u = loginDB.findByNameAndPassword(username, Password);
        return u;

    }

    public Disponible_RoomBean show_p(LocalTime timeInizio, LocalTime timeFine, String dateSearch) {
        //ArrayList<Disponible_RoomBean> R;
        Disponible_RoomBean showDatabase_prof = ShowDatabase_Prof.show_prof(timeInizio, timeFine, dateSearch);
        return showDatabase_prof;
    }

    public ArrayList<Room> show_s() {
        //ArrayList<Disponible_RoomBean> R;
        ArrayList<Room> rooms = ShowDatabase_Secr.show_secr();
        return rooms;
    }

    /*public ArrayList<Room> show_s_complete(){
        ArrayList<Room> rooms = ShowDatabase_Secr.show_secr(timeInizio, timeFine, dateSearch);
        return rooms;
    }*/

    public void createSingleton(User u){
        UserSingleton singleton = UserSingleton.getInstance();
        singleton.setUser(u);
    }

    public boolean newPrenotationProfessore(String nameAula, String tipoPrenota, String dataPrenota, LocalTime timeInizioPrenota,
                                            LocalTime timeFinePrenota, boolean a) {
        DBInsert_Prof dbInsertProf = new DBInsert_Prof();
        boolean Response = dbInsertProf.insert(nameAula, tipoPrenota, dataPrenota, timeInizioPrenota, timeFinePrenota, a);
        if (Response){
            return true;
        }else return false;
    }

    public boolean newPrenotationSecretary(String nameAula, String tipoPrenota, String dataPrenota, LocalTime timeInizioPrenota,
                                           LocalTime timeFinePrenota, boolean a){
        DBInsert_Secretary dbInsert_secretary = new DBInsert_Secretary();
        boolean response = dbInsert_secretary.insert(nameAula, tipoPrenota, dataPrenota, timeInizioPrenota, timeFinePrenota);
        if (response){
            return true;
        }else return false;
    }

    public ArrayList<Room> showCompleteDB_Prof(){
        ArrayList<Room> rooms = ShowCompleteDB_Prof.show_completeDB();
        return rooms;
    }


}
