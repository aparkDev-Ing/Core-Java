package core.java.multithread.chapter6.semaphore;

import java.util.concurrent.Semaphore;

public class JavaSemaphoreAPI {

    static final int noOfThreads = 5;

    static final int permit = 3;



    public static void main(String args[]) throws Exception{

        int counter =0;

        Semaphore semaphore =new Semaphore(permit);
        RunnableIm runnable = new RunnableIm(semaphore);

        Thread[] threadList= new Thread[noOfThreads];

        for(int i =0; i < noOfThreads; i++){
            threadList[i] = new Thread(runnable,"Thread"+ (i+1));
            //threadList[i].start();
        }

        for(int i =0; i < noOfThreads; i++){
            threadList[i].start();
            //threadList[i].join(); //make main thread join all user threads.
        }


            threadList[0].join();
            threadList[1].join();
            threadList[2].join();
            threadList[3].join();
            threadList[4].join();

        System.out.println("\n Final result of counter: "+ runnable.counter);


    }
}

class RunnableIm implements Runnable{

    Semaphore semaphore;

    public static int counter = 0;


    public RunnableIm(Semaphore semaphore){

        this.semaphore = semaphore;
    }


    @Override
    public void run(){

        try {
            semaphore.acquire();

                System.out.println("Thread:: " + Thread.currentThread().getName() + " has acquired a lock on semaphore!");
                for (int i = 1; i <= 10000; i++) {
                    counter++;
                    //System.out.println("Thread "+  Thread.currentThread().getName()+ " Count: "+ this.counter);


            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            semaphore.release();
            System.out.println("Thread:: "+ Thread.currentThread().getName() +" has released a lock on semaphore!");
            //System.out.println("Final result of counter: "+this.counter);
        }





    }

}