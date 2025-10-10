package core.java.multithread.chapter6.spinandlock;

public class SpinAndLockExample {



    public static void main(String args[]) throws InterruptedException{

        Burner burner = new Burner();

        Runnable runnable = new Cooker(burner);

        Thread thread1 = new Thread(runnable, "Thread1");

        Thread thread2 = new Thread(runnable, "Thread2");

        Thread thread3 = new Thread(runnable, "Thread3");


        thread1.start();
        thread2.start();
        thread3.start();


        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("All cooking completed!");
    }

}

class Cooker implements Runnable{

    Burner burner;

    public Cooker(Burner burner){
        this.burner = burner;
    }

    @Override
    public void run(){

        if(burner != null){

            while(!burner.turnBurnerOn()){

                System.out.println("Thread "+ Thread.currentThread().getName() +" Could not turn the burner on as it's already turned on. Busy waiting!");
            }

            try {
                burner.cook();
            }
            finally {
                burner.turnBurnerOff();
            }


        }else{
            System.out.println("Burner cannot be null!");
        }


        System.out.println("Thread ["+ Thread.currentThread().getName() +"] terminating!");
    }
}