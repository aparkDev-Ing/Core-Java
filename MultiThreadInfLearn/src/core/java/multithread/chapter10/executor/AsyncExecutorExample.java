package core.java.multithread.chapter10.executor;

import java.util.concurrent.Executor;

public class AsyncExecutorExample {


    public static void main(String args[]) {

        Executor asyncExecutor = new AsyncExecutor();

        //AsyncExecutorExample.AsyncExecutor asyncExecutor2 = new AsyncExecutorExample.AsyncExecutor();
        //Executor asyncExecutor3 = new AsyncExecutorExample.AsyncExecutor();

        asyncExecutor.execute(() ->
        {
            System.out.println("Work in progress");
            System.out.println("Work completed");

        });

        asyncExecutor.execute(() ->
        {
            System.out.println("Work in progress");
            System.out.println("Work completed");

        });

    }


    //비동기 실행방법 - 메인이실행시키느것이아닌, 독립적인스레드가생성되서 새로운 콜스택이생긴다는것
    // 사실 그냥 메인에서 스레드 몇개만들어서 바로구현해도(람다로) 똑같은 일이지만 이건 테스크를만들어서 submit 하는것과 스레드객체생성과 실행의 분리를 보여주는 예제
    static class AsyncExecutor implements Executor{

        @Override
        public void execute(Runnable command){

            Thread newThread = new Thread(command);

            newThread.start();
        }

    }

}
