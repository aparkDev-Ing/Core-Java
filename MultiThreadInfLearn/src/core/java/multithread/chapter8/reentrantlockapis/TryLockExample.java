package core.java.multithread.chapter8.reentrantlockapis;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockExample {

    public static void  main(String args[]){

        TryLockExample tryLockExample = new TryLockExample();

        tryLockExample.performSoccerGame();
    }

    public void performSoccerGame(){

        Lock lock = new ReentrantLock();

        Ball ball = new Ball("Soccer Ball");

        Thread th1 = new Thread(()->{

                if(lock.tryLock()) {

                    try {
                        System.out.println("Player " + Thread.currentThread().getName() + " has taken a ball!. A winner is " + Thread.currentThread().getName());
                        ball.setAcquired(true);
                    }
                    finally{
                        System.out.println("Unlocking a lock ! " + Thread.currentThread().getName());
                        lock.unlock();
                    }

                }else{
                    System.out.println("Unfortunetly, "+ Thread.currentThread().getName()+" has failed to win this game. Good luck next time!");
                }



        },"Ronaldo");

        Thread th2 = new Thread(()->{

            if(lock.tryLock()) {

                try {
                    System.out.println("Player " + Thread.currentThread().getName() + " has taken a ball!. A winner is " + Thread.currentThread().getName());
                    ball.setAcquired(true);
                }
                finally{
                    System.out.println("Unlocking a lock ! " + Thread.currentThread().getName());
                    lock.unlock();
                }

            }else{
                System.out.println("Unfortunetly, "+ Thread.currentThread().getName()+" has failed to win this game. Good luck next time!");
            }



        },"Messi");


        th1.start();
        th2.start();


    }



}


class Ball{

    boolean isAcquired = false;

    String name ;

    Ball(String name){

        this.name = name;
    }

    public boolean isAcquired() {
        return isAcquired;
    }

    public void setAcquired(boolean acquired) {
        isAcquired = acquired;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}