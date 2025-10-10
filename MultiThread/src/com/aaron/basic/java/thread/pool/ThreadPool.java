package com.aaron.basic.java.thread.pool;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class ThreadPool {

    private BlockingQueue<Runnable> taskQueue = null;
    private List<PoolThreadRunnable> runnables = new ArrayList<>();
    private boolean isStopped = false;


    public ThreadPool(int noOfThreads, int maxNoOfTasks){

        taskQueue = new ArrayBlockingQueue<Runnable>(maxNoOfTasks);

        for(int i = 0; i< noOfThreads; i++){

            PoolThreadRunnable poolThreadRunnable = new PoolThreadRunnable(taskQueue);
            runnables.add(poolThreadRunnable);

        }

        for(PoolThreadRunnable poolThreadRunnable: runnables){
            new Thread(poolThreadRunnable).start();
        }

    }

    public synchronized void execute(Runnable task) throws Exception{

        if(this.isStopped)
            throw new IllegalStateException("ThreadPool is stopped already");

        this.taskQueue.offer(task);

    }

    public synchronized void stop(){

        this.isStopped = true;

        for(PoolThreadRunnable poolThreadRunnable: runnables){
            poolThreadRunnable.doStop();
        }

    }

    public synchronized void waitUntilAllTasksFinished(){

        while(this.taskQueue.size()>0){
            try{
                System.out.println("Not all tasks completed yet!. Thread " + Thread.currentThread().getName() +" is going sleep.");
                Thread.sleep(1);

            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }

}
