package core.java.multithread.chapter9.atomicexamples;

import java.lang.reflect.AnnotatedType;
import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanExample {

    Burner burner = new Burner();

    public static void main(String args[]){



        AtomicBooleanExample atomicBooleanExample = new AtomicBooleanExample();

        Thread chef1 = new Thread(()->
        {

            atomicBooleanExample.cook();

        },
        "Chef1");

        Thread chef2 = new Thread(()->
        {

            atomicBooleanExample.cook();

        },
        "Chef2");

        Thread chef3 = new Thread(()->
        {

            atomicBooleanExample.cook();

        },
        "Chef3");


        chef1.start();
        chef2.start();
        chef3.start();

    }

    void cook(){

        while(!burner.turnBurnerOn()){

            System.out.println(Thread.currentThread().getName()+ " Failed to turn burner on. Looks like other chef is using it! ");


        }

        burner.cook();

        burner.turnBurnerOff();


    }

}

class Burner{

    AtomicBoolean isBurnerOn = new AtomicBoolean(false);

    boolean turnBurnerOn(){


        return isBurnerOn.compareAndSet(false,true);
    }

    void turnBurnerOff(){

        System.out.println(Thread.currentThread().getName()+ " Cooking completed! Turning the burner off!");
        isBurnerOn.set(false);


    }

    void cook(){
        System.out.println(Thread.currentThread().getName()+ " Cooking Started!");
    }

}