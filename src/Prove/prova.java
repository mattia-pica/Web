package Prove;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class prova {

    public static void main(String args[]){

        /*SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = "7/01/2013";

        try {

            Date date = formatter.parse(dateInString);
            System.out.println(formatter.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }
*/

        /*String r = "22/02/2018";

        System.out.println(r.substring(3,5));*/

        /*String s = "2018-09-12";
        System.out.println(s.substring(0,4));*/

        /*SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = "2018-09-23";

        try {

            Date date = formatter.parse(dateInString);
            //System.out.println(date);
            System.out.println(formatter.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        String s = "2018-02-01,2018-03-01";
        String[] n = s.split(",");
        String p1 = n[0];
        String p2 = n[1];
        System.out.println("Inizio: " + p1 + " Fine: " +  p2);

        /*Pattern p = Pattern.compile("\\d{2}(/)\\d{2}(/)\\d{4}");

        //Pattern p = Pattern.compile("\\d{2}-\\d{2}-\\d{4}");
        if (p.matcher(r).matches()){
            System.out.println(222);
        }
*/
    }
}
