package core.java.multithread.chapter10.executorsUtility;

import java.util.concurrent.*;

public class SingleThreadPoolExample {

    public static void main(String args[]){

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.submit(()->{

            System.out.println(Thread.currentThread().getName());
        });

    }
}
