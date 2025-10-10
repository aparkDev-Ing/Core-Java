package core.java.multithread.chapter12.completablefutureapis;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Supplier;

public class ApplyThenExample {

    static String prepareFood(String food){

        System.out.println(Thread.currentThread().getName()+ " | Chef has started a cooking! ");
        try {
           Thread.sleep(500);
        }
        catch(Exception e){
            System.out.println("Error occured: "+e);
        }
        System.out.println(Thread.currentThread().getName()+ " | Chef has finished cooking! ");

        return food;
    }

    static String deliverFood(String food){

        System.out.println(Thread.currentThread().getName()+ " | Driver has been assigned to this order: "+food);
        try{
            Thread.sleep(500);
        }catch(Exception e){
            System.out.println("Error occured: "+e);
        }

        System.out.println(Thread.currentThread().getName()+ " | Driver has picked up an order and delivered the food to infront of house!: " + food);

        return food;
    }

    static void orderFood(String food){
        System.out.println("음식을주문합니다! "+ food);
    }

    static void launchApplication(){

        long currentTime = System.currentTimeMillis();

        System.out.println(Thread.currentThread().getName()+ " | User has launched food order app!");

        String food = "Pizza";
        orderFood(food);
//
//        CompletableFuture<String> cf = CompletableFuture.supplyAsync(
//                ()->  prepareFood(food)
//        ).thenApply(
//                result->{
//            deliverFood(result);
//            return result;
//        }
//
//        );

//        CompletableFuture<String> cf = CompletableFuture.supplyAsync(
//                ()->  prepareFood(food)
//        ).thenApplyAsync(
//                result->{
//                    deliverFood(result);
//                    return result;
//                }
//
//        );

//        ExecutorService executorService = Executors.newFixedThreadPool(3);

//        String foodResult = CompletableFuture.supplyAsync(
//                ()->  prepareFood(food),executorService
//        ).thenApplyAsync(
//                result->{
//                    deliverFood(result);
//                    return result;
//                }
//
//        ,executorService).join();
//
//        executorService.shutdown();



        String foodResult = CompletableFuture.supplyAsync(
                ()->  prepareFood(food)
        ).thenApplyAsync(
                result->{
                    deliverFood(result);
                    return result;
                }

        ).join();




        try{
//            String result = cf.get();
            //System.out.println(Thread.currentThread().getName()+ "| User receivd an order created through an app! "+ result);
            System.out.println(Thread.currentThread().getName()+ "| User receivd an order created through an app! "+ foodResult);

            System.out.println("Time it took to complete order: " + (System.currentTimeMillis() - currentTime) );
        }catch(Exception e){
            System.out.println("Error occured: "+e);
        }

    }

    public static void main(String args[]) {

//        Supplier<String> stringSupplier = ()-> {
//            System.out.println("Does supplier perform tasks other than return");
//
//            try {
//                Thread.sleep(2000);
//            }
//            catch(InterruptedException e){
//
//            }
//
//            return "Thanks";
//
//        };
//
//        stringSupplier.get();

        launchApplication();
    }

}
