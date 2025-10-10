package core.java.multithread.chapter10.callableandfuture;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnableExample {

    static int count = 0;

    void calculate(){

        for(int i =0; i<10000000;i++) {
            count++;
        }
    }
    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        RunnableExample runnableExample = new RunnableExample();

        Runnable runnableTask = () ->{

            runnableExample.calculate();
//            System.out.println("Runnable 작업 수행 중..");
//            System.out.println("Runnable 작업 완료");

            System.out.println("Completed: "+ count);
        };

        executorService.execute(runnableTask);

        executorService.shutdown();
    }
}