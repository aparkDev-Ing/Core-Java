package core.java.multithread.chapter7.waitandnotify;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerExample {

    public static void main(String args[]) throws InterruptedException{

        SharedQueue sharedqueue = new SharedQueue();

        Thread thread1 = new Thread(()->{

            for(int i=0; i< 10; i++) {
               try {
                   sharedqueue.produce(i);
               }
               catch(Exception e){

               }
           }
        });

        Thread thread2 = new Thread(()->{

            for(int i=0; i< 10; i++) {
                try {
                    sharedqueue.consume();
                } catch (Exception e) {

                }
            }
        });


        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Queue status: "+ sharedqueue.queue);
    }


}

class SharedQueue{

    final int CAPACITY = 5;

    Object lock = new Object();

    public Queue<Integer> queue =  new LinkedList<>();

    public void produce(int item) throws InterruptedException {

        synchronized (lock) {
            while (queue.size() == CAPACITY) {
                System.out.println("Queue size is at its limit! " + queue.size() + " Thread will enter waiting pool");

                   lock.wait();

            }

            queue.add(item);
            System.out.println("Item added to the queue successfully! "+item);
            lock.notifyAll();
        }

    }

    public void consume() throws InterruptedException {

        synchronized (lock) {
            while (queue.isEmpty()) {
                System.out.println("Queue is Empty! " + queue.size() + " Thread will enter waiting pool");

                lock.wait();

            }

            int head = queue.poll();
            System.out.println("Item consumed from the queue successfully! "+head);
            lock.notifyAll();
        }

    }

}
