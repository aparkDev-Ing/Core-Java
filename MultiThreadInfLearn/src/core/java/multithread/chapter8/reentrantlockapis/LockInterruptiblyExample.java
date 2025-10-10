package core.java.multithread.chapter8.reentrantlockapis;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptiblyExample {

//    public static void main(String args[]){
//
//        A a = new A();
//
//        A.B b = a.new B();
//
//        b.method();
//
//    }

    public static void main(String args[]){

        Map<String,String> newHashMap = new HashMap<>();

        ReentrantLock lock = new ReentrantLock();

        Thread th1 = new Thread(()->
        {
            try{
                lock.lockInterruptibly();
                System.out.println("Thread "+Thread.currentThread().getName()+" acquired a lock. 잠수를 시작합니다");
                Thread.sleep(3000);
                System.out.println("잠수에 성공햇습니다!");
            }
            catch(InterruptedException e){
                System.out.println("Thread "+ Thread.currentThread().getName()+" got Interrupted! " + e);
            }
            finally {
                System.out.println(Thread.currentThread().getName()+ " 이 잠금을 해제합니다");
                lock.unlock();

            }

        },"Thread1" );


        Thread th2 = new Thread(()->
        {
            try{

                Thread.sleep(1000);
                th1.interrupt();
            }
            catch(InterruptedException e){
                System.out.println("Thread "+ Thread.currentThread().getName()+" got Interrupted! " + e);
            }


        },"Thread2" );


        th1.start();
        //th2.start();

    }


}

class A{ /// A 는 B의 외부클래스

    //static int x = 5;
    int i = 50;




    class B{ // B 는 A의 내부클래스

        //int x = 30;

        int i = 30;
        void method(){ //이 B를 A에서만 쓴다는가정이라면, class A에 넣는게 효율적

            int i = 200;
            //            A a = new A();
            //            System.out.println("A's i: " + a.i);

            System.out.println("B's local i: "  + i) ; //200
            System.out.println("B's i: "  + this.i) ;  //30
            System.out.println("A's i: "  + A.this.i) ; //100
        }


    }


}
