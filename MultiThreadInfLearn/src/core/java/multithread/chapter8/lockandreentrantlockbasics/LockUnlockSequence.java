package core.java.multithread.chapter8.lockandreentrantlockbasics;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockUnlockSequence {

    int count = 0;

    Lock lock1 = new ReentrantLock();

    Lock lock2 = new ReentrantLock();
    Object object1 = new Object();

    Object object2 = new Object();



    public void increment(){

        synchronized (object1){

            synchronized (object2) {
                count++;
        }
               }

    }

    public void incrementWithLock(){

        lock1.lock();
        try{
            System.out.println("Lock 1 acquired by main thread!");
            count++;

            lock2.lock();
            System.out.println("Lock 2 acquired by main thread!");
            try{

                count++;
            }
            finally{
                lock1.unlock();
                System.out.println("Lock 1 released by main thread!");
            }
        }
        finally{
            lock2.unlock();
            System.out.println("Lock 2 released by main thread!");
        }



    }



    public static void main(String args[]){

        LockUnlockSequence lockUnlockSequence =new LockUnlockSequence();

        lockUnlockSequence.incrementWithLock();

    }
}
