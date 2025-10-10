package com.aaron.basic.java.thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RestaurantSimulator {


    public static void main(String args[]){

        Table table =new Table();
        new Thread(new Chef("Aaron","5",table)).start();

        Thread tr1 = new Thread(new Customer("John",table),"John");
        Thread tr2 = new Thread(new Customer("David",table),"David");

        tr1.setDaemon(true);
        tr2.setDaemon(true);

        tr1.start();
        tr2.start();

        //new Thread(new Customer("Karen",table));

//        try{
//             Thread.sleep(2000);
//        }
//        catch(InterruptedException e){
//            Thread.currentThread().interrupt();
//        }
    }


}


class Chef implements Runnable{

    @Override
    public void run(){

        while(isIngredientAvaliable()){

            noOfOrders++;

            Random rand = new Random();
//            this.table.add(new Order1(Menu.menuList.get((int) Math.random() * 2 + 1 )));

            try {
                Thread.sleep(1000);
                if (this.table.add(new Order1(Menu.menuList.get(rand.nextInt(3))))) {
                    noOfOrders++;
                } else {
                    System.out.println("Failed to add food to the table. ");
                }
            }
            catch(InterruptedException e){
                System.out.println("Thread interrupted "+ e);
                Thread.currentThread().interrupt();
            }
        }


    }

    int noOfOrders ;

    String name;

    String noOfStars;

    Table table;

    public Chef(String name, String noOfStars, Table table){

        this.noOfStars = noOfStars;
        this.name = name;
        this.table=table;
    }

    public boolean isIngredientAvaliable(){

        if(noOfOrders > 19){
            System.out.println("\n "+ noOfOrders+ " Orders already fulfilled | " + "Left over food on table "+ this.table.listOfOrdersOnTable + " Closing a restuarnt today. Bye");
            return false;
        }
        return true;
    }

}

class Customer implements Runnable{

    @Override
    public void run(){

        while(true){

            Random rand = new Random();
            try {
                Thread.sleep(2000);
                table.remove(Menu.menuList.get(rand.nextInt(3)));
            }
            catch(InterruptedException e){
                System.out.println("Thread interrupted "+ e);
                Thread.currentThread().interrupt();
            }
        }

    }


//     Order1 order;

     String name;

    Table table;

    public Customer( String name, Table table){
//        this.order = order;
        this.name = name;
        this.table=table;
    }




}

class Order1{

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return "Order{" +
                "food='" + food + '\'' +
                '}';
    }

    String food;

    public Order1(String food){
        this.food = food;
    }



}

class Menu{
    public static final List<String> menuList = Arrays.asList("Pizza", "Burger", "Taco");


}

class Table{

    public List<Order1> listOfOrdersOnTable = new ArrayList<>() ;

    public final int noOfSizeOnTable = 5;

    public boolean add(Order1 order){

        synchronized (this){

            if(listOfOrdersOnTable.size() < noOfSizeOnTable && Menu.menuList.contains(order.getFood()) ){

                System.out.println("Order "+ order +" is added to the table. Notifying customer to start the food. Enjoy");
                listOfOrdersOnTable.add(order);
                notify();
                return true;

            }else if(listOfOrdersOnTable.size() < noOfSizeOnTable && !Menu.menuList.contains(order.getFood())){

                System.out.println(order.getFood()+" isn't part of menu. Please pick a food and order from the menu. Chef waiting customer to order valid food. ");
                try {
                    wait();
                }
                catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                }

                return false;

            }else{

                System.out.println("You already have " + listOfOrdersOnTable.size() + " on the table. Please order little later. Chef waiting to cook new food");
                try {
                    wait();
                   notify();
                }
                catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                }

                return false;
            }
        }

    }

    public boolean remove(String food){

        synchronized (this){

//            if(listOfOrdersOnTable.size() >0 && listOfOrdersOnTable.contains(order) ){

            if(listOfOrdersOnTable.size() >0  ) {

                int indexOfOrder = 0;
                for(Order1 order : listOfOrdersOnTable){

                    if(order.getFood().equals(food)){
                        indexOfOrder = listOfOrdersOnTable.indexOf(order);
                        break;
                    }
                }

                if(indexOfOrder >= 0){
                    System.out.println(Thread.currentThread().getName()+ " Customer finished a food: " + listOfOrdersOnTable.get(indexOfOrder) + " notifiying chef to prepare new food.");
                    listOfOrdersOnTable.remove(listOfOrdersOnTable.get(indexOfOrder));
                    notify();
                    return true;
                }
                else{
                    try {
                        System.out.println(food + " isn't ready yet. Customer waiting on food: ");
                        wait();
                    }
                    catch(InterruptedException e){

                        Thread.currentThread().interrupt();
                    }

                    return false;
                }
            }
//            }
            else{
                try {
                    System.out.println("No food avaliable on table as of now. Customer waiting on food: " );
                    wait();
                }
                catch(InterruptedException e){

                    Thread.currentThread().interrupt();
                }
            }

        }
        return false;
    }


}




