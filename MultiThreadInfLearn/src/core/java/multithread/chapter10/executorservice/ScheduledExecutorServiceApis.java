package core.java.multithread.chapter10.executorservice;

import java.util.concurrent.*;

public class ScheduledExecutorServiceApis {

    public static void main(String args[]) throws InterruptedException, ExecutionException,CancellationException {


        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);

        //example 1
//        System.out.println("여행을 시작합니다! 공항에갑니다");
//        scheduledExecutorService.schedule(() -> {
//
//
//            System.out.println("비행기가 출발합니다");
//
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//
//            }
//            System.out.println("여행지에 도착합니다!");
//
//        }, 2, TimeUnit.SECONDS);

        //example 2
//        System.out.println("여행을 시작합니다! 공항에갑니다");
//        ScheduledFuture<String> result = scheduledExecutorService.schedule(() -> {
//
//
//            System.out.println("비행기가 출발합니다");
//
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//
//            }
//            //System.out.println("여행지에 도착합니다!");
//
//            return "괌";
//        }, 2, TimeUnit.SECONDS);
//
//
//        try {
//            String ansewr = result.get();
//            System.out.println("여행지 "+ ansewr +" 에도착햇습니다!");
//        }
//        catch(InterruptedException | ExecutionException| CancellationException e){
//            System.out.println("Error occured "+e);
//        }



        //example3

        System.out.println("놀이공원 운영을 시작합니다!");
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(()->{
            System.out.println("놀이기구 운행을 시작합니다");
            try {
                Thread.sleep(2000);
            }
            catch(InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println("Error occured: "+ e);
            }
            System.out.println("놀이기구 운행이 끝이났습니다. 승객들이 하차합니다");
        },1,2,TimeUnit.SECONDS);


//        scheduledFuture.get();
//        System.out.println("놀이공원 운영을 중단합니다!");


      scheduledExecutorService.awaitTermination(6,TimeUnit.SECONDS);
      scheduledFuture.cancel(false);
      System.out.println("놀이공원 운영을 중단합니다!");
      scheduledExecutorService.shutdown();


//        System.out.println("놀이공원 운영을 시작합니다!");
//        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(()->{
//            System.out.println("놀이기구 운행을 시작합니다: "+ Thread.currentThread().getName());
//
//            try {
//                Thread.sleep(2000);
//            }
//            catch(InterruptedException e){
//                Thread.currentThread().interrupt();
//                System.out.println("Error occured: "+ e);
//            }
//            System.out.println("놀이기구 운행이 끝이났습니다. 승객들이 하차합니다");
//        },1,1000,TimeUnit.MILLISECONDS);
//
//
////        scheduledFuture.get();
////        System.out.println("놀이공원 운영을 중단합니다!");
//
//
//        scheduledExecutorService.awaitTermination(10,TimeUnit.SECONDS);
//        scheduledFuture.cancel(true);
//        System.out.println("놀이공원 운영을 중단합니다!");
//
//        scheduledExecutorService.shutdown();



    }



    }


