package core.java.multithread.chapter10.futureandcallback;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class FutureExample {

    public static void main(String args[]) {


//        String lineItemId= "0#HC#WriteOff";
//
//        System.out.println(lineItemId.contains("WriteOff"));
//
//        Map<String,String> vertexConfigMap = new HashMap<>();
//        vertexConfigMap.put("excludeIfContains","WriteOff,random");
//
//
//        List<String> stringList = Arrays.asList(vertexConfigMap.get("excludeIfContains").split("\\s*,\\s*"));
//
//        System.out.println(stringList.stream().anyMatch(s ->lineItemId.contains(s)));

        new FutureExample().waitForDelivery();
    }



    public void waitForDelivery() {

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        System.out.println("우버이츠로 음식배달을 시켯습니다");

       Future<String> result =  executorService.submit(()->{
           System.out.println("식당이 음식준비중입니다");


//           try {
               Thread.sleep(5000);
//           }
//           catch(InterruptedException e){
//                throw new RuntimeException(e);
//           }

           //throw new RuntimeException();


            System.out.println("음식이준비되엇습니다! 배달기사가 출발합니다!");

           return "더블치즈버거";
        });

       //캔슬 api 로 음식주문을취소하기

        try {
            Thread.sleep(500);
        }
        catch(InterruptedException e){

        }

        //boolean cancelled = result.cancel(true);

        result.cancel(true);

        //System.out.println("메인스레드가 블락되지않앗다는증거! ");

        //이렇게 isdone 을 메인스레드가 쓰면서 기다리면, 밑의 get 에서는 wait 할일이없다 왜냐면 이미작업이 끝낫으니까 이건 동기적인 프로그래밍이다
//        while(!result.isDone()){
//            System.out.println("음식을 기다리는중");
//            try {
//                Thread.sleep(500);
//            }
//            catch(InterruptedException e){
//
//            }
//
//        }

//        if(!result.isCancelled()) {

            try {

                //main thread on waiting state until result is returned
                String food = result.get();
                System.out.println("주문한 음식이 배달완료하엿습니다! " + food);

            } catch (InterruptedException | ExecutionException | CancellationException e) {

                System.out.println("주문한 배달받는것에 실패했습니다 " + e);
            }
//        }
//        else{
//            System.out.println("주문이 취소되었습니다!");
//        }

        //System.out.println("음식배달을시키고 일을계속합니다!");
        executorService.shutdown();

    }


}
