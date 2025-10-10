package core.java.multithread.chapter10.executorsUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

class Menu{

    static List<String> menuList = Arrays.asList("CheeseBurger","Pasta","Taco","Pizza");

}

public class FixThreadPoolExample {

    public static void main(String args[]) throws InterruptedException{

        placeOrder();
    }

    static void placeOrder()throws InterruptedException{
//        ExecutorService executorService = Executors.newFixedThreadPool(3, new ThreadFactory(){

//
//            public Thread newThread(Runnable r){
//            @Override
//
//                return new Thread();
//            };
//
//        });

        CustomFactory customFactory = new CustomFactory();

//        ExecutorService executorService = Executors.newFixedThreadPool(3, r->{
//
//            return new Thread(r);
//        });

        ExecutorService executorService = Executors.newFixedThreadPool(3,customFactory);


        System.out.println("손님이 식당에 들어왓습니다");

        List<Future<String>> futureList = new ArrayList<>();

        for(int i =0 ; i<3; i++) {
            Future<String> future = executorService.submit(() -> {
                //long currTime = System.currentTimeMillis();
                System.out.println(
                        Thread.currentThread().getName() + " 주문받은 음식을 준비중입니다"
                );

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Error occured while placing an order " + e);
                }

                System.out.println("음식준비가 완료되엇습니다!");

                int index = new Random().nextInt(4);
                return Menu.menuList.get(index);
                //System.out.println("Time it took to complete process: "+ (System.currentTimeMillis()-currTime ));
            });
            futureList.add(future);
        }

        for(Future<String> future: futureList){
            try {
                System.out.println("주문한음식: " + future.get() + " 을 받앗습니다");
            }
            catch(Exception e){

            }
        }

        executorService.shutdown();

        if(!executorService.awaitTermination(5, TimeUnit.SECONDS)){
            System.out.println("시간이지났습니다 오늘운영은여기까지입니다");
            executorService.shutdownNow();
            while(!executorService.isTerminated()){
                System.out.println("마감하고잇습니다!");
            }

        }else{
            System.out.println("식당운영을 종료합니다");
        }


    }

}

class CustomFactory implements ThreadFactory{

    int id = 0;

    final String name = "Chef";

    @Override
    public Thread newThread(Runnable r){

        String threadName = name+" "+id;
        id++;
        return new Thread(r, threadName);

    }

}
