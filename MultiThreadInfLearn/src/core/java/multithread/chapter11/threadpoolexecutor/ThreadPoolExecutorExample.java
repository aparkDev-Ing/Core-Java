package core.java.multithread.chapter11.threadpoolexecutor;

import java.util.concurrent.*;

public class ThreadPoolExecutorExample {

    public static void main(String args[]){

        int corePoolSize = 2;
        int maxPoolSize = 4;
        long keepAliveTime = 0L;
        //BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>(); //이 큐를쓰게되면, 큐 사이즈 리밋이없어서 코어스레드수보다 더 스레드를소환할경우가없다 즉 맥스스레드풀사이즈만큼 스레드가 늘어나지않는다
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(4);

        int taskNum = 8;
        //ExecutorService executorService = new ThreadPoolExecutor(corePoolSize,maxPoolSize,keepAliveTime, TimeUnit.SECONDS,workQueue);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,maxPoolSize,keepAliveTime, TimeUnit.SECONDS,workQueue);

        for(int i =0; i<taskNum;i++){
            final int taskId = i;

            threadPoolExecutor.execute(()->{

                System.out.println(
                        Thread.currentThread().getName() +" 가 태스크 "+ taskId+" 를 실행하고있습니다!"
                );

            });
        }


        threadPoolExecutor.shutdown();
    }
}
