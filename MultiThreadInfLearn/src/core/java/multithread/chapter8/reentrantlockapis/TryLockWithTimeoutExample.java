package core.java.multithread.chapter8.reentrantlockapis;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockWithTimeoutExample {

    //public static ThreadLocal<Boolean> threadLocalIsWinner =new ThreadLocal<>();

    Thread winnerThread = new Thread();
    static boolean isWinner = false;

    public static void main(String args[]){

        new TryLockWithTimeoutExample().performQuiz();
    }

    void guessQuiz(Lock lock){

        //boolean isWinner = threadLocalIsWinner.get();

        while (!isWinner) {

            try {
                System.out.println(Thread.currentThread().getName()+" trying to acquire for competition.");
                if (lock.tryLock(2, TimeUnit.SECONDS)) {
                    try {
                        System.out.println("Participator " + Thread.currentThread().getName() + " has pressed a bell and was able to get a chance to guess on the quiz!");
                        Thread.sleep(3000);
                    }
                    finally {
                        lock.unlock();
                    }
                }
                else {
                    System.out.println("Participator " + Thread.currentThread().getName() + " has missed a chance. Please try faster next time!");
                }

            } catch (InterruptedException exception) {
                System.out.println("Exception occured while taking a quiz " + exception);
            }
        }

    }
    void performQuiz(){

        TryLockWithTimeoutExample quizGuessor=  new TryLockWithTimeoutExample();

        Lock lock = new ReentrantLock();




        Thread[] threads = new Thread[3];



        for(int i =0; i<threads.length;i++){

            threads[i] = new Thread(()-> {

                //threadLocalIsWinner.set(false);
                quizGuessor.guessQuiz(lock);

            },"Quizer "+ i);



            threads[i].start();



        }



        new Thread(()->
        {
            try {
                Thread.sleep(12000);
            }
            catch(InterruptedException e){}
            winnerThread =threads[decideWinner(threads.length)];
            isWinner =true;

        }).start();


        try {
            threads[0].join();
            threads[1].join();
            threads[2].join();
        }
        catch(InterruptedException e){

        }


         System.out.println("Winner of the quiz is: "+ winnerThread.getName() +"! All players derserve a win but please try better next year! ") ;

    }

    int decideWinner(int noOfThreads){

        int index = new Random().nextInt(noOfThreads);

        return index;
    }



}
