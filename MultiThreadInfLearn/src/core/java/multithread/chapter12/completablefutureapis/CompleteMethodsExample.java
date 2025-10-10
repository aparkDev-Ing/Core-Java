package core.java.multithread.chapter12.completablefutureapis;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CompleteMethodsExample {


    static String serve(String food){
        System.out.println(Thread.currentThread().getName()+" | is starting to serve food: "+ food);

        try {
            Thread.sleep(1000);
        }
        catch(Exception e ){
            System.out.println("Thread got interrupted while sleeping: "+e);

        }
        throw new RuntimeException("Socket TimeOut");

        //ystem.out.println(Thread.currentThread().getName()+" | is finished with serve. Now will clean a restaurant ");

        //return food;
    }

    static String serve2(String food){
        System.out.println(Thread.currentThread().getName()+" | is starting to serve food: "+ food);

        try {
            Thread.sleep(2000);
        }
        catch(Exception e ){
            System.out.println("Thread got interrupted while sleeping: "+e);

        }

        System.out.println(Thread.currentThread().getName()+" | is finished with serve: "+food);
        return food;
    }
    static void clean(){

        System.out.println(Thread.currentThread().getName()+" | is starting to clean the restaurant ");

        try {
            Thread.sleep(1000);
        }
        catch(Exception e ){
            System.out.println("Thread got interrupted while sleepig: "+e);
        }

        System.out.println(Thread.currentThread().getName()+" | is finished with cleaning.");

    }

    static void assignToWorker(){
        new Thread(()->
            clean()
        ,"New Worker").start();
    }

//    //Complete Method
//    static void startRestaruant(){
//
//        String food = Menu.menuList.get(new Random().nextInt(4));
//
//        CompletableFuture<String> future = new CompletableFuture<>();
//
//        try {
//            String result = serve(food);
//            //System.out.println(result);
//            clean();
//        }
//        catch(Exception e){
//            boolean result2 = future.complete(food);
//            if(result2) {
//                System.out.println(result2+ " | "+ future.join() + ": "+Thread.currentThread().getName() + " | is too late to serve a food. Owner is helping and assigning cleaning to new worker: " + e);
//                assignToWorker();
//            }
//        }
//
//    }

    //CompleteFuture Method
//    public static void startRestaruant(){
//
//        String food = Menu.menuList.get(new Random().nextInt(4));
//
//        CompletableFuture<String> future = CompletableFuture.completedFuture(food);
//
//        try {
//            String result = serve(food);
//            //System.out.println(result);
//            clean();
//        }
//        catch(Exception e){
//
//                String result2 = future.join();
//                System.out.println(result2+": "+ Thread.currentThread().getName() + " | is too late to serve a food. Owner is helping and assigning cleaning to new worker: " + e);
//                assignToWorker();
//
//        }
//
//    }

//    //CompleteOnTime Method
//    public static void startRestaruant(){
//
//        String food = Menu.menuList.get(new Random().nextInt(4));
//
//        assignToWorker();
//        CompletableFuture<String> future = CompletableFuture.supplyAsync(()->serve2(food)).completeOnTimeout(food,3, TimeUnit.SECONDS);
//        System.out.println(Thread.currentThread().getName()+ " |  Restaruant was able to serve customer: "+future.join());
//
//    }

    //CompleteExceptionlaly Method
    public static void startRestaruant(){

        String food = Menu.menuList.get(new Random().nextInt(4));

        CompletableFuture<String> future = new CompletableFuture<>();

        CompletableFuture<String> future2 = new CompletableFuture<>();

        try {
            //캔슬을하게되면, future가캔슬되어서 completeExceptionally, complete 둘다불가능해진다 !
            //future.cancel(true);
            String result = serve(food);
            future.complete(result);
            //System.out.println(result);
            clean();
        }
        catch(Exception e){

            future.completeExceptionally(e);

            future2 = future.handle((result,exception)->{
                    if(exception!=null){
                        System.out.println("Timeout: "+ exception);
                        return food;
                    }
                    return food;
                });

                //System.out.println(future.join()+": "+ Thread.currentThread().getName() + " 시험");
                System.out.println(future2.join()+": "+ Thread.currentThread().getName() + " | is too late to serve a food. Owner is helping and assigning cleaning to new worker: " + e);
                assignToWorker();

        }

        // 에러발생, 퓨처가 캔슬됨 System.out.println("Future1 result: "+future.join());


        System.out.println("Future 1| is done: "+ future.isDone());
        System.out.println("Future 1| is completed exceptionally: "+ future.isCompletedExceptionally());
        System.out.println("Future 1| is cancelled: "+ future.isCancelled());

        System.out.println("Future 2| is done: "+ future2.isDone());
        System.out.println("Future 2| is completed exceptionally: "+ future2.isCompletedExceptionally());
        System.out.println("Future 2| is cancelled: "+ future2.isCancelled());
    }
    public static void main(String args[]){

        //startRestaruant();


        MyService service = new MyService();
        CompletableFuture<Integer> cf = service.performTask();
        cf.thenApply(r -> {
            System.out.println(Thread.currentThread().getName() + " | Thread executing then apply: " );
            return r + 20;
            }
        );

        System.out.println("result: " + cf.join());
        System.out.println("메인 스레드 종료");
    }

    static class MyService{

        public CompletableFuture<Integer> performTask(){

            CompletableFuture<Integer> cf = new CompletableFuture<>();

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(()->{

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() +" | assigning 40 to completableFuture..");
                cf.complete(40);
            });

            executorService.shutdown();
            return cf;
        }
    }

}
