package com.aaron.basic.java.thread;

public class HappensBeforeDemo {

    static int counter;

     volatile boolean isValid = true;

    public static void main(String args[]) throws InterruptedException{

        HappensBeforeDemo obj = new HappensBeforeDemo();

        new Thread(()-> {
            System.out.println("Thread " +Thread.currentThread().getName()+" is starting.");
            while(obj.isValid){
                    counter++;
            }
            System.out.println("Counter value: " + counter);

        } ,"Thread1").start();

        new Thread(()-> {

            try {
                Thread.sleep(500);
                obj.isValid = false;
            }
            catch(InterruptedException e){
                System.out.println("Thread Interuppted " +e);
                Thread.currentThread().interrupt();
            }
            System.out.println("Thread "+Thread.currentThread().getName() +" is terminating.");
        } ,"Thread2").start();

    }

}

