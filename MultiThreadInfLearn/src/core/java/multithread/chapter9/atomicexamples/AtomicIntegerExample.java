package core.java.multithread.chapter9.atomicexamples;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {

    final static int noOfDishesPerChef = 15;
    static AtomicInteger totalNoOfDishes = new AtomicInteger(0);

    static void increment(){
        totalNoOfDishes.incrementAndGet();
    }
    public static void main(String args[]){


        Thread chef1 = new Thread(()->
        {

            for(int i=0; i<noOfDishesPerChef;i++ ){

                increment();
            }

        });

        Thread chef2 = new Thread(()->
        {

            for(int i=0; i<noOfDishesPerChef;i++ ){

                increment();
            }

        });

        chef1.start();
        chef2.start();

        try {
            chef1.join();
            chef2.join();
        }
        catch(InterruptedException e){

        }
        System.out.println("Total no of dishes done today: " + totalNoOfDishes);
    }
}
