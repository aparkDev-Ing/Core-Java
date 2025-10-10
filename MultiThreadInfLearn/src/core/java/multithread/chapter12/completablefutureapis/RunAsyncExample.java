package core.java.multithread.chapter12.completablefutureapis;

import java.util.concurrent.CompletableFuture;

public class RunAsyncExample {

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

        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{

            prepareFood(order);
        });

        try {

            completableFuture.join();
            //String food = completableFuture.get();
            System.out.println("All order has been fulfilled! Closing restaurant!");
        }
        catch(Exception e){
            System.out.println("Error occured while fulfilling customer order: "+ e);
        }

    }

    public static void main(String args[]){

        launchApp();
    }

}
