package core.java.multithread.chapter10.executorservice;

import java.sql.SQLOutput;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuteExample {

    public static void main(String args[]){

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        //완전히 같은 function 이지만, 앞으로는우리가 자바가제공하는 스레드풀을이용해서 작업제출에만 신경쓰고 작업실행관리는 스레드풀에게 맡기자는것

        //작업제출후 스레드풀이 내부적으로 스레드생성후 비동기 작업
        executorService.execute(()->{
            System.out.println("Async task starts: ");
        });

        //메인스레드가 직접 스레드소환후 비동기 작업 실행
        new Thread(()->{
            System.out.println("Async task starts: ");
        });

        executorService.shutdown();
    }

}
