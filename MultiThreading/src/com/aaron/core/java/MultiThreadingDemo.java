package com.aaron.core.java;

public class MultiThreadingDemo {
    public static void main (String args[]){

        Thread.currentThread().setPriority(7);

        MineThread thread1 = new MineThread();
        thread1.setName("Aaron");

        MineThread thread2 = new MineThread();
        thread2.setName("");

//        thread1.setPriority(10);


//        thread1.setPriority(9);
//        thread2.setPriority(3);

        thread1.start();
        thread2.start();

//        for(int i =0; i <10; i++){
//            System.out.println(Thread.currentThread().getName());
//        }

        System.out.println("Main Thread Priorty "+Thread.currentThread().getPriority());
        System.out.println("Thread 1 Priorty " +thread1.getPriority());
        System.out.println("Thread 2 Priorty " +thread2.getPriority());

    }

}

class MineThread extends Thread{

    @Override
    public void run() {

        Thread thread = new Thread("Dummy");

        System.out.println(this.getName() + " "+this.getPriority());

        System.out.println(thread.getName() + " "+thread.getPriority());

        //this.setName("Aaron");
//       for(int i =0; i < 10; i++) {
//           System.out.println("Hi " + this.getName());
//           System.out.println(Thread.currentThread().getName());
//
//       }

    }

//    @Override
//   public void start(){
//        System.out.println("Overriding start Method ");
//    }
}