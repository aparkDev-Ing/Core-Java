package com.aaron.thread.runnable;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

class Counter{

    //AtomicInteger count = new AtomicInteger();
    static int count ;
    Object lock;

    public static void increment(){
    //  this.count.getAndIncrement() ;
        count++;
        //count = count + 1;
        //count = 1002+1;
        //count = 1002+1;
    }

    public void decrement(){
    //  this.count.getAndDecrement() ;
        count--;
    }

    public static void loopIncremet() {

        for(int i =0; i <10000; i++) {
            increment();
//            System.out.println("Thread: " + Thread.currentThread());
        }

    }

    public static List<String> addString(List<String> newString, List<String> stringList){



        for(String a : stringList){
            newString.add(a );
            newString.add(a + "1");
        }

        return newString;
    }
}

public class SynchronizedDemo {

    public static void main(String args[]) throws InterruptedException{

        Integer i = 10;

        System.out.println("Current time in UTC: " + LocalDateTime.now(ZoneOffset.UTC).toString());

        System.out.println("Current time - 10 DAYS in UTC: " +  LocalDateTime.now(ZoneOffset.UTC).minusDays(i).toString());


        Counter counter = new Counter();

        List<String> stringArray = new ArrayList<>();

        stringArray.add("Aaron");


        List<String> newStringArray = new ArrayList<>();

//        Thread t1 = new Thread(new Runnable(){
//
//            @Override
//            public void run() {
//
//                Counter.addString(newStringArray, stringArray);
//
//            }
//        },"Aaron");


//        Thread t1 = new Thread( ()-> {
//
//            Counter.addString(newStringArray, stringArray);
//
//        } ,"Aaron");

        //위에랑 같음
        Thread t1 = new Thread( ()->

                Counter.addString(newStringArray, stringArray)

        ,"Aaron");

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {

                Counter.addString(newStringArray, stringArray);

            }
        },"");

        t1.start();
        t2.start();


        t1.join();
        t2.join();


//        System.out.println("Count: " + newStringArray);
    }
}
