package Utils;

public class Query {

    public static String PASS = "trottola12";
    public static String USER = "root";
    public static String DB_URL = "jdbc:mysql://localhost/dbEsame";

    //-----------------SEGRETARIA-----------------//

    public static String delete_deleteThenUpdate = "DELETE FROM dbEsame.Aule WHERE ((datapr='%s' AND ID!='%s') AND " +
            "((inizio<='%s' AND fine>='%s') OR (inizio<='%s' AND fine>='%s') OR (inizio>='%s' AND fine <='%s')))";

    public static String deleteSecretary = "DELETE FROM dbEsame.Aule WHERE ((datapr='%s' AND nome='%s') AND " +
            "((inizio<='%s' AND fine>='%s') OR (inizio<='%s' AND fine>='%s') OR (inizio>='%s' AND fine <='%s')))";

    public static String emailInfo_deleteThenInsert = "SELECT DISTINCT Name,Surname,Email,nome,datapr,inizio,fine FROM users JOIN Aule ON fromp=Username WHERE " +
            "((datapr='%s' AND nome='%s') AND ((inizio<='%s' AND fine>='%s') OR (inizio<='%s' AND fine>='%s') OR (inizio>='%s' AND fine<='%s')));";

    public static String newAccYear = "INSERT INTO dbEsame.anni_accademici (DataInizio,DataFine,Nome) VALUES ('%s','%s','%s')";


    //----------------PROFESSORE-------------------//


    //----------------COMUNI-------------------------//

    public static String login = "SELECT * FROM dbEsame.users WHERE Username='%s' AND Password='%s';";

    public static String delete = "DELETE FROM dbEsame.Aule WHERE ID = '%s';";

    public static String completeDB = "SELECT * FROM dbEsame.Aule WHERE fromp='%s';";

    public static String allPrenotation = "SELECT * FROM dbEsame.Aule WHERE tipopr IS NOT NULL;";

    public static String deleteEmpty = "DELETE FROM dbEsame.Aule WHERE nome ='%s' AND tipopr IS NULL;";

    public static String insert = "INSERT INTO dbEsame.Aule (nome, tipopr, datapr, inizio, fine, fromp, sessione) VALUES ('%s', '%s', '%s', '%s', '%s', '%s','%s');";

    public static String modify = "UPDATE Aule SET inizio='%s', fine='%s', datapr='%s', tipopr='%s' WHERE ID='%s';";

    public static String emptyControl = "SELECT * FROM dbEsame.Aule WHERE nome='%s' AND tipopr IS NULL;";

    public static String retrieveUsername = "SELECT DISTINCT Name,Surname,Email FROM users WHERE Username='%s'";

    public static String emailInfo = "SELECT Name,Surname,Email,Username FROM users JOIN Aule ON fromp=Username WHERE ID='%s';";

    public static String classInformation = "SELECT nome, datapr, inizio, fine FROM Aule WHERE ID='%s';";

    public static String activePrenotation = "SELECT nome, datapr, inizio, fine, tipopr FROM Aule WHERE fromp='%s' AND datapr>='%s';";

    public static String duplicateControl = "SELECT * FROM dbEsame.Aule WHERE ((datapr='%s' AND nome='%s') AND " +
            "((inizio<='%s' AND fine>='%s') OR (inizio<='%s' AND fine>='%s') OR (inizio>='%s' AND fine <='%s')))";

    public static String duplicateControl_Modify = "SELECT * FROM dbEsame.Aule WHERE ((datapr='%s' AND ID!='%s') AND " +
            "((inizio<='%s' AND fine>='%s') OR (inizio<='%s' AND fine>='%s') OR (inizio>='%s' AND fine <='%s')))";

    public static String allRooms = "SELECT nome FROM dbEsame.Aule";

    public static String disponibleRooms = "SELECT DISTINCT nome FROM dbEsame.Aule WHERE nome NOT IN (" +
            "SELECT nome FROM dbEsame.Aule WHERE datapr='%s' AND ((inizio<='%s' AND fine>='%s') OR (fine>='%s' AND " +
            "inizio<='%s') OR (inizio>='%s' AND fine <='%s')))";


    //-------------------ANNI ACCADEMICI----------------------//

    public static String retrieveAccYear = "SELECT * FROM anni_accademici WHERE Nome='%s'";

    //--------------------SESSIONI----------------------------//

    public static String sessionControl = "SELECT Nome FROM anni_accademici WHERE (('%s' <= '%s' AND '%s' >= '%s')" +
            "AND ('%s' <= '%s' AND '%s' >= '%s')) ";

    public static String insertSession = "INSERT INTO sessioni (DataInizio, DataFine, Tipo, nome) VALUES ('%s','%s','%s','%s')";

    public static String duplicateSession = "SELECT * FROM dbEsame.sessioni WHERE (nome != '%s' AND ((DataInizio<='%s' " +
            "AND DataFine>='%s') OR (DataInizio<='%s' AND DataFine>='%s') OR (DataInizio>='%s' AND DataFine<='%s')))";

    public static String findSession = "SELECT * FROM sessioni WHERE (DataInizio <= '%s' AND DataFine >= '%s')";

    public static String prenotationOutOfSession = "SELECT ID FROM dbEsame.Aule WHERE sessione='%s'";

    public static String deleteOutOfSession = "DELETE FROM dbEsame.Aule WHERE ID='%s'";

    public static String modifySession = "UPDATE sessioni SET DataInizio='%s', DataFine='%s', Tipo='%s', nome='%s' WHERE nome='%s'";

}
