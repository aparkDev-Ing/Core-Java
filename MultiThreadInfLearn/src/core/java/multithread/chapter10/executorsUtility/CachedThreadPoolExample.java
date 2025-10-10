package core.java.multithread.chapter10.executorsUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CachedThreadPoolExample {

    static int noOfQuestions = 20;
    static int i =0;

    static AtomicInteger count = new AtomicInteger(0);

    public static void main(String args[]){

        solveQuestions();
    }

    public static void solveQuestions(){

        ThreadLocal<String> name = new ThreadLocal<>();

        ExecutorService executorService = Executors.newCachedThreadPool(r->{

            name.set("Student "+ i);

            i++;
            return new Thread(r,name.get());
        });

//        List<Callable<Integer>> callableList = new ArrayList<>();

//        for(int i=0; i<noOfQuestions;i++){
//
//            callableList.add( ()->{
//                        count.incrementAndGet();
//                        System.out.println(Thread.currentThread().getName()+ "'s count: " +count);
//                        return count.get();
//                    }
//                   );
//        }
//
//        try {
//            List<Future<Integer>> futureList = executorService.invokeAll(callableList);
//
//            for(Future<Integer> future: futureList){
//                int count = future.get();
//                //System.out.println(Thread.currentThread().getName()+ "'s count: " +count);
//            }
//        }
//        catch(Exception e){
//
//        }



        for(int i=0; i<noOfQuestions;i++){

            executorService.submit(()->{
                System.out.println(Thread.currentThread().getName()+ "'s count: " +count.incrementAndGet());
            });

        }


        executorService.shutdown();

        ThreadPoolExecutor pool = (ThreadPoolExecutor) executorService;

        try {
            if (executorService.awaitTermination(2, TimeUnit.SECONDS)) {
                System.out.println("Total count: "+ count);

            }else{
                System.out.println("Thread pool size: "+pool.getPoolSize());
                executorService.shutdownNow();
            }
        }
        catch(Exception e){

        }


    }

}
