package com.aaron.basic.java.thread;

public class StaticSyncDemo {



}

class ThreadExecutor{

    public static void main(String args[]){

//        Runnable r = new ThreadRunner();
//
//        Runnable r2 = new ThreadRunner2();

//        Runnable r3 = new ThreadRunner3();
//
        Order a = new Order(10);

        a.setName("Aaron");

        Runnable r4 = new ThreadRunner4(a);

        Runnable r5 = new ThreadRunner5(a);

//        new Thread(r).start();
//
//        new Thread(r2).start();


//        new Thread(r3).start();

        new Thread(r4).start();

        new Thread(r5).start();

    }
}

class Order {

    public Order(int noOfOrders){
        this.noOfOrders = noOfOrders;
        this.initialOrderNumber = noOfOrders;
    }

    public int getInitialOrderNumber() {
        return initialOrderNumber;
    }

    public void setInitialOrderNumber(int initialOrderNumber) {
        this.initialOrderNumber = initialOrderNumber;
    }

    private int initialOrderNumber;
    public String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String name;

    public synchronized static int getOrderCounter() {
        return orderCounter;
    }

    public static void setOrderCounter(int orderCounter) {
        Order.orderCounter = orderCounter;
    }

    public static int orderCounter = 0;

    public synchronized static int getNoOfOrders() {
        return noOfOrders;
    }

    public void setNoOfOrders(int noOfOrders) {
        this.noOfOrders = noOfOrders;
    }

    public static int noOfOrders = 10;

    public static int doubleCouner = 0;

    public synchronized static void handleOrder(){

            System.out.println("Total Number Of Orders by customer: " + Order.getNoOfOrders());
            while (Order.getNoOfOrders() > 0) {
//                try {
//                    Thread.sleep(100);
                    //System.out.println("No Of Orders Left: " + this.getNoOfOrders());
                    noOfOrders--;
                    System.out.println("No Of Orders Left: " + getNoOfOrders());
//            System.out.println("Current Fulfilled number of order: "+ (this.getInitialOrderNumber() - this.noOfOrders));
//                } catch (InterruptedException E) {
//                    Thread.currentThread().interrupt();
//                }
            }


    }

    public synchronized static void countOrder(){

        System.out.println("Order Count Starts : "+ Order.getOrderCounter());

        while(orderCounter < 10) {
//            try {
//                Thread.sleep(1000);
                //System.out.println("No Of Orders Left: " + this.getNoOfOrders());
                orderCounter++;
                System.out.println("No Of Orders Made: " + orderCounter);
//            System.out.println("Current Fulfilled number of order: "+ (this.getInitialOrderNumber() - this.noOfOrders));
//            } catch (InterruptedException E) {
//                Thread.currentThread().interrupt();
//            }
        }

    }

    public synchronized static void doubleCountOrder(){

        System.out.println("Double Order Count Starts : "+ doubleCouner);

        while(doubleCouner < 20) {
//            try {
//                Thread.sleep(1000);
            //System.out.println("No Of Orders Left: " + this.getNoOfOrders());
            doubleCouner += 2;
            System.out.println("No Of double Orders Made: " + doubleCouner);
//            System.out.println("Current Fulfilled number of order: "+ (this.getInitialOrderNumber() - this.noOfOrders));
//            } catch (InterruptedException E) {
//                Thread.currentThread().interrupt();
//            }
        }

    }

    public synchronized void printNames(){

        System.out.println("Printing names ");


        for(int i =0 ; i < 20 ; i++){

            System.out.println(Thread.currentThread().getName() +" Name: " + name);
        }


    }








}

class ThreadRunner implements Runnable{

//    Thread th;
//
//    public ThreadRunner(Thread thread){
//
//        this.th = thread;
//        th.start();
//
//    }


    @Override
    public void run(){

            Order.handleOrder();

    }


}


class ThreadRunner2 implements Runnable{

//    Thread th;
//
//    public ThreadRunner(Thread thread){
//
//        this.th = thread;
//        th.start();
//
//    }


    @Override
    public void run(){


            Order.countOrder();

    }


}

class ThreadRunner3 implements Runnable{

//    Thread th;
//
//    public ThreadRunner(Thread thread){
//
//        this.th = thread;
//        th.start();
//
//    }


    @Override
    public void run(){


        Order.doubleCountOrder();

    }


}

class ThreadRunner4 implements Runnable{

    Order a ;

    public ThreadRunner4(Order a){
        this.a =a ;
    }
//    Thread th;
//
//    public ThreadRunner(Thread thread){
//
//        this.th = thread;
//        th.start();
//
//    }

//    Order a = new Order(10);



    @Override
    public void run(){

        System.out.println ("Object id: "+ a);
//        a.setName("Aaron");

        a.printNames();

    }


}

class ThreadRunner5 implements Runnable{

    Order a;
    public ThreadRunner5(Order a){
        this.a =a ;
    }
//    Thread th;
//
//    public ThreadRunner(Thread thread){
//
//        this.th = thread;
//        th.start();
//
//    }

//    Order a = new Order(10);


    @Override
    public void run(){

        System.out.println ("Object id: "+ a);

//        a.setName("James");

        a.printNames();

    }


}