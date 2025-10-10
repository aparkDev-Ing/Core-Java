package core.java.multithread.chapter10.futureandcallback;

import java.util.concurrent.*;


interface Callback2{
    void onComplete(String food);
}
public class FutureCallbackExample {

    public static void main(String args[]){
        new FutureCallbackExample().orderFood();

    }


   static void deliverFood(Future<String> future, Callback2 callback){

        new Thread(()->{

            try {
                System.out.println(Thread.currentThread().getName()+ " has arrived at restuarnt to pick up an order");
                String food = future.get();
                callback.onComplete(food);
            }
            catch(Exception e){

                System.out.println("음식이 캔슬되어서 운전사가 돌아갑니다.");
            }

            if (!future.isCancelled())
            System.out.println("Delivery is completed by "+ Thread.currentThread().getName());
        },"DeliveryMan").start();


    }

    void orderFood(){

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        String food = "Burger";
        System.out.println("음식주문에 성공했습니다! "+ food);

        System.out.println("배달을 기다리는동안 일을합니다");


       Future<String> result =  executorService.submit(()->{

            System.out.println("음식주문이 들어왔습니다! 음식을시작합니다 " + food);

            try{
                Thread.sleep(2000);
            }
            catch(Exception e){

                throw new RuntimeException(e);
            }

            System.out.println("음식이완료되었습니다 운전기사에게 음식을전달합니다! " + food);

            return food;
        });

        System.out.println("음식을 캔슬합니다");
        result.cancel(false);


       try {
           Thread.sleep(500);
       }
       catch(InterruptedException e){

       }



        deliverFood(result, s->{
            System.out.println("Handing a food to a delivery man "+ food);
        });

        executorService.shutdown();
    }

}
