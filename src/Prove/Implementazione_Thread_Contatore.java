package Prove;

import static java.lang.Thread.sleep;

public class Implementazione_Thread_Contatore implements Runnable{


    @Override
    public void run() {

        for (int i=0; i<5; i++){

            System.out.println(i);
            try{

                sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    public static void main(String[] args){

        Implementazione_Thread_Contatore contatore = new Implementazione_Thread_Contatore();

        Thread t = new Thread(contatore);

        t.start();




    }
}
