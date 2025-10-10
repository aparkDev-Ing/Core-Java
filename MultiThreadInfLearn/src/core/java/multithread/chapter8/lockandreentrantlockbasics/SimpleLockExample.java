package core.java.multithread.chapter8.lockandreentrantlockbasics;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleLockExample {

     static int noOfOrders = 1000000;

     static Lock lock = new ReentrantLock();


     public static void cook(){

         noOfOrders--;
     }


     public static void main(String args[]) throws  InterruptedException{

//         try {
//             lock.lock();
//         }
//         catch(InterruptedException e){
//
//         }

         Thread th1 = new Thread(()->

         {

            for(int i = 0; i< 500000; i++) {
                lock.lock();

                try {
                    //System.out.println("Thread "+Thread.currentThread().getName() +" has acquired a lock!");
                    cook();
                } finally {
                    lock.unlock();
                }
            }



         },"Thread1");

         Thread th2 = new Thread(()->

         {
             for(int i = 0; i< 500000; i++) {

                 lock.lock();

                 try {
                     //System.out.println("Thread "+Thread.currentThread().getName() +" has acquired a lock!");
                     cook();
                 }
                 finally{
                     lock.unlock();
                 }

             }

         },"Thread2");

         th1.start();
         th2.start();

         th1.join();
         th2.join();

         System.out.println("No of orders left: "+ noOfOrders);
     }





}
