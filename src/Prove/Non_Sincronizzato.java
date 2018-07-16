package Prove;

public class Non_Sincronizzato {

    static class Table{

        void printTable(int n){

                for(int i=1;i<=5;i++){
                    System.out.println(n*i);
                    try{
                        Thread.sleep(400);
                    }catch(Exception e){System.out.println(e);}
            }
        }

        //end of the method
    }

    static class MyThread1 extends Thread{
        Non_Sincronizzato.Table t;
        MyThread1(Non_Sincronizzato.Table t){
            this.t=t;
        }
        public void run(){
            t.printTable(5);
        }

    }
    static class MyThread2 extends Thread{
        Non_Sincronizzato.Table t;
        MyThread2(Non_Sincronizzato.Table t){
            this.t=t;
        }
        public void run(){
            t.printTable(100);
        }
    }

    public static class TestSynchronizedBlock1{
        public static void main(String args[]){
            Non_Sincronizzato.Table obj = new Non_Sincronizzato.Table();//only one object
            Non_Sincronizzato.MyThread1 t1=new Non_Sincronizzato.MyThread1(obj);
            Non_Sincronizzato.MyThread2 t2=new Non_Sincronizzato.MyThread2(obj);
            t1.start();
            t2.start();
        }
    }


}
