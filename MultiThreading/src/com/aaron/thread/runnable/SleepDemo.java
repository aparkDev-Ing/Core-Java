package com.aaron.thread.runnable;

class MyRunnableSix implements Runnable{

    @Override
    public void run(){
        System.out.println("Priorty for this thread: " +Thread.currentThread().getPriority());
        for(int i=0; i < 10; i++){
            System.out.println("Iteration "+ i +"/ Thread Name: " +Thread.currentThread().getName() );
            try {
                Thread.currentThread().sleep(2000);
            }
            catch(InterruptedException ie){
                System.out.println("Exception occured during sleeping " +ie.getMessage());
            }
        }
    }
}
public class SleepDemo {

    public static void main(String args[]){

        MyRunnableSix mr1 = new MyRunnableSix();

        Thread thread1 = new Thread(mr1,"james");

        thread1.start();

        try {
            thread1.join();
        }
        catch(InterruptedException ie){
            System.out.println("Exception occured during sleeping " +ie.getMessage());
        }

        System.out.println("Priorty for this thread: " +Thread.currentThread().getPriority());
        for(int i=0; i < 10; i++){
            System.out.println("Iteration "+ i +"/ Thread Name: " +Thread.currentThread().getName() );
        }

    }
}
