package Prove;

import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class Reader {

    String line = null;
    String w = null;

     static class TestLettura {
        public static void main(String args[]){
            Scanner input = new Scanner(System.in);
            String s = input.nextLine();
            System.out.println("Ho letto: " + s);

        }
    }


    public void read(){

        try {
            FileReader fileReader = new FileReader("/home/mattia/Scrivania/vimrc.txt");

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line=bufferedReader.readLine()) != null){
                System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writer(){

        try {

            File file = new File("/home/mattia/Scrivania/nuovo.txt");
            file.createNewFile();
            FileWriter fileReader = new FileWriter(file);
            BufferedWriter buffered = new BufferedWriter(fileReader);

            buffered.write("ciao");

            buffered.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
       /* Reader reader= new Reader();
        reader.read();*/

       Reader reader = new Reader();
       reader.writer();
    }
}
