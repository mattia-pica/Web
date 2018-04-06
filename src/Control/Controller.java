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

    public ArrayList<Room> showComplete_DB(){
        ArrayList<Room> rooms = ShowCompleteDB.show_completeDB();
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
        return dbInsert_secretary.insert(nameAula, tipoPrenota, dataPrenota, timeInizioPrenota, timeFinePrenota);

    }

    //------------------CANCELLA PRENOTAZIONI GIÀ ESISTENTI CHE DANNO CONFLITTO E POI INSERISCI LA NUOVA PRENOTAZIONE-------------//

    public boolean deleteThenInsert(String nameAula, String tipoPrenota, String dataPrenota, LocalTime timeInizioPrenota,
                                    LocalTime timeFinePrenota){

        DeleteThenInsert deleteThenInsert = new DeleteThenInsert();
        deleteThenInsert.deleteThenInsert(nameAula, tipoPrenota, dataPrenota, timeInizioPrenota, timeFinePrenota);

        return true;
    }

    //-----------------------MODIFICA PRENOTAZIONE------------------//

    public boolean modify(String ID, LocalTime start, LocalTime end, String date, String type){

        Modify modify = new Modify();
        return modify.modify(ID, start, end, date, type);

    }

    //------------------CANCELLA PRENOTAZIONI GIÀ ESISTENTI CHE DANNO CONFLITTO E POI AGGIORNA LA PRENOTAZIONE SELEZIONATA-------------//

    public boolean deleteThenUpdate(String id, LocalTime start, LocalTime end, String date, String type){

        DeleteThenUpdate deleteThenUpdate = new DeleteThenUpdate();
        return deleteThenUpdate.deletethenUpdate(id, start, end, date, type);

    }

    public boolean duplicateControl(String dataPrenota, LocalTime timeInizioPrenota, LocalTime timeFinePrenota){

        EntryController entryController = new EntryController();
        return entryController.duplicateController(dataPrenota, timeInizioPrenota, timeFinePrenota);

    }

    public boolean emptyControl(String name){

        EntryController entryController = new EntryController();
        return entryController.emptyController(name);
    }

    //--------------------CREAZIONE SINGLETON--------------------//

    public void createSingleton(User u){
        UserSingleton singleton = UserSingleton.getInstance();
        singleton.setUser(u);
    }
}
