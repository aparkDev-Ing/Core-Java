package core.java.multithread.chapter12.completablefutureapis;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

public class SupplyAsyncExample {


    static String prepareFood(String food){

        System.out.println("Chef has started to prepare for an order: "+ Thread.currentThread().getName());

        try{
           Thread.sleep(500);
        }catch(Exception e){
            System.out.println(e);
        }

        System.out.println("Chef has completed an order: "+ Thread.currentThread().getName());

        return food;
    }
    static void orderFood(String food){

        System.out.println(Thread.currentThread().getName()+" just placed an order: "+ food);

    }
    static void launchApp(){



        String order = "Pizza";

        orderFood(order);
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->prepareFood(order));

//        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()-> {
//
//            return prepareFood(order);
//        });
//        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync( ()->"");

        try {
            //String food = completableFuture.get();
            String food = completableFuture.join();
            System.out.println("All order has been fulfilled! Closing restaurant! " + food );
        }
        catch(Exception e){
            System.out.println("Error occured while fulfilling customer order: "+ e);
        }

    }

    public static void main(String args[]){

        launchApp();
    }

}
