package com.aaron.basic.java.thread.cas;

import java.util.concurrent.atomic.AtomicLong;

public class OptimisticLockCounter {

    private AtomicLong count = new AtomicLong();

    public void inc(){

        boolean successful = false;

        while (!successful){

            long currentValue = this.count.get();
            long newValue = currentValue +1;

            successful = count.compareAndSet(currentValue, newValue);

            System.out.println(Thread.currentThread().getName() +" has tried and result is "+ successful);

        }


    }

    public static void main(String args[]){

        OptimisticLockCounter optimisticLockCounter = new OptimisticLockCounter();

        Thread th1 = new Thread(()->{
            optimisticLockCounter.inc();

        },"Thread1");

        Thread th2 = new Thread(()->{
            optimisticLockCounter.inc();
        },"Thread2");

        th1.start();
        th2.start();
    }

}
