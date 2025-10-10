package core.java.multithread.chapter12.completablefutureapis;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class ThenAcceptExample {

    static List<String> stringList= Arrays.asList("연필","돈","가방");

    static String pick(){

        System.out.println(Thread.currentThread().getName()+ " 아이가 물건을집습니다! ");

        try {
            Thread.sleep(500);
        }
        catch(InterruptedException e){

        }
        int index = new Random().nextInt(3);
        String thing = stringList.get(index);

        return "thing";
    }

    static void launchApplication(){

        System.out.println("돌잡이가 시작됩니다! ");

        CompletableFuture<Void> future = CompletableFuture.supplyAsync(()->pick()).thenAccept(r->{

            System.out.println(Thread.currentThread().getName()+ " 사회자가 발표합니다!");
            System.out.println("아이가 " + r +" 을 집었습니다");
        }).thenAcceptAsync((r)->{

            System.out.println(Thread.currentThread().getName()+ " 엄마가 즐거워합니다 "+ r);

        });

        System.out.println("Result: "+future.join());

    }
    public static void main(String args[]){

        launchApplication();

    }

}
