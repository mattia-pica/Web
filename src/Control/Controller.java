package Control;

import DAO.LoginDB;
import Entity.User;

public class Controller {

    public User check(String username, String Password){
        LoginDB loginDB = new LoginDB();
        User u = loginDB.findByNameAndPassword(username, Password);
        return u;

    }
}
