package core.java.multithread.chapter12.completablefuture;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


public class FutureRelayExample {

    static List<Runner> runnerList = new ArrayList<>();

    static int noOfRunners = 5;

    //static AtomicInteger runnerId = new AtomicInteger(0);

    class Runner{

        AtomicInteger runnerId = new AtomicInteger(0) ;

        void startRun(Future<Boolean> future){

            try{
                System.out.println(Thread.currentThread().getName()+" has started task.");
            if(future.get()){
                Long currentTime = System.currentTimeMillis();
                System.out.println("Runner ID: "+ this.runnerId+ " | "+ this.name +" "+ " is starting a initial run! | " +Thread.currentThread().getName());
                Thread.sleep(500);

                System.out.println("Runner ID: "+ this.runnerId+ " | "+ this.name +" "+ " has completed running! | "+Thread.currentThread().getName() );
                Long endTime = System.currentTimeMillis();

                this.record = endTime-currentTime;
                this.runnerId.getAndIncrement();

            }
            }catch(Exception e){
                System.out.println("Exception occured: "+e);
            }

        }

        void startInitialRun(){

            System.out.println(Thread.currentThread().getName()+" has started task.");
            try{
                    Long currentTime = System.currentTimeMillis();
                    System.out.println("Runner ID: "+ this.runnerId+ " | "+ this.name +" "+ " is starting a initial run! | " +Thread.currentThread().getName());
                    Thread.sleep(500);

                    System.out.println("Runner ID: "+ this.runnerId+ " | "+ this.name +" "+ " has completed running! | "+Thread.currentThread().getName() );
                    Long endTime = System.currentTimeMillis();

                    this.record = endTime-currentTime;

                    this.runnerId.getAndIncrement();

                //System.out.println(Thread.currentThread().getName()+" has started task.");
            }catch(Exception e){
                System.out.println("Exception occured: "+e);
            }

        }


        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getRecord() {
            return record;
        }

        public void setRecord(long record) {
            this.record = record;
        }

        long record;

        public Runner(String name){
            this.name = name;

            //this.runnerId = FutureRelayExample.runnerId.getAndIncrement();
            FutureRelayExample.runnerList.add(this);

        }



    }


    static void launchRelayApplication(){

        ExecutorService executorService = Executors.newFixedThreadPool(noOfRunners);

            Runner runner = new FutureRelayExample().new Runner("Runner");

        Future<Boolean> result1 = executorService.submit(()->{
            runner.startInitialRun();

            return true;
        });

         Future<Boolean> result2 = executorService.submit(()->{
                runner.startRun(result1);

                return true;
            });

        Future<Boolean> result3 = executorService.submit(()->{
            runner.startRun(result2);

            return true;
        });

        Future<Boolean> result4 = executorService.submit(()->{
            runner.startRun(result3);

            return true;
        });


        Future<Boolean> result5 = executorService.submit(()->{
            runner.startRun(result4);

            return true;
        });


        try {
            System.out.println(Thread.currentThread().getName()+" waiting for final result!");
            long startTime = System.currentTimeMillis();
            if(result5.get()){
                Map<String,Long> recordMap = FutureRelayExample.runnerList.stream().collect(Collectors.toMap(k->k.getName(), v->v.getRecord()));
                System.out.println("Running is completed by all 5 runners! Congrats. Total Result: " + recordMap);

                long endTime = System.currentTimeMillis();
                System.out.println("Total time it took: "+ (endTime- startTime));
            }


        }
        catch(Exception e){

        }

        executorService.shutdown();


    }
    public static void main(String args[]){
        launchRelayApplication();
    }

}
