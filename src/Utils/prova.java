package Utils;

import javax.print.DocFlavor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class prova {

    public static void main(String args[]){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
/*
        System.out.println(dtf.format(localDate)); //2016/11/16
*/
        String d = dtf.format(localDate);
        System.out.println(d);


    }
}
