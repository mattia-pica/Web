package Control;

import Bean.Disponible_RoomBean;
import DAO.*;
import Entity.Room;
import Entity.User;
import Utils.UserSingleton;

import java.time.LocalTime;
import java.util.ArrayList;

public class Controller {

    //--------------LOGIN---------------//

    public User check(String username, String Password){
        LoginDB loginDB = new LoginDB();
        User u = loginDB.findByNameAndPassword(username, Password);
        return u;
    }

    //------AULE DISPONIBILI PROFESSORE-------------//

    public Disponible_RoomBean show(LocalTime timeInizio, LocalTime timeFine, String dateSearch) {
        //ArrayList<Disponible_RoomBean> R;
        Disponible_RoomBean showDatabase_prof = ShowDatabase_Prof.show_prof(timeInizio, timeFine, dateSearch);
        return showDatabase_prof;
    }

    //-----------PRENOTAZIONI EFFETUATE SEGRETARIA----------------//

    public ArrayList<Room> show_s() {
        //ArrayList<Disponible_RoomBean> R;
        ArrayList<Room> rooms = ShowDatabase_Secr.show_secr();
        return rooms;
    }

    //--------------PRENOTAZIONI EFFETTUATE PROFESSORE---------------//

    public ArrayList<Room> showCompleteDB_Prof(){
        ArrayList<Room> rooms = ShowCompleteDB_Prof.show_completeDB();
        return rooms;
    }

    //-------------MOSTRA TUTTE LE PRENOTAZIONI SEGRETARIA-----------//

    public ArrayList<Room> allPrenotation(){
        ArrayList<Room> rooms = All_Prenotation.showAll();
        return rooms;
    }

    //----------------NUOVA PRENOTAZIONE PROFESSORE--------------//
    public boolean newPrenotationProfessore(String nameAula, String tipoPrenota, String dataPrenota, LocalTime timeInizioPrenota,
                                            LocalTime timeFinePrenota) {
        DBInsert_Prof dbInsertProf = new DBInsert_Prof();
        return dbInsertProf.insert(nameAula, tipoPrenota, dataPrenota, timeInizioPrenota, timeFinePrenota);
    }

    //---------------------NUOVA PRENOTAZIONE SEGRETARIA-------------//

    public boolean newPrenotationSecretary(String nameAula, String tipoPrenota, String dataPrenota, LocalTime timeInizioPrenota,
                                           LocalTime timeFinePrenota){
        DBInsert_Secretary dbInsert_secretary = new DBInsert_Secretary();
        boolean response = dbInsert_secretary.insert(nameAula, tipoPrenota, dataPrenota, timeInizioPrenota, timeFinePrenota);
        if (response) return true;
        else return false;
    }

    //------------------CANCELLA PRENOTAZIONI GIÀ ESISTENTI CHE DANNO CONFLITTO E POI INSERISCI LA NUOVA PRENOTAZIONE-------------//

    public boolean deleteThenInsert(String nameAula, String tipoPrenota, String dataPrenota, LocalTime timeInizioPrenota,
                                    LocalTime timeFinePrenota){

        DeleteThenInsert deleteThenInsert = new DeleteThenInsert();
        deleteThenInsert.deleteThenInsert(nameAula, tipoPrenota, dataPrenota, timeInizioPrenota, timeFinePrenota);

        return true;
    }

    //--------------------CREAZIONE SINGLETON--------------------//

    public void createSingleton(User u){
        UserSingleton singleton = UserSingleton.getInstance();
        singleton.setUser(u);
    }
}
