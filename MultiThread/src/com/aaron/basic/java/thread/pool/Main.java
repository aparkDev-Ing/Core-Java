package com.aaron.basic.java.thread.pool;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void executeThreadPool() throws Exception{

        int maxNoOfTasks = 10;

        ThreadPool threadPool = new ThreadPool(3, maxNoOfTasks);

        for(int i =0; i<maxNoOfTasks; i++){

            int taskNo = i;
            threadPool.execute(  ()->{

                System.out.println(Thread.currentThread().getName()+ ": Task " + taskNo);

            }  );

        }
        threadPool.waitUntilAllTasksFinished();
        threadPool.stop();

    }


    public static void main(String args[]) throws Exception{

        executeThreadPool();


    }
}
