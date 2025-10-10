package core.java.multithread.chapter12.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class CompletedFutureRelayExample {

    static AtomicInteger runnerId =new AtomicInteger(0);

    static void startRun() {
        try {
            System.out.println(Thread.currentThread().getName() + " has started task.");
            Long currentTime = System.currentTimeMillis();
            System.out.println("Runner ID: " + runnerId + " | is starting a initial run! | " + Thread.currentThread().getName());
            Thread.sleep(500);

            System.out.println("Runner ID: " + runnerId + " |   has completed running! | " + Thread.currentThread().getName());
            Long endTime = System.currentTimeMillis();

            runnerId.getAndIncrement();


        } catch (Exception e) {
            System.out.println("Exception occured: " + e);
        }
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        boolean finalResult = CompletableFuture.supplyAsync(() -> {
            startRun();
           return true;
        }).thenApplyAsync(result1 -> {
            startRun();
            return true;
        }).thenApplyAsync(result2 -> {
            startRun();
            // 비동기 서비스 3의 작업 수행 (service2 결과 사용)
            return true;
        }).thenApplyAsync(result3 -> {
            startRun();
            // 비동기 서비스 4의 작업 수행 (service3 결과 사용)
            return true;
        }).thenApplyAsync(result4 -> {
            startRun();
            // 비동기 서비스 5의 작업 수행 (service4 결과 사용)
            return true;
        }).get();

        // 최종 결과를 얻기 위해 service5의 완료를 기다림
        System.out.println("최종 결과: " + finalResult);
    }
}
