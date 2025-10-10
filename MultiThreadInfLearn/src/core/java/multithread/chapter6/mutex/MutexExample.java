package core.java.multithread.chapter6.mutex;

import java.util.Stack;

public class MutexExample {

    public static void main(String args[]){

        SharedData sharedData = new SharedData(new Mutex());

       Thread th1 =  new Thread(()->{

            sharedData.sum();
        },"Thread1");


       Thread th2 =  new Thread(()->{

            sharedData.sum();
        },"Thread2");

       Long startTime = System.currentTimeMillis();

       th1.start();
       th2.start();

       try {
           th1.join();
           th2.join();
       } catch (InterruptedException e) {
           throw new RuntimeException(e);
       }

        Long endTime = System.currentTimeMillis();

        System.out.println("Time it took to complete in ms: "+ (endTime - startTime));
        System.out.println("Final result of sum: "+ sharedData.getCounter());

//        int i = 0;
//
//        i += 2>1? 5: 0 ;
//
//        int x = 2>1? 5: 0;
//
//        System.out.println(i);
//        System.out.println(x);


    }

}
