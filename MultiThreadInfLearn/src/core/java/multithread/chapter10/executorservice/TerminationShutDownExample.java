package core.java.multithread.chapter10.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TerminationShutDownExample {

    public static void main(String args[]){



            placeOrder();



    }


    public static void placeOrder() {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        System.out.println("앱으로 음식주문을 합니다! 음식을 주문하고 할일을합니다!");




        Future<String> result = executorService.submit(()->{

            System.out.println("식당이 주문을받고 준비중입니다!");

            try {
                Thread.sleep(2000);
            }
            catch(InterruptedException e){
                System.out.println("알바들이 철수합니다!");
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }

            System.out.println("음식준비가 완료됫습니다!");

            return "CheeseBurger";
        });

        executorService.shutdown();

        System.out.println("식당주인이 알바들에게 마감을 부탁을하고 기다립니다 ");
        try {
            if (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
                System.out.println("알바들이 음식준비를 시간안에 처리하지못합니다");
                executorService.shutdownNow();
                while(!executorService.isTerminated()){
                 //  System.out.println("식당주인이 강제로 마감하려합니다!");
                }
                System.out.println("식당주인이 강제로 마감후 잠에듭니다");

            } else {
                System.out.println("시간안에 마감완료");
            }
        }
        catch(Exception e){

        }




            try {
                String food = result.get();
                System.out.println("손님:: 음식을 받앗습니다 " + food);

            } catch (Exception e) {
                System.out.println("식당의 마감으로 음식받는데 실패합니다");
            }

//        while(!executorService.isTerminated()){
//            System.out.println("식당주인이 강제로 마감하려합니다!");
//        }


        // RejectedExecutionException
//        Future<String> result2 = executorService.submit(()->{
//
//            System.out.println("식당이 주문을받고 준비중입니다!");
//
//            Thread.sleep(2000);
//
//            System.out.println("음식준비가 완료됫습니다!");
//
//            return "CheeseBurger";
//        });
//
//        try {
//            String food = result2.get();
//            System.out.println("음식을 받앗습니다 " + food);
//        } catch (Exception e) {
//
//        }




    }



}
