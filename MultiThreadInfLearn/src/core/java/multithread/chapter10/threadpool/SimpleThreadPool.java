package core.java.multithread.chapter10.threadpool;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleThreadPool {

    private int numThreads;

    private Queue<Runnable> taskQueue ;

    private Thread[] threads;

    private volatile boolean isShutDown;

    public SimpleThreadPool(int numThreads){
        this.numThreads =numThreads;
        taskQueue = new LinkedList<>();
        this.threads=new Thread[numThreads];
        this.isShutDown=false;

        for(int i =0; i<numThreads;i++){

            threads[i]= new Thread(new Worker(),"Thread "+(i+1));
            threads[i].start();
        }
    }

    public void submit(Runnable task){

        if(!isShutDown){
            synchronized (taskQueue) {
                taskQueue.offer(task);
                taskQueue.notifyAll();
            }
        }

    }

    public void shutdown(){
        this.isShutDown=true;

        synchronized (taskQueue){
            taskQueue.notifyAll();
        }

        for(Thread thread: threads){
            try{
                thread.join();
            }
            catch(InterruptedException e){
                throw new RuntimeException(e);
            }
        }

    }

    private class Worker implements  Runnable{

        @Override
        public void run(){

            while(!isShutDown){

                Runnable task;

                synchronized (taskQueue) {
                    while (taskQueue.isEmpty() && !isShutDown) {
                        try {
                            taskQueue.wait();
                        }
                        catch(InterruptedException e){
                            System.out.println("Exception occured while watiting "+e);
                        }
                    }




                }

                if(!taskQueue.isEmpty()) {
                    task = taskQueue.poll();

                }else{
                    continue;
                }
                task.run();

            }
        }

//        public void work(){
//
//            Runnable task;
//
//            synchronized (taskQueue) {
//                while (taskQueue.isEmpty() && !isShutDown) {
//                    try {
//                        taskQueue.wait();
//                    }
//                    catch(InterruptedException e){
//                        System.out.println("Exception occured while watiting "+e);
//                    }
//                }
//
//                if(!taskQueue.isEmpty()) {
//                    task = taskQueue.poll();
//                    task.run();
//                }
//
//
//            }
//
//
//        }

    }



}


