package core.java.multithread.chapter6.semaphore;

import core.java.multithread.chapter6.mutex.Mutex;

public class SharedResource {

    public SharedResource(CountingSemaphore countingSemaphore) {

        this.countingSemaphore = countingSemaphore;
    }

    CountingSemaphore countingSemaphore;

    private int counter = 0;

    public void sum(){

        if(countingSemaphore!=null) {

            try {
                countingSemaphore.acquired();
                System.out.println("Thread Lock acquired! - " + Thread.currentThread().getName());

                for (int i = 0; i < 100000; i++) {

                    this.counter++;

                }
            }
            finally {
                countingSemaphore.release();
                System.out.println("Thread Lock released! - " + Thread.currentThread().getName());
            }

        }else{
            System.out.println("Mutex cannot be null!");
        }
    }

    public int getCounter(){
        return this.counter;
    }
}
