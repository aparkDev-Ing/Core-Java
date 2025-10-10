package com.aaron.thread.runnable;


class MyRunnableFive implements Runnable {

    public static final String word ="Test";
    static Thread main;

    @Override
    public void run()
    {
        //To Demonstrate DeadLock
//        try{
//            main.join();
//        }
//        catch(InterruptedException ie){
//            System.out.println("Error occured during joining: " +ie.getMessage());
//        }

        System.out.println("Priorty for this thread: " +Thread.currentThread().getPriority());
        for(int i=0; i < 10; i++){
            System.out.println("Iteration "+ i +"/ Thread Name: " +Thread.currentThread().getName() );
        }
    }
}
class MyRunnableFour implements Runnable {

    static String data ;
    static Thread i;

    @Override
    public void run()
    {
        System.out.println("Priorty for this thread: " +Thread.currentThread().getPriority());

        try {
            i.join();
        }
        catch(InterruptedException ie){
            System.out.println("Error occured during joining: " +ie.getMessage());
        }

        for(int i=0; i < 10; i++){
            System.out.println("Iteration "+ i +"/ Thread Name: " +Thread.currentThread().getName() );
            data = MyRunnableFive.word;
        }
    }
}
public class JoinDemo {

    public static void main(String args[]){

        MyRunnableFour mr1 = new MyRunnableFour();

        Thread thread1 = new Thread(mr1, "Aaron");

        MyRunnableFive mr2 = new MyRunnableFive();

        Thread thread2 = new Thread(mr2, "");

        mr1.i = thread2;

        mr2.main = Thread.currentThread();

        thread1.start();

        thread2.start();

        //Deadlock two - you cannot join on your own thread
//        try {
//            Thread.currentThread().join();
//        }
//        catch(InterruptedException e){
//
//            System.out.println("Error occured during joining: " +e.getMessage());
//        }

        try {
            thread1.join();
        }
        catch(InterruptedException e){

            System.out.println("Error occured during joining: " +e.getMessage());
        }

        System.out.println("Priorty for this thread: " +Thread.currentThread().getPriority());
        for(int i=0; i < 10; i++){
            System.out.println("Iteration "+ i +"/ Thread Name: " +Thread.currentThread().getName() );
            System.out.println(mr1.data);
        }

    }
}
