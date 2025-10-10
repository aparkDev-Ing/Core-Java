package core.java.multithread.chapter12.completablefutureapis;


import core.java.multithread.chapter9.atomicexamples.AtomicIntegerExample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class AllOfExample {

    static AtomicInteger driverId = new AtomicInteger(0);
    static int noOfDrivers= 3;
    static void prepareForParty(){

        List<CompletableFuture<String>> completableFuturesList = new ArrayList<>();


        for(int i =0; i<noOfDrivers;i++) {

            int index = new Random().nextInt(4);
            completableFuturesList.add(CompletableFuture.supplyAsync(() -> pickUpOrder(Menu.menuList.get(index))));
        }

        CompletableFuture<Void> cf1=  CompletableFuture.allOf(completableFuturesList.toArray(new CompletableFuture[0]));

         Void result =  cf1.thenAccept((Void)-> {

           completableFuturesList.forEach(cf->{

               System.out.println("Result of each pickup: " + cf.join());

           });
        }).join();

        System.out.println("Main thread terminating: "+Thread.currentThread().getName());


    }


    static String pickUpOrder(String order){

        System.out.println(Thread.currentThread().getName()+" | Driver " + driverId.getAndIncrement() + " starts picking up an order: "+ order );

        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            Thread.currentThread().interrupt();
            System.out.println("Error occured while picking up a order: "+e);
        }

        return order;
    }
    public static void main(String args[]){

        prepareForParty();

    }

}
