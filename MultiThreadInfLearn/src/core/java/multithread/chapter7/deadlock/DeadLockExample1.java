package core.java.multithread.chapter7.deadlock;

public class DeadLockExample1 {

    static Object lock1 = new Object();
    static Object lock2 = new Object();

    public static void main(String args[]){

        Thread th1 = new Thread(()->
        {
            synchronized (DeadLockExample1.lock1){

                System.out.println("Thread1 acquired lock1... ");

                System.out.println("Thread1 now will try to acquire lock2... ");
                synchronized (DeadLockExample1.lock2){

                    System.out.println("Thread1 acquired lock2... ");
                }

            }
        });


        Thread th2 = new Thread(()->
        {
            synchronized (DeadLockExample1.lock2){

                System.out.println("Thread2 acquired lock2... ");

                System.out.println("Thread2 now will try to acquire lock1... ");
                synchronized (DeadLockExample1.lock1){

                    System.out.println("Thread2 acquired lock1... ");
                }

            }
        });


        th1.start();
        th2.start();

    }
}
