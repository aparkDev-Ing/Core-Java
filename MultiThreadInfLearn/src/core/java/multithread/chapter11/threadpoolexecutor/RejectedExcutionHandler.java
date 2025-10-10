package core.java.multithread.chapter11.threadpoolexecutor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;



public class RejectedExcutionHandler {

    static int noOfCustomers = 9;
    //static AtomicInteger customerId = new AtomicInteger(0);
    public static void rideShuttleBus(){

        int corePoolSize = 2;
        int maxCoreSize  = 4;
        int queueSize = 4;
        long aliveTime = 0l;
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>(queueSize);

        //abort policy example
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxCoreSize, aliveTime, TimeUnit.SECONDS,blockingQueue, r->{
//            //String name = "Thread " + customerId.getAndIncrement();
//            return new Thread(r,"name");
//        }, new ThreadPoolExecutor.AbortPolicy());

        //discard policy example
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxCoreSize, aliveTime, TimeUnit.SECONDS,blockingQueue, r->{
//            //String name = "Thread " + customerId.getAndIncrement();
//            return new Thread(r,"name");
//        }, new ThreadPoolExecutor.DiscardPolicy());

        //discard oldest policy example
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxCoreSize, aliveTime, TimeUnit.SECONDS,blockingQueue, r->{
//            //String name = "Thread " + customerId.getAndIncrement();
//            return new Thread(r,"name");
//        }, new ThreadPoolExecutor.DiscardOldestPolicy());


        //Caller Runs Policy
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxCoreSize, aliveTime, TimeUnit.SECONDS,blockingQueue, r->{
//            //String name = "Thread " + customerId.getAndIncrement();
//            return new Thread(r,"name");
//        }, new ThreadPoolExecutor.CallerRunsPolicy());

          //Custom Policy by implementing
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxCoreSize, aliveTime, TimeUnit.SECONDS,blockingQueue, r->{
//            //String name = "Thread " + customerId.getAndIncrement();
//            return new Thread(r,"name");
//        }, new CustomRejectedExecutionHandler());

        //Custom Policy by implementing with lambda
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxCoreSize, aliveTime,TimeUnit.SECONDS,blockingQueue, r->{
            //String name = "Thread " + customerId.getAndIncrement();
            return new Thread(r,"name");
        }, (r, executor)->{
            System.out.println("One customer could not land on airplane! We are sending her on next flight | "+ Thread.currentThread().getName());

            try {
                Thread.sleep(2000);
            }
            catch(InterruptedException e){

            }
            executor.execute(r);

        });


        for(int i =0;i<noOfCustomers; i++ ){
            int count = i;
            threadPoolExecutor.submit(()->{

                String customer = "Customer "+ count;
                try {
                    Thread.sleep(500);
                }
                catch(InterruptedException e){

                }
                System.out.println(customer +" was able to get on airplane! | " +Thread.currentThread().getName());
            });
        }

        threadPoolExecutor.shutdown();


    }

    public static void main(String args[]){

        rideShuttleBus();
    }



}


class CustomRejectedExecutionHandler implements RejectedExecutionHandler{

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor){

        System.out.println("One customer could not land on airplane! We are sending her on next flight | "+ Thread.currentThread().getName());

        try {
        Thread.sleep(2000);
    }
        catch(InterruptedException e){

    }
        executor.execute(r);

    }

}