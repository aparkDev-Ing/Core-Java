package core.java.multithread.chapter10.executorservice;

import java.sql.SQLOutput;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SubmitRunnableExample {

    public static void main(String args[])throws ExecutionException, InterruptedException{


        ExecutorService executorService = Executors.newSingleThreadExecutor();

       Future<?> result1 =  executorService.submit(()->{

            System.out.println(Thread.currentThread().getName()+ " Async task starts");

        });

        Future<Integer> result2 =  executorService.submit(()->{

            System.out.println(Thread.currentThread().getName()+ " Async task starts");

        },12);


        executorService.shutdown();


        Object answer1 = result1.get();

        System.out.println(answer1);

        int answer2 = result2.get();

        System.out.println(answer2);

    }
}
