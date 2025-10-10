package core.java.multithread.chapter6.spinandlock;

import java.util.concurrent.atomic.AtomicBoolean;

public class Burner {

    public AtomicBoolean isBurnerOn = new AtomicBoolean(false);

    public boolean turnBurnerOn(){

        return isBurnerOn.compareAndSet(false, true);

    }

    public void turnBurnerOff(){

        System.out.println("Cooking finished! Now turning burner off! " +  " Thread [" + Thread.currentThread().getName()+"] " );

        this.isBurnerOn.set(false);


    }

    public void cook(){

        System.out.println("Burner is on now! "+isBurnerOn +" Cooking in progress... Thread ["+ Thread.currentThread().getName()+ "] acquired burner " );

    }

}
