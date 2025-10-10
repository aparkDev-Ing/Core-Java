package com.aaron.thread.runnable;

public class ThreadLocalInheritanceDemo {

    public static void main(String args[]){

        ThreadLocal<String> threadLocalString = new ThreadLocal<String>();

        InheritableThreadLocal<String> parentThreadLocalString = new InheritableThreadLocal<>();

        threadLocalString.set("Main");
        System.out.println("Main Thread Local value: "+threadLocalString.get());
        parentThreadLocalString.set("Kevin");
        System.out.println("Parent's "+ parentThreadLocalString.get());

        Thread thread1 = new Thread(()->
        {

            threadLocalString.set("Thread1");

            System.out.println("Child's before Overriden: "+parentThreadLocalString.get());


            parentThreadLocalString.set("KevinInherited");

            System.out.println("Child Thread Local value: "+ threadLocalString.get());
            System.out.println("Child once overriden: "+parentThreadLocalString.get());

            Thread childThread1 = new Thread(()->{

                threadLocalString.set("Aaron");
                System.out.println("Grand Child Thread Local value: "+threadLocalString.get());

                parentThreadLocalString.set("AaronInherited");
                System.out.println("Grand Child: "+ parentThreadLocalString.get());

            });

            childThread1.start();
        }
        );

        thread1.start();

    }
}
