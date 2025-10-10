package core.java.multithread.chapter12.forkjoinpool;

import java.sql.Array;
import java.util.*;
import java.util.concurrent.*;

enum Menu{

   BURGER("Burger",10),
    PIZZA("Pizza",20),
    TACO("Taco",3);

    Menu(String menu,int price){
       this.menu = menu;
       this.price= price;
   }
   public String menu;

    public int price;

    public String getMenu(){

        return this.menu;
    }

    public int getPrice(){

        return this.price;
    }

}
public class CustomForkJoinPoolExample {

    static List<String> workerList = Arrays.asList("Aaron","Jaeyoung","Hongsun");

    static List<Menu> menuList = Arrays.asList(Menu.BURGER,Menu.PIZZA,Menu.TACO);

    //static int coreSize = Runtime.getRuntime().availableProcessors()-1;
    static int coreSize = 3;
    static int noOfOrders = 3;
    static List<Order> orderList = new ArrayList<>();

    static BlockingQueue<Order> blockingQueue = new LinkedBlockingQueue<>();
    static final String customerName = "Aaron";

    public static void main(String args[]) throws InterruptedException{

        //runRestaurant();

        computeTotalIncomeToday();

    }

    static void runRestaurant()throws InterruptedException{

        Thread th1 = new Thread(()->{
            placeOrder();
        },customerName);


        th1.start();

        List<CompletableFuture<Void>> cfList = new ArrayList<>();

        for(int i =0; i<coreSize; i++) {

            int j = i;
             CompletableFuture<Void> cf1 = CompletableFuture.runAsync(() -> {

                 PartTimeWorker partTimeWorker = new PartTimeWorker("Worker "+ j);
                 partTimeWorker.prepareOrder();
                 //throw new RuntimeException("Test Exceptions");

            }).handle((r,e)->{

                if(e!=null){
                    System.out.println("Error occured while worker preparing an order: "+e );
                }
                return null;
             });

             cfList.add(cf1);
        }

        CompletableFuture<Void> cfAll=  CompletableFuture.allOf(cfList.toArray(new CompletableFuture[0]));

        cfAll.join();

        System.out.println("");
        System.out.println(customerName+ " has finished placing "+noOfOrders +" orders. " +orderList);
        System.out.println("");

        //System.out.println("Total Order Size: "+ orderList.size());

        int total = orderList.stream().map(r-> r.price).mapToInt(Integer::intValue).sum();

        System.out.println("Total Price computed through lambda: "+total);



    }

    static void computeTotalIncomeToday() throws InterruptedException{

        System.out.println("Workers will take a rest for 1 second. Then start compute today's sales!");
        Thread.sleep(1000);

        orderList.add(new Order(5,"Aaron","Completed","Taco"));

        orderList.add(new Order(10,"Aaron","Completed","Pizza"));

        orderList.add(new Order(15,"Aaron","Completed","Pasta"));

        CustomRecursiveTask task = new CustomRecursiveTask(0,orderList.size()-1,orderList);

        ForkJoinPool forkJoinPool = new ForkJoinPool(coreSize);

        int totalSales = forkJoinPool.invoke(task);

        //ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(task);

        forkJoinPool.shutdown();

        //System.out.println("Today's total sales: "+ task.compute());

        //System.out.println("Today's total sales: "+ forkJoinTask.join());

        System.out.println("Today's total sales: "+ totalSales);

    }

    static void placeOrder(){

        for(int i=0; i<noOfOrders;i++){

            int index = new Random().nextInt(3);

            Menu menu = CustomForkJoinPoolExample.menuList.get(index);

            Order order = new Order(menu.price, customerName,"In Progress",menu.menu);

            try {
                blockingQueue.put(order);
                System.out.println(Thread.currentThread().getName() +" | has placed an order: "+ order.orderName);
            }
            catch(Exception e){
                System.out.println("Error occured while placing an order: "+ e);
            }

        }

        for (int i = 0; i < coreSize; i++) {
            try {
                blockingQueue.put(new Order());
            }
            catch(Exception e){
                System.out.println("Error occured while placing an order: "+ e);
            }
        }
    }



}

class Order{

    public Order(){

    }

    public Order(int price, String customerName, String orderStatus, String orderName) {

        this.orderId = UUID.randomUUID();
        this.price = price;
        this.customerName = customerName;
        this.orderStatus = orderStatus;
        this.orderName = orderName;
    }

    UUID orderId ;

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", price=" + price +
                ", customerName='" + customerName + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderName='" + orderName + '\'' +
                '}';
        //return "Price: "+ price;
    }

    int price;

    String customerName;

    String orderStatus;

    String orderName;


}

class PartTimeWorker{

    public PartTimeWorker(String workerName){
        this.workerName = workerName;
    }
    String workerName;

    //List<Order> listOfOrder = new ArrayList<>();

    void prepareOrder(){

        while(true) {
            try {
                Order order = CustomForkJoinPoolExample.blockingQueue.take();

                if(order.orderId == null){
                    System.out.println(this.workerName + " is completed with its work today for completing customer order!");
                    break;
                }

                System.out.println(this.workerName + " is preparing for a order: " + order.orderName + " | " + order.orderId);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(e);
                }
                order.orderStatus = "Completed";

                //listOfOrder.add(order);

                synchronized (CustomForkJoinPoolExample.orderList) {
                    CustomForkJoinPoolExample.orderList.add(order);
                }

                System.out.println(this.workerName + " has fulfilled an order: " + order.orderName + " | " + order.orderId);
            } catch (Exception e) {
                System.out.println(Thread.currentThread().getName() + " | Error occured while preparing an order: " + e);
            }
        }
    }



}