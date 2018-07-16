package Prove;

public class Monitor {

    /*private final Object monitor = new Object();


    public class Mythread implements Runnable {

        void goo() {

            synchronized (monitor){
                System.out.println("Before Wait");
                try {
                    wait();
                    System.out.println("After Wait");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
        @Override
        public void run() {
            goo();
        }
    }

    public class Mythread1 implements Runnable {

        void foo() {
            synchronized (monitor){
                System.out.println("Before Notify");
                notify();
                System.out.println("After Notify");
            }
        }
        @Override
        public void run() {
            foo();
        }
    }

    public static class Test {
        public static void main(String[] args) {
            Monitor prova = new Monitor();
            prova.method();
        }
    }

    public void method(){
        Thread t = new Thread(new Mythread());
        Thread t1 = new Thread(new Mythread1());

        t.start();
        t1.start();

    }*/
    public static class ThreadA {
        public static void main(String[] args){
            ThreadB b = new ThreadB();
            b.start();

            synchronized(b){
                try{
                    System.out.println("Waiting for b to complete...");
                    b.wait();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }

                System.out.println("Total is: " + b.total);
            }
        }
    }

    static class ThreadB extends Thread{
        int total;
        @Override
        public void run(){
            synchronized(this){
                for(int i=0; i<5 ; i++){
                    total += i;
                }
                notify();
            }
        }
    }

}
