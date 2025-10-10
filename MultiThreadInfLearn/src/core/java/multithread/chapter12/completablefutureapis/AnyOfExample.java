package core.java.multithread.chapter12.completablefutureapis;

import core.java.multithread.chapter9.atomicexamples.AtomicIntegerExample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class AnyOfExample {

    static AtomicInteger driverId = new AtomicInteger(0);
    static int noOfDrivers= 3;
    static void prepareForParty(){

        //List<CompletableFuture<String>> completableFuturesList = new ArrayList<>();
//
//        for(int i =0; i<noOfDrivers;i++) {

            //int index = new Random().nextInt(4);
            CompletableFuture<String> cf1  = CompletableFuture.supplyAsync(() -> pickUpOrder1(Menu.menuList.get(1)));
            CompletableFuture<String> cf2  = CompletableFuture.supplyAsync(() -> pickUpOrder2(Menu.menuList.get(2)));
//        }

        CompletableFuture<Object> cf3=  CompletableFuture.anyOf(cf1,cf2);

        Void result =  cf3.thenAccept((object)-> {

            System.out.println("Result: "+ object );

        }).join();



    }


    static String pickUpOrder1(String order){

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
    static String pickUpOrder2(String order){

        System.out.println(Thread.currentThread().getName()+" | Driver " + driverId.getAndIncrement() + " starts picking up an order: "+ order );

        try {
            Thread.sleep(3000);
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
