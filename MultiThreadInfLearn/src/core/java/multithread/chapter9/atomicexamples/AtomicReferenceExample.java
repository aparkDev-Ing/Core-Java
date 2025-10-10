package core.java.multithread.chapter9.atomicexamples;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceExample<T> {

    static Burger burger = new Burger("American","Double Cheese",10);
     static AtomicReference<Food> atomicReference = new AtomicReference<>(burger);

    boolean changeOrder(Food food){

        //atomicReference.get
        return atomicReference.compareAndSet(atomicReference.get(), food);

    }
    public static void main(String args[]){



        // AtomicReference<? extends Food> atomicReference2 = new AtomicReference<>();


      //   AtomicReference<Burger> burgerAtomicReference = new AtomicReference<Burger>();

        // atomicReference2=new AtomicReference<Burger>();


        AtomicReferenceExample atomicReferenceExample = new AtomicReferenceExample();
        Thread customer1 = new Thread(()->
        {
            Burger burger = new Burger("Korean","Bulgogi",16);
            while(!atomicReferenceExample.changeOrder(burger)){

                System.out.println(Thread.currentThread().getName()+" has failed to change an order!");
            }

            System.out.println(Thread.currentThread().getName() + " was able to change an order to "+ burger);

        },"Customer 1");




        Thread customer2 = new Thread(()->
        {
            Pizza pizza = new Pizza("Italian","Pepproni",20);
            while(!atomicReferenceExample.changeOrder(pizza)){

                System.out.println(Thread.currentThread().getName()+" has failed to change an order!");
            }

            System.out.println(Thread.currentThread().getName() + " was able to change an order to "+ pizza);

        },"Customer 2");


        customer1.start();
        customer2.start();
        try {
            customer1.join();
            customer2.join();

        }
        catch(InterruptedException e){

        }

        System.out.println("Current Food in queue is: "+atomicReference.get());

    }
}





class Food{

    String name;



    int calrories;


}

class Burger extends Food{

    String type;

    public Burger(String type, String name, int price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    String name;

    int price;

    @Override
    public String toString() {
        return "Burger{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

class Pizza extends Food{

    String type;

    String name;

    public Pizza(String type, String name, int price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    int price;

    @Override
    public String toString() {
        return "Pizza{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}