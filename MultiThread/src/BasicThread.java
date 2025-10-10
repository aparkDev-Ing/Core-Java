
public class BasicThread {

    static long startTime = 0L;

    public static void main(String args[]){

        ThreadEX1 th1 = new ThreadEX1();
        th1.setName("Thread-1");

//        Runnable r = new ThreadEx2();
        Thread th2 = new Thread(new ThreadEx2());
        th2.setName("Thread-2");

        th1.start();
        th2.start();

        Thread th3 = new Thread(new Runnable() {
            @Override
            public void run() {

                for(int i = 0; i < 300; i++){

                    try{
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + " "+ new String("/"));
                    }catch(InterruptedException e){


                        System.out.println("Thread interrupted while sleeping for " + Thread.currentThread().getName() + " "+ e);
                        Thread.currentThread().interrupt();
                        System.out.println("Is Thread Interrupted? "+ Thread.currentThread().isInterrupted());

                        //throw new RuntimeException(e);

                    }


                }
            }
        });

        th3.setName("Thread-3");

        th3.start();

        startTime = System.currentTimeMillis();


        //th1.interrupt();
        //th2.interrupt();
        //th3.interrupt();


//        try{
//            th1.join();
//            th2.join();
//            th3.join();
//        }catch(InterruptedException e){
//            System.out.println("Exception occured while join "+ e.getMessage());
//        }
//
//
//
//        System.out.println("Time it took: "+  (System.currentTimeMillis() - startTime) );


    }



}

class ThreadEX1 extends Thread{

    @Override
    public void run(){

        for(int i = 0; i < 300; i++){

            try{
                Thread.sleep(1000);
                System.out.println(this.getName()+ " "+ new String("-"));
            }catch(InterruptedException e){
                System.out.println("Thread interrupted while sleeping for " + this.getName() + " "+ e);
                Thread.currentThread().interrupt();
                System.out.println("Is Thread Interrupted? "+ this.isInterrupted());

                //throw new RuntimeException(e);
            }


        }

    }

}

class ThreadEx2 implements Runnable {

    @Override
    public void run() {

        for (int i = 0; i < 300; i++) {

            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " " + new String("|"));
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted while sleeping for " + Thread.currentThread().getName() + " " + e);
                Thread.currentThread().interrupt();
                System.out.println("Is Thread Interrupted? "+ Thread.currentThread().isInterrupted());

                //throw new RuntimeException(e);

            }



        }
    }
}
