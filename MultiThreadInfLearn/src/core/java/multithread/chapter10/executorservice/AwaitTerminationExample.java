package core.java.multithread.chapter10.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class AwaitTerminationExample {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                return thread;
            }
        });

//        for(int i=0; i<100;i++) {
            executorService.submit(() -> {
            while(true){
                System.out.println(Thread.currentThread().getName() + " : 데몬 스레드 실행 중...");
                Thread.sleep(1000);


//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//
                }
            });
//        }


        //executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        executorService.awaitTermination(5, TimeUnit.SECONDS);
        //Thread.sleep(20000);
        System.out.println("메인 스레드 종료");

    }
}
