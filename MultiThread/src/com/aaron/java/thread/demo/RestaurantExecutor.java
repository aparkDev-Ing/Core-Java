package com.aaron.java.thread.demo;

import java.util.Scanner;

public class RestaurantExecutor {

    public static void main(String args[]){

        //Runnable r = new Customer("Aaron");

        Customer david = new Customer("David");
        Customer jane = new Customer("Jane");
        Customer jesse = new Customer("Jesse");

//        Thread tr1 = new Thread(aaron,"Aaron1");
//        Thread tr2 = new Thread(aaron,"Aaron2");
//        Thread tr3 = new Thread(aaron,"Aaron3");

        Thread tr1 = new Thread(david);
        Thread tr2 = new Thread(jane);
        Thread tr3 = new Thread(jesse);


        tr1.setDaemon(true);
        tr2.setDaemon(true);
        tr3.setDaemon(true);

        tr1.start();
        tr2.start();
        tr3.start();

        Chef aaron = new Chef("Aaron", Cuisine.BURGER.getCuisine());
        Chef jaeyoung = new Chef("Jaeyoung", Cuisine.BIBIMBAP.getCuisine());
        Chef hongsun = new Chef("Hongsun", Cuisine.TACO.getCuisine());
        Chef byeongjae = new Chef("BJ", Cuisine.PASTA.getCuisine());

        Thread c1 = new Thread(aaron);
        Thread c2 = new Thread(jaeyoung);
        Thread c3 =new Thread(hongsun);
        Thread c4 = new Thread(byeongjae);


        c1.start();
        c2.start();
        c3.start();
        c4.start();


        try {
            tr1.join();
            tr2.join();
            tr3.join();

            c1.join();
            c2.join();
            c3.join();
            c4.join();
        }
        catch(InterruptedException e){
            System.out.println("Thread interrupted while join " + e);
        }

        System.out.println("Total number of orders: " + Order.currentNumberOfOrders);
        System.out.println("Total number of orders left in kitchen: " + Kitchen.listOfOrders.size() );
        System.out.println("Customer "+ david.getName() + " has placed "+ david.listOfOrderFromCustomer.size() +" orders"  );
        System.out.println("Customer "+ jane.getName()  + " has placed "+  jane.listOfOrderFromCustomer.size() +" orders");
        System.out.println("Customer "+ jesse.getName()  + " has placed "+  jesse.listOfOrderFromCustomer.size() +" orders");

    }

}



