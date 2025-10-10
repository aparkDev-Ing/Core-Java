package core.java.multithread.chapter10.threadpool;

public class ThreadPoolMain {

   static  int noOfThreads =30;
    public static void main(String args[]) throws InterruptedException{

        SimpleThreadPool simpleThreadPool =new SimpleThreadPool(noOfThreads);

        for(int i=0;i<1000;i++){
            int taskId =i;

            simpleThreadPool.submit(()->{

                System.out.println(Thread.currentThread().getName()+ "| Task: "+ taskId+" in progress");

                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e) {

                }
                System.out.println(Thread.currentThread().getName()+ "| Task: "+ taskId+" Finished");

            });

        }

        Thread.sleep(3000);
        simpleThreadPool.shutdown();
        System.out.println("Threadpool terminated!");

    }

}
