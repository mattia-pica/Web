package DAO;

import Bean.UserBean;
import Control.Controller;
import Entity.User;
import Utils.DATABASE_Utils;
import Utils.UserSingleton;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static DAO.DB_Connection.conn_Aule;

public class Delete {

    public boolean delete(String ID){

        Controller controller = new Controller();
        User user = UserSingleton.getInstance().getUser();
        UserBean userBean = new UserBean();
        String nome = null;
        String data = null;
        String inizio = null;
        String fine = null;

        try {

            Statement statement = conn_Aule.createStatement();

            String emailInfo = String.format(DATABASE_Utils.emailInfo); //Recupero i dati dell'utente a cui è stata eliminata la prenotazione

            ResultSet resultSet = statement.executeQuery(emailInfo);

            if (resultSet.next()){

                userBean.setName(resultSet.getString("Name"));
                userBean.setSurname(resultSet.getString("Surname"));
                userBean.setEmail(resultSet.getString("Email"));
            }

            String sql = String.format(DATABASE_Utils.delete, ID);   //Cancello l'aula
            String classInformation = String.format(DATABASE_Utils.classInformation, ID);
            ResultSet info = statement.executeQuery(classInformation); //Recupero i dati dell'aula eliminata

            while (info.next()){
                nome = info.getString("nome");
                data = info.getString("datapr");
                inizio = info.getString("inizio");
                fine = info.getString("fine");

                System.out.println(nome + data + inizio + fine);
            }

            statement.executeUpdate(sql);

            String deleteInformation = "Signor " + userBean.getName() + " " + userBean.getSurname() +  " la prenotazione da " +
                    "lei inserita per l'" + nome + " nel giorno " + data +
                    " dalle ore " + inizio + " alle ore " + fine + " è stata " +
                    " eliminata da " + user.getName() + " " + user.getSurname();

            controller.deletedEmail(user.getMail(), userBean.getEmail(), "Eliminazione effettuata", deleteInformation);

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

}
