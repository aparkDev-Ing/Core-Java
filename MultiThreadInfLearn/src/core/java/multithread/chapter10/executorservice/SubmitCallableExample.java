package core.java.multithread.chapter10.executorservice;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SubmitCallableExample {

    public static void main(String args[])throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<String> result = executorService.submit(()->{

            System.out.println("Async task starts");
            return "Hello";
        });


        String answer = result.get();
        System.out.println("Result: "+ answer);

        executorService.shutdown();

    }
}
