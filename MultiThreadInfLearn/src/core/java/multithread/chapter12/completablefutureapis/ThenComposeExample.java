package core.java.multithread.chapter12.completablefutureapis;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.CompletableFuture;

class Menu{
    static List<String> menuList = Arrays.asList("Pizza","Burger","Sandwich","Taco");

    static Map<String,Integer> menuMap = new HashMap<>();
}
public class ThenComposeExample {



    static String placeOrder(){

        //System.out.println(Thread.currentThread().getName()+ " | customer wants to place an order!" );
        int index = new Random().nextInt(4);

        String menu = Menu.menuList.get(index);
        System.out.println(Thread.currentThread().getName()+ " | customer wants to place an order: " + menu);

        return menu;
    }


    static CompletableFuture<Integer> computePrice(String menu){

        return CompletableFuture.supplyAsync(()-> calculatePrice(menu));
    }

    static Integer calculatePrice(String menu){

        System.out.println(Thread.currentThread().getName()+ " | Owner calculating a price for "+ menu);
        Integer price = Menu.menuMap.get(menu);

        return price;
    }

    static void launchApplication(){


        setPrice();

//        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(()-> placeOrder()).thenApply(menu->
//                {
//
//                try {
//                    Thread.sleep(1000);
//                }
//                catch(InterruptedException e){
//
//                }
//                    System.out.println(Thread.currentThread().getName()+ " | Chef has fulfilled order! "+ menu);
//                    return menu;
//                }
//                );
//
//        CompletableFuture<Integer> cf3 = cf1.thenCompose( result -> computePrice(result) );
//
//        Integer i = cf3.join();
//
//        System.out.println("Final price: " + i );

        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(()-> placeOrder()).thenApply(menu->
                {

                    try {
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException e){

                    }
                    System.out.println(Thread.currentThread().getName()+ " | Chef has fulfilled order! "+ menu);
                    return menu;
                }
        ).thenCompose(result -> computePrice(result));

//        CompletableFuture<Integer> cf3 = cf1.thenCompose( result -> computePrice(result) );
//
//        Integer i = cf3.join();

        System.out.println("Final price: " + cf1.join() );

    }

    static void setPrice(){

        Menu.menuMap.put("Pizza", 10);
        Menu.menuMap.put("Burger", 25);
        Menu.menuMap.put("Sandwich", 40);
        Menu.menuMap.put("Taco", 60);
    }
    public static void main(String args[]){

        launchApplication();


    }
}
