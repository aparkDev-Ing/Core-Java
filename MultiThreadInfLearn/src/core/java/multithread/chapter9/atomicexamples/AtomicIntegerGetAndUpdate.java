package core.java.multithread.chapter9.atomicexamples;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerGetAndUpdate {

    final static int noOfDishesPerChef = 2;
    static AtomicInteger totalPriceOfDish = new AtomicInteger(0);

    static void operateRestaurant(){

        //int prev = 0;

       // int randomPrice;
       int finalPrice =  totalPriceOfDish.updateAndGet(price->{



            int randomPrice = new Random().nextInt(100);

            //System.out.println(Thread.currentThread().getName()+ "'s Price of this dish "+ randomPrice);

            return price+randomPrice;
        });



       System.out.println(Thread.currentThread().getName() +": final price of this dish: "+ finalPrice);

    }
    public static void main(String args[]){


        Thread chef1 = new Thread(()->
        {

            for(int i=0; i<noOfDishesPerChef;i++ ){


                operateRestaurant();

            }

        },"Chef1");

        Thread chef2 = new Thread(()->
        {

            for(int i=0; i<noOfDishesPerChef;i++ ){

                operateRestaurant();
            }

        },"Chef2");

        chef1.start();
        chef2.start();

        try {
            chef1.join();
            chef2.join();
        }
        catch(InterruptedException e){

        }
        System.out.println("Total price of dishes today(total sale): " + totalPriceOfDish);
    }
}
