package core.java.multithread.chapter10.executorservice;

import java.sql.SQLOutput;
import java.util.*;
import java.util.concurrent.*;


class Menu{

    static List<String> menuList = Arrays.asList("CheeseBurger","Pasta","Taco","Pizza");

}


public class InvokeAllAnyExample {

    static BlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
    static int noOfCustomers= 5;
    static int noOfRestuarnt= 2;
    static int maxNoOfOrderPerCustomer = 3;

    static boolean isRestaurntClosed = false;



    public static void main(String args[]){

        try {
            launchApp();
        }
        catch(Exception e){
            System.out.println("Exception occured "+ e);
        }
    }

    public static void launchApp() throws InterruptedException, ExecutionException{

        ExecutorService executorService =  Executors.newFixedThreadPool(5);

        ExecutorService executorService2 =  Executors.newFixedThreadPool(2);


        List<Callable<Customer>> customerCallableList = new ArrayList<>();
        List<Callable<List<Order>>> restaurantCallableList = new ArrayList<>();

        for(int i = 0; i<noOfCustomers; i++){
            Customer customer = new Customer("Customer " + i);
            //customerList.add(customer);

            customerCallableList.add(()->{

                int noOfOrders = new Random().nextInt(1,maxNoOfOrderPerCustomer+1);
                for(int j=0; j<noOfOrders;j++) {
                    customer.placeOrder();
                }
                return customer;
            });
        }
        for(int i = 0; i<noOfRestuarnt; i++){
            Restaurant restaurant = new Restaurant("Restaurant " + i);
            //restaurantList.add(restaurant);
            restaurantCallableList.add(()->{

                while(!InvokeAllAnyExample.orderQueue.isEmpty() ){
                    restaurant.cook();
                }
                return restaurant.orderList;
            });
        }



//    //invoke all
//        List<Future<Customer>> futureList = executorService.invokeAll(customerCallableList);
//
//        executorService.awaitTermination(2,TimeUnit.SECONDS);
//
//        List<Future<List<Order>>> futureList2 = executorService2.invokeAll(restaurantCallableList);
//
//
//        for(Future<Customer> future: futureList){
//
//            Customer customer = future.get();
//
//            System.out.println("List of orders placed by "+ customer.name +" : "+ customer.orderList);
//
//        }

        //invoke any
        //Customer customer = executorService.invokeAny(customerCallableList);

        //executorService.awaitTermination(2,TimeUnit.SECONDS);

        //System.out.println("손님 "+customer+ " 의 주문이 완료됫습니다");

        //List<Order> order = executorService2.invokeAny(restaurantCallableList);

        //System.out.println("주문 :" + order);


           //ystem.out.println("List of orders placed by " + customer.name + " : " + customer.orderList);



        executorService.shutdown();
        executorService2.shutdown();




    }



}


class Customer{

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", orderList=" + orderList +
                '}';
    }

    String name;

    public Customer(String name) {
        this.name = name;

    }

    List<Order> orderList = new ArrayList<>();
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }


    public void placeOrder (){



            Order order = new Order();

            int index = new Random().nextInt(4);
            //System.out.println(index);
            order.orderName = Menu.menuList.get(index);

            try {
                Thread.sleep(500);
                InvokeAllAnyExample.orderQueue.put(order);
            } catch (InterruptedException e) {

            }


            order.orderStatus = "In Progress";

            order.customerName = this.name;

            System.out.println(this.name + " has placed an order! " + order.orderName + " Thread Name: " + Thread.currentThread().getName());

            this.orderList.add(order);


    }


}

class Restaurant{

    public Restaurant(String name){
        this.name = name;
    }

    String name;

    List<Order> orderList = new ArrayList<>();

    void cook() throws InterruptedException{


        try {
            System.out.println("Restaurant " + this.name + " has started cooking");

            Thread.sleep(500); // Optional delay

            Order order = InvokeAllAnyExample.orderQueue.take(); // This will throw InterruptedException if interrupted

            order.orderStatus = "Completed";

            System.out.println("Order: " + order + " has been completed! Enjoy");

            orderList.add(order);

        } catch (InterruptedException e) {
            System.out.println("Restaurant " + name + " interrupted. Shutting down.");
            throw e;  // Rethrow to break out of loop
        }
        }

}

class Order{

    @Override
    public String toString() {
        return "Order{" +
                "orderStatus='" + orderStatus + '\'' +
                ", orderId='" + orderId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", orderName='" + orderName + '\'' +
                '}';
    }

    String orderStatus;
    String orderId;
    String customerName;



    String orderName;


}