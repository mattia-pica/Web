package Control;

import Bean.Accademic_Year;
import Bean.Disponible_RoomBean;
import Bean.Prenotation_Bean;
import Bean.RoomBean;
import DAO.*;
import Entity.Room;
import Entity.User;
import Utils.PrenotationBeanSingleton;
import Utils.SendMail;
import Utils.UserSingleton;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class Controller {

    //--------------LOGIN---------------//

    public User check(String username, String Password){
        LoginDB loginDB = new LoginDB();
        User u = loginDB.findByNameAndPassword(username, Password);
        return u;
    }

    //------AULE DISPONIBILI-------------//

    public Disponible_RoomBean show(LocalTime timeInizio, LocalTime timeFine, String dateSearch) {
        //ArrayList<Disponible_RoomBean> R;
        Disponible_RoomBean showDatabase = DisponibleRooms.show(timeInizio, timeFine, dateSearch);
        return showDatabase;
    }

    //-------PRENOTAZIONI ATTIVE----------//

    public ArrayList<Room> active(){
        ArrayList<Room> rooms = ActivePrenotation.show();
        return rooms;
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

    //-------------MOSTRA TUTTE LE PRENOTAZIONI (SEGRETARIA)-----------//

    public ArrayList<Room> allPrenotation(){
        ArrayList<Room> rooms = All_Prenotation.showAll();
        return rooms;
    }

    //-----------------TUTTE LE AULE-----------------------------------//

    public ArrayList<RoomBean> allRooms(){
        ArrayList<RoomBean> rooms = All_Rooms.AllRooms();
        return rooms;
    }

    //----------------NUOVA PRENOTAZIONE PROFESSORE--------------//
    public boolean newPrenotationProfessore(String nameAula, String tipoPrenota, String dataPrenota, LocalTime timeInizioPrenota,
                                            LocalTime timeFinePrenota) {
        Insert_Prof dbInsertProf = new Insert_Prof();
        return dbInsertProf.insert(nameAula, tipoPrenota, dataPrenota, timeInizioPrenota, timeFinePrenota);
    }

    //---------------------NUOVA PRENOTAZIONE SEGRETARIA-------------//

    public boolean newPrenotationSecretary(String nameAula, String tipoPrenota, String dataPrenota, LocalTime timeInizioPrenota,
                                           LocalTime timeFinePrenota){
        Insert_Secretary insert_secretary = new Insert_Secretary();
        return insert_secretary.insert(nameAula, tipoPrenota, dataPrenota, timeInizioPrenota, timeFinePrenota);

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

    //--------------CANCELLAZIONE PRENOTAZIONE----------//

    public boolean delete(String ID){

        Delete delete = new Delete();
        return delete.delete(ID);

    }

    //-----------CONTROLLO ENTRY DUPLICATE---------//

    public boolean duplicateControl(String name, String dataPrenota, LocalTime timeInizioPrenota, LocalTime timeFinePrenota){

        EntryController entryController = new EntryController();
        return entryController.duplicateController(name, dataPrenota, timeInizioPrenota, timeFinePrenota);

    }

    //---------------CONTROLLO ENTRY VUOTE-------------//

    public boolean emptyControl(String name){

        EntryController entryController = new EntryController();
        return entryController.emptyController(name);
    }


    //------------EMAIL DI NOTIFICA CANCELLAZIONE PRENOTQZIONE---------//

    public void deletedEmail(String dest, String object, String text){

        SendMail sendMail = new SendMail();
        sendMail.inviaMail(dest, object, text);

    }

    //--------------------CREAZIONE SINGLETON--------------------//

    public void createSingleton(User u){
        UserSingleton singleton = UserSingleton.getInstance();
        singleton.setUser(u);
    }

    //-----------------BEAN----------------------//

    public void createPrenotationBean(LocalTime inizio, LocalTime fine, String date){

        Prenotation_Bean prenotation_bean = new Prenotation_Bean(inizio, fine, date);
        PrenotationBeanSingleton.getInstance().setPrenotation_bean(prenotation_bean);


    }


    //--------------------ANNI ACCADEMICI--------------------------//

    public boolean newYear(String datainizio, String datafine){

        NewAccYear newAccYear = new NewAccYear();

        return newAccYear.newYaear(datainizio, datafine);
    }

    public ArrayList<Accademic_Year> showYears(){

        Show_Acc_Year show_acc_year = new Show_Acc_Year();
        return show_acc_year.show();

    }

    //-------------------SESSIONI-----------------------//

    public boolean newSess(String datainizio, String datafine, String tipo, String accYear){
        NewSess newSess = new NewSess();
        return newSess.insertSess(datainizio, datafine, tipo, accYear);
    }

    /*public void createPrenotationBean(String nome, LocalTime inizio, LocalTime fine, String date, String tipo){

        Prenotation_Bean prenotation_bean = new Prenotation_Bean(nome, inizio, fine, date, tipo);

    }*/

    /*public void createPrenotationSingleton(String nome, LocalTime inizio, LocalTime fine, String date, String tipo){

        Prenotation_Bean prenotation_bean = new Prenotation_Bean(nome, inizio, fine, date, tipo);
        PrenotationBeanSingleton.getInstance().setPrenotation_bean(prenotation_bean);

    }*/
}
