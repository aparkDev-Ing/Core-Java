package com.aaron.thread.runnable;


import java.io.IOException;

class Pizza{


}

public class ThreadLocalDemo
{

    public String word;

    public String getWord(){

        return this.word;
    }
    public void setWord(String word){
        this.word = word;
    }
    public static void main(String args[]) throws InterruptedException {

        ThreadLocal<String> threadLocalString = new ThreadLocal<String>();

        ThreadLocal<String> threadLocalString2 = new ThreadLocal<String>();

        if(threadLocalString2.get() == null){
            threadLocalString2.set("threadLocalString2");
        }

        System.out.println("Lazy Loading: " + threadLocalString2.get());


        ThreadLocal<Object> threadLocalObject = new ThreadLocal<Object>();

        ThreadLocalDemo threadLocalDemo1 = new ThreadLocalDemo();

        Thread thread1 = new Thread( ()->{

            threadLocalString.set("Aaron");

            threadLocalObject.set("Pizza");

            threadLocalDemo1.setWord("CommonObject");

            try {
                Thread.sleep(3000);

            }
            catch(InterruptedException ie){
                System.out.println("Something went wrong while sleeping");
            }

            System.out.println(threadLocalString.get());
            System.out.println(threadLocalObject.get());
            System.out.println(threadLocalDemo1.getWord());
        }) ;

        Thread thread2 = new Thread( ()->{

            threadLocalString.set("devin");

            threadLocalObject.set(new Pizza());

            threadLocalDemo1.setWord("CommonObject");


            try {
                Thread.sleep(3000);

            }
            catch(InterruptedException ie){
                System.out.println("Something went wrong while sleeping");
            }

//            threadLocal.remove();

            System.out.println(threadLocalString.get());
            System.out.println(threadLocalObject.get());
            System.out.println(threadLocalDemo1.getWord());
        }) ;

        thread1.start();
        thread2.start();

    }

}
