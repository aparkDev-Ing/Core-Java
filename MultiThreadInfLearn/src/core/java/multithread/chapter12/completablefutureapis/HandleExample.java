package core.java.multithread.chapter12.completablefutureapis;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class HandleExample {

    static String deliverFood(String menu){

        System.out.println(Thread.currentThread().getName()+" | starts to deliver a menu");

        System.out.println(Thread.currentThread().getName()+" | driver missed a side menu to deliver. Going back to restuarnat to pick it up");

        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            Thread.currentThread().interrupt();
            System.out.println(e);
        }

        throw new RuntimeException("driver missed a side menu to deliver. Delivery Failed");

        //System.out.println(Thread.currentThread().getName()+" | driver successfully delivered food! "+ menu);

        //return menu;
    }

    static void launchApplication() throws InterruptedException{

        int index = new Random().nextInt(4);

        String order = Menu.menuList.get(index);

        System.out.println("Customer has ordered a food: "+order);

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(()->deliverFood(order)).handle((r,t)->{

            if(t!=null){
                return Thread.currentThread().getName() +" has failed to delivery due to:: "+t;
            }
            else{
                return r;
            }
        });

        //Thread.sleep(1000);

        //cf1.cancel(true);

        String result = cf1.join();

        System.out.println("Delivery result:: "+ result);

    }
    public static void main(String args[]) throws InterruptedException{

        launchApplication();
    }

}
