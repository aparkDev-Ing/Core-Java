package core.java.multithread.chapter8.readwritelock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockSimpleExample {

    static boolean isActive = true;

    public void read(ReadWriteLock readWriteLock, SharedResource sharedResource){

        readWriteLock.readLock().lock();
        try {

            System.out.println(Thread.currentThread().getName() + "'s Current Stock Price is ->" + sharedResource.stockPrice);
            try {
                Thread.sleep(500);

            } catch (InterruptedException e) {

            }

        } finally {
            readWriteLock.readLock().unlock();
            //System.out.println("Please check back in  ->" + sharedResource.stockPrice);
        }
    }


    public static void main(String args[]){

        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        SharedResource sharedResource = new SharedResource();

        ReadWriteLockSimpleExample readWriteLockSimpleExample = new ReadWriteLockSimpleExample();


        Thread reader1 = new Thread(()->{


            while(isActive) {
                readWriteLockSimpleExample.read(readWriteLock, sharedResource);
            }


        },"Robinhood");

        Thread reader2 = new Thread(()->{


            while(isActive) {
                readWriteLockSimpleExample.read(readWriteLock, sharedResource);
            }


        },"Coinbase");

        Thread writer = new Thread(()->{


                readWriteLock.writeLock().lock();

                try {

                    sharedResource.stockPrice = new Random().nextInt(51);

                    System.out.println(Thread.currentThread().getName() + " setting up price ->" + sharedResource.stockPrice);
                    try {
                        Thread.sleep(2000);

                    } catch (InterruptedException e) {

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                } finally {
                    readWriteLock.writeLock().unlock();
                    //System.out.println("Please check back in  ->" + sharedResource.stockPrice);
                }



        },"Investors");


        reader1.start();
        reader2.start();
        writer.start();

        try {
            Thread.sleep(10000);
            isActive = false;
        }
        catch(InterruptedException e){

        }


    }


}

class SharedResource{


    int stockPrice;


}