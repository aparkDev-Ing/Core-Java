package core.java.multithread.chapter11.threadpoolexecutorhook;

import java.sql.Time;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorHookExample {

    static  AtomicInteger count =new AtomicInteger(0);


    public static void main(String args[])throws Exception{


        int coreSize= 2;
        int maxCoreSize =4;
        long liveTime = 0l;
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>(10);

        ThreadPoolExecutor threadPoolExecutorHook = new ThreadPoolExecutorHook(coreSize,maxCoreSize,liveTime,TimeUnit.SECONDS,blockingQueue);

        for(int i =0; i<3; i++) {

            //execute same
            threadPoolExecutorHook.submit(() -> {
                try {
                    Thread.sleep(500);
                    System.out.println(Thread.currentThread().getName() + " | count: " +count.incrementAndGet());

//                    String test = null;
//                    System.out.println(test.length());
                } catch (Exception e) {
                    System.out.println("Exception occured: "+ e);
                }

            });
        }


//        if(!threadPoolExecutorHook.awaitTermination(1, TimeUnit.SECONDS)){
//            threadPoolExecutorHook.shutdownNow();
//        }
//        System.out.println("TotalCount: "+count.get());
        threadPoolExecutorHook.shutdown();


    }


}

class ThreadPoolExecutorHook extends ThreadPoolExecutor{

    public ThreadPoolExecutorHook(int corePoolSize,
                                  int maximumPoolSize,
                                  long keepAliveTime,
                                  TimeUnit unit,
                                  BlockingQueue<Runnable> workQueue
                                  ) {
            super(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue);
    }

    @Override
    public void beforeExecute(Thread t, Runnable r){

        //System.out.println("Calculation starts by thread: "+t.getName());
        //super.beforeExecute(t,r);
    }

    @Override
    public void afterExecute(Runnable r, Throwable t)
    {
        if(t!=null){
            System.out.println("Calculation failed by thread: "+Thread.currentThread().getName() +" caused by: "+ t.getCause() +" | error message: "+t.getMessage());
        }else{
            //System.out.println("Calculation done by Thread: "+Thread.currentThread().getName());
            //we can even get runnable task afterwards and execute onemore time like this

            //System.out.println("Starting calculation one more time by Thread: "+Thread.currentThread().getName());
            //r.run();
        }
        //System.out.println("Calculation done by Thread: "+Thread.currentThread().getName());
        //System.out.println("Printing stack trace: ");
       // t.printStackTrace();
    }

    @Override
    public void terminated()
    {
        System.out.println("TotalCount: "+ThreadPoolExecutorHookExample.count.get());
    }

}