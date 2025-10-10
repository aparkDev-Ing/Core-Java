package com.aaron.thread.runnable;

public class SynchronizedBlockDemo {

    public int counter;

    public void setCounter1(){

        synchronized (this) {
            for (int i = 0; i < 1000; i++) {
                this.counter = i;
                System.out.println("Incrementing by 1 (first method) " + Thread.currentThread().getName() + " " + this.counter);
            }
        }
    }
    public void setCounter2(){

        synchronized (this) {
            for (int i = 0; i < 1000; i++) {
                this.counter = i;
                System.out.println("Incrementing by 1 (second method) " + Thread.currentThread().getName() + " " + this.counter);
            }
        }
    }



    public static void main(String args[]) throws InterruptedException{


        SynchronizedBlockDemo obj1 = new SynchronizedBlockDemo();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                obj1.setCounter1();
            }
        },"Aaron");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                obj1.setCounter2();
            }
        },"");


        thread1.start();
        thread2.start();

    }

}
