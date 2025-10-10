package core.java.multithread.chapter9.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {

    public static void main(String args[]){

        int noOfHideThreads = 5;
        CountDownLatch countDownLatch =new CountDownLatch(noOfHideThreads);


        Thread thread[] = new Thread[noOfHideThreads];

//        for(int i = 0; i<noOfHideThreads;i++){
//
//            thread[i] = new Thread();
//        }


        Thread aaron = new Thread(new Hider("Aaron",countDownLatch),"Aaron");
        Thread david = new Thread(new Hider("David",countDownLatch),"David");
        Thread james = new Thread(new Hider("James",countDownLatch),"James");
        Thread kevin = new Thread(new Hider("Kevin",countDownLatch),"Kevin");
        Thread jim = new Thread(new Hider("Jim",countDownLatch),"Jim");


        Thread seekerJohn = new Thread(new Seeker("John",countDownLatch),"John");

        seekerJohn.start();

        aaron.start();
        david.start();
        james.start();
        kevin.start();
        jim.start();

        try{
            seekerJohn.join();

            aaron.join();
            david.join();
            james.join();
            kevin.join();
            jim.join();
        }catch(Exception e){

        }

        System.out.println("Hide and seek completed!");


    }



}


class Hider implements  Runnable{


    public Hider(String name, CountDownLatch countDownLatch){

        this.name = name;
        this.countDownLatch=countDownLatch;
    }
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    CountDownLatch countDownLatch;

    @Override
    public void run(){

        hide();

    }

    void hide(){

        try{
            System.out.println("Hider "+ this.getName()+ " has hidden from the building!"  );

            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            System.out.println("Error occured while hiding "+ e);
        }
        finally{
            //System.out.println( this.getName()+ " Decreasing a count!");
            this.countDownLatch.countDown();
        }

    }

    @Override
    public String toString() {
        return "Hider{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Seeker implements  Runnable{

    public Seeker(String name, CountDownLatch countDownLatch){

        this.name = name;
        this.countDownLatch=countDownLatch;
    }
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    CountDownLatch countDownLatch;

    @Override
    public void run(){

        seek();

    }

    void seek(){

        try {
            System.out.println("Seeker " + this.getName() + " has to wait until all hiders hide!");
            this.countDownLatch.await();
            System.out.println("Seeker " + this.getName() + " is finally able to start now!");
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            System.out.println("Seeker thread interrupted while awaiting "+e);
        }

    }

    @Override
    public String toString() {
        return "Seeker{" +
                "name='" + name + '\'' +
                '}';
    }
}