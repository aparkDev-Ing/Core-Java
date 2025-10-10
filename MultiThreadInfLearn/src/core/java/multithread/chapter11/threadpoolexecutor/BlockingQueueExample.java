package core.java.multithread.chapter11.threadpoolexecutor;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Menu {
    static List<String> menuList = Arrays.asList("Burger", "Pizza", "Taco", "Pasta");
}

public class BlockingQueueExample {

    static int chefId = 0;
    static int noOfOrders = 50;
    static int noOfBurners = 5;
    static int noOfChefs = 3;

    static BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(noOfBurners);

    public static void main(String args[]) throws InterruptedException {
        launchApp();
    }

    public static void launchApp() throws InterruptedException {
        // Producer thread (Customer)
        Thread producerThread = new Thread(() -> {
            try {
                for (int i = 0; i < noOfOrders; i++) {
                    int index = new Random().nextInt(4);
                    String order = Menu.menuList.get(index);
                    blockingQueue.put(order);  // blocks if queue is full
                    System.out.println(Thread.currentThread().getName() + " placed order: " + order);
                }

                // Send poison pills to signal chefs to stop
                for (int i = 0; i < noOfChefs; i++) {
                    blockingQueue.put("EXIT");
                }

            } catch (InterruptedException e) {
                System.out.println("Producer interrupted: " + e);
            }
        }, "Customer");

        producerThread.start();

        // Consumer threads (Chefs)
        for (int i = 0; i < noOfChefs; i++) {
            String chefName = "Chef " + chefId++;
            Thread chefThread = new Thread(() -> {
                try {
                    while (true) {
                        String menu = blockingQueue.take();  // blocks if queue is empty
                        if (menu.equals("EXIT")) {
                            System.out.println(Thread.currentThread().getName() + " is going home!");
                            break;
                        }
                        Thread.sleep(500);  // simulate cooking time
                        System.out.println(Thread.currentThread().getName() + " prepared: " + menu);
                    }
                } catch (InterruptedException e) {
                    System.out.println("Chef interrupted: " + e);
                }
            }, chefName);

            chefThread.start();
        }
    }
}
