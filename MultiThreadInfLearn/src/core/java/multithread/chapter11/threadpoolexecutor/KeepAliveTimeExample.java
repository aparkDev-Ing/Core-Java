package core.java.multithread.chapter11.threadpoolexecutor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class KeepAliveTimeExample {

    static AtomicInteger count = new AtomicInteger(0);

    public static void main(String args[]){

        placeOrder();
    }

    public static void placeOrder(){

        //ThreadLocal<String> name = new ThreadLocal<>();

        int noOfOrders = 10;

        int coreSize= 2;

        int maxCoreSize = 5;

        long aliveTime = 1l;

        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(5);

//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(coreSize,maxCoreSize, aliveTime, TimeUnit.SECONDS,queue, r->{
//
//            String workerName = "Worker: " ;
//            if (workerId < coreSize) {
//               workerName = workerName+ "-core";
//            } else {
//                workerName = workerName+  "-noncore";
//            }
//
//            workerId++;
//            return new Thread(r,workerName);
//        });

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                coreSize, maxCoreSize, aliveTime, TimeUnit.SECONDS, queue,
                r -> {
                    String workerName = "Worker: ";
                    if (count.get() < coreSize) {
                        workerName += "-core";
                    } else {
                        workerName += "-noncore";
                    }
                    count.getAndIncrement();
                    return new Thread(r, workerName);
                });

        threadPoolExecutor.allowCoreThreadTimeOut(true);

        //threadPoolExecutor.prestartAllCoreThreads();

        System.out.println("Customer entered restaurant and wants to place an order");

        for(int i =0; i<noOfOrders;i++){

            threadPoolExecutor.execute(()->{
                try {
                    Thread.sleep(2000);
                }
                catch(InterruptedException e){
                    System.out.println("Error occured while preparing a food "+e);
                }
                System.out.println(Thread.currentThread().getName()+" has completed preparing an order.");

            });
        }

        try {

            Thread.sleep(8000);
        }
        catch(Exception e){

        }

        threadPoolExecutor.shutdown();




    }

}

//package core.java.multithread.chapter11.threadpoolexecutor;
//
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.LinkedBlockingQueue;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
//public class KeepAliveTimeExample {
//    public static void main(String[] args) throws InterruptedException {
//
//        int corePoolSize = 2;
//        int maxPoolSize = 4;
//        long keepAliveTime = 2L;
//        BlockingQueue<Runnable> workQueue =  new LinkedBlockingQueue<>(2);
//        int taskNum = 6;
//
//
//        ThreadPoolExecutor executor =
//                new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);
//
//        executor.allowCoreThreadTimeOut(true);
//
//        for (int i = 0; i < taskNum; i++) {
//            final int taskId = i;
//            executor.execute(()->{
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                System.out.println(Thread.currentThread().getName() + " 가 태스크" + taskId + " 를 실행하고 있습니다.");
//            });
//        }
//
//
//        //Thread.sleep(6000);
//        executor.shutdown();
//    }
//}