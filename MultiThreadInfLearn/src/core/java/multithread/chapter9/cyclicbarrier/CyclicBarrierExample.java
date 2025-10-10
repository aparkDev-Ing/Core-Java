package core.java.multithread.chapter9.cyclicbarrier;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CyclicBarrierExample {

    static int noOfThreads = 4;

    static CyclicBarrier cyclicBarrier = new CyclicBarrier(noOfThreads,()->{


        System.out.println("All travelrs have arrived! Let's start for this trip!!");

    });

    static void arriveOnTime() {

        System.out.println("Traveler " + Thread.currentThread().getName() + " has arrived on time! Let's wait for others for few min before we start for trip.");
        try {
            cyclicBarrier.await(2, TimeUnit.SECONDS);



//            System.out.println("Now time for travelers to go to first trip place");
//            cyclicBarrier.await();

//            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
            System.out.println(Thread.currentThread().getName()+ " Exception has occured while waiting for other travelers: " + e);
        }
//        } catch (InterruptedException | BrokenBarrierException e) {
//            System.out.println("Exception has occured while waiting for other travelers: " + e);
//        }
    }

    static void arriveLate() {

        System.out.println("Traveler " + Thread.currentThread().getName() + " is late! Let's see if he can make it");
        try {

            Thread.sleep(1000);
//            System.out.println("Traveler " + Thread.currentThread().getName() + " was able to make it!");
//            cyclicBarrier.await(2, TimeUnit.SECONDS);

            cyclicBarrier.await();

//            System.out.println("Now time for travelers to go to first trip place");
//            cyclicBarrier.await();

//        } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
//            System.out.println("Exception has occured while waiting for other travelers: "+Thread.currentThread().getName()+ " " + e);
//        }
        } catch (InterruptedException | BrokenBarrierException e) {
            System.out.println("Exception has occured while waiting for other travelers: "+Thread.currentThread().getName()+ " " + e);
        }
    }
    public static void main(String args[]){

        new CyclicBarrierExample().startTrip();
    }

    void startTrip(){

//        Traveler aaron = new Traveler("Aaron");
//
//        Traveler david = new Traveler("David");
//
//        Traveler kevin = new Traveler("Kevin");

        Thread[] traverlers = new Thread[noOfThreads];

        for(int i=0; i< noOfThreads-1; i++) {
            traverlers[i] = new Thread(CyclicBarrierExample::arriveOnTime,"Traveler "+(i+1));

            traverlers[i].start();
        }

        Thread lateTraveler = new Thread(CyclicBarrierExample::arriveLate,"LateTraveler");

        lateTraveler.start();



        //lateTraveler.interrupt();


    }



}


//class Traveler implements Runnable{
//
//
//    CyclicBarrier cyclicBarrier;
//
//    String name;
//
//    public void setCyclicBarrier(CyclicBarrier cyclicBarrier){
//
//        this.cyclicBarrier=cyclicBarrier;
//    }
//
//    String getName(){
//
//        return this.name;
//    }
//
//    public Traveler(String name){
//        this.name = name;
//    }
//
//    public Traveler(String name, CyclicBarrier cyclicBarrier){
//        this.name = name;
//        this.cyclicBarrier=cyclicBarrier;
//    }
//
//    @Override
//    public void run(){
//
//        arriveOnTime();
//
//    }
//
//    void arriveOnTime() {
//
//        System.out.println("Traveler " + this.getName() + " has arrived on time! Let's wait for others for few min before we start for trip.");
//        try {
//            cyclicBarrier.await(2, TimeUnit.SECONDS);
//        } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
//            System.out.println("Exception has occured while waiting for other travelers: " + e);
//        }
//    }
//}