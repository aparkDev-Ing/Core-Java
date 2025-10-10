package core.java.multithread.chapter10.executor;

import java.util.concurrent.Executor;

public class SyncExecutorExample {


    public static void main(String args[]) {

        Executor syncExecutor = new SyncExecutor();

        syncExecutor.execute(() ->
        {
            System.out.println("Work in progress");
            System.out.println("Work completed");

        });

        syncExecutor.execute(() ->
        {
            System.out.println("Work in progress");
            System.out.println("Work completed");

        });

    }


    //동기적 구현방법 - 러너블을 받아서 바로 실행하면된다 그리고 이실행의주체는 엑스큐터를사용하는 이스레드가된다
    // 동기적으로사용한다면 사실굳이이렇게 구현할필요는없다 그래도 작업의 제출과 실행은 분리가된다
    // 이게바로 메인에서 직접실행하는법 !
    static class SyncExecutor implements Executor{

        @Override
        public void execute(Runnable command){
            command.run();
        }

    }

}
