package core.java.multithread.chapter5.singlemultithread;


public class MultithreadPractice {

   static int sum = 0;
   static Object obj =new Object();

    public static void main(String args[]){

        long startTime = System.currentTimeMillis();

        Calculator calculator = new Calculator();
        Thread thread1 = new Thread(calculator);

        Thread thread2 = new Thread(calculator);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long endTime = System.currentTimeMillis();


        System.out.println("Time it took to complete calculations in ms: "+ (endTime-startTime));
        System.out.println("Final Result: "+ sum);
    }

//    public synchronized static void sum (){
//        for(int i =0; i<10; i++) {
//            try {
//                Thread.sleep(100);
//                sum++;
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
//    }

    public synchronized static void sum (){

                sum++;

        }


}

class Calculator implements Runnable{

    @Override
    public void run(){
        //MultithreadPractice obj = new MultithreadPractice();

//        try {
//            Thread.sleep(100);
////                synchronized (MultithreadPractice.obj) {
//            MultithreadPractice.sum();
////                }
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

       for(int i =0; i<10; i++) {
            try {
                Thread.sleep(100);
//                synchronized (MultithreadPractice.obj) {
                    MultithreadPractice.sum();
//                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

}
