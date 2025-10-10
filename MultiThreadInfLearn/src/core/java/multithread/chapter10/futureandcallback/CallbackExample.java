package core.java.multithread.chapter10.futureandcallback;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallbackExample {

    public static void main(String args[]){

        new CallbackExample().waitForDelivery();
    }

    static void receiveFood(String result, Callback callback){

        callback.onComplete(result);
    }


    public void waitForDelivery(){

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        System.out.println("우버이츠로 음식배달을 시켯습니다");

        executorService.execute(()->{

            System.out.println("식당이 음식준비중입니다");

            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){

            }

            System.out.println("음식이준비되엇습니다! 배달기사가 출발합니다!");

            receiveFood("더블치즈버거", s -> {
                System.out.println("주문한 음식이 배달완료하였습니다 "+ s);
            });

        });


        System.out.println("음식배달을시키고 일을계속합니다!");


        executorService.shutdown();

    }

}

//class MyCallback implements Callback{
//
//    @Override
//    public void onComplete(String result){
//        System.out.println(result);
//    }
//}

//@FunctionalInterface
interface Callback{

    void onComplete(String result);
}


