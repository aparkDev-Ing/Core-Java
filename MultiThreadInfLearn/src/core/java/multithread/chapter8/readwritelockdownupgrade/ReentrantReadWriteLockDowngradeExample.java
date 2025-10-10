package core.java.multithread.chapter8.readwritelockdownupgrade;


import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockDowngradeExample {

    static int noOfThreads = 10;

    static int noOfScript = 0;


    public static void main(String args[]){

        ReentrantReadWriteLockDowngradeExample reentrantReadWriteLockDowngradeExample = new ReentrantReadWriteLockDowngradeExample();

        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();

        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();



           Thread writer = new Thread(()->{
               writeLock.lock();
               System.out.println("Writer has acquired a lock");
               try {
                   reentrantReadWriteLockDowngradeExample.writeScript();
                   noOfScript = noOfThreads;

                   readLock.lock();
                   System.out.println("Writer downgraded to read lock");

                   writeLock.unlock();
                   System.out.println("Writer has released a write lock");

                   try {
                       Thread.sleep(3000);
                   } catch (InterruptedException e) {
                       throw new RuntimeException(e);
                   }

               }
               finally{
                  // writeLock.unlock();

                   readLock.unlock();
                   System.out.println("\n Writer has released a read lock. Downgrade Completed!");
               }
           });

           writer.start();



        Thread[] presenterThreads = new Thread[noOfThreads];

        for(int i =0; i<noOfThreads; i++){
            presenterThreads[i] = new Thread(()->{

                readLock.lock();
                System.out.println("Thread "+ Thread.currentThread().getName()+" has acquired a read lock!");
                try{
                    reentrantReadWriteLockDowngradeExample.readScript();
                }
                finally{

                    readLock.unlock();
                    //System.out.println("Thread "+ Thread.currentThread().getName()+" has released a lock!");
                }

            });

            presenterThreads[i].start();

        }




    }

    void writeScript(){

        System.out.println("Writing a script!");
    }
    void readScript(){

        System.out.println("Reading a script!");
    }

}
