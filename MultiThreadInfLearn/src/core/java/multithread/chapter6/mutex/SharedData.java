package core.java.multithread.chapter6.mutex;

public class SharedData {

    public SharedData(Mutex mutex) {

        this.mutex = mutex;
    }

    Mutex mutex;

    private int counter = 0;

    public void sum(){

        if(mutex!=null) {

            try {
                mutex.acquired();
                System.out.println("Thread Lock acquired! - " + Thread.currentThread().getName());

                for (int i = 0; i < 1000000; i++) {

                    this.counter++;

                }
            }
            finally {
                mutex.release();
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
