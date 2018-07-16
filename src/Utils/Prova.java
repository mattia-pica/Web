package Utils;

import Bean.Prenotation_Bean;

import java.util.ArrayList;

public class Prova {

    public boolean met(){

        try {
            ArrayList<Integer> array = new ArrayList<>();
            for (int i =0; i<5; i++){
                array.add(i);
            }

            for (int k =0; k<20; k++){

                System.out.println(array.get(k));
            }
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
            return false;
        }finally {
            System.out.println("ci sto");
        }

        return true;

    }

    public static void main(String[] args){
        Prova prova = new Prova();
        prova.met();
    }



}
