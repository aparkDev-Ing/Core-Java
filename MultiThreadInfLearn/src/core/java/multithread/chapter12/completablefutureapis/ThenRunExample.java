package core.java.multithread.chapter12.completablefutureapis;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class ThenRunExample {
    static List<String> stringList= Arrays.asList("연필","돈","가방");

    static String thingStatic = "";
    static String pick(){

        System.out.println("아이가 물건을집습니다! ");

        try {
            Thread.sleep(500);
        }
        catch(InterruptedException e){

        }
        int index = new Random().nextInt(3);
        String thing = stringList.get(index);

        thingStatic = thing;

        return thing;
    }

    static void launchApplication(){

        System.out.println("돌잡이가 시작됩니다! ");

//        Void future = CompletableFuture.supplyAsync(()->pick()).thenRun( ()->{
//
//            System.out.println("사회자가 발표합니다!");
//            System.out.println("아이가 " + thingStatic +" 을 집었습니다");
//        }).join();


        CompletableFuture<String> future = CompletableFuture.supplyAsync(()->pick());

        //Void nothing =
         future.thenRun(()->{
            try{
                String result = future.get();
                System.out.println("사회자가 발표합니다!");
                System.out.println("아이가 " + result +" 을 집었습니다");
            }catch(Exception e) {

            }
        } ).join();

    }
    public static void main(String args[]){

        launchApplication();

    }
}
