package core.java.multithread.chapter8.readwritelock;

import java.sql.SQLOutput;
import java.util.*;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {

    ThreadLocal<Boolean> threadLocalHasPunched =new ThreadLocal<>();

//    Set<UfcPlayerConstants> ufcPlayers = new HashSet<>();

    List<UfcPlayer> listOfPlayers = new ArrayList<>();

    UfcPlayer bestPlayer ;
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();


    public static void main(String args[]){

//        Set<UfcPlayerConstants> ufcPlayers = new HashSet<>();
//
//        ufcPlayers.add(UfcPlayerConstants.CONNOR);
//
//       System.out.println( ufcPlayers.contains(UfcPlayerConstants.CONNOR));

        ReadWriteLockExample readWriteLockExample = new ReadWriteLockExample();

        readWriteLockExample.playPunchingMachine();

        readWriteLockExample.commentatorAnnounce(readWriteLockExample);


    }
    public void playPunchingMachine(){

        ReadWriteLockExample readWriteLockExample = new ReadWriteLockExample();


        Runnable punchMachinePlayer = ()->{

            threadLocalHasPunched.set(false);

            while(!threadLocalHasPunched.get()){
                //System.out.println("Thread trying to acquire time out lock: "+ Thread.currentThread().getName());
//                try {
//                    if (readWriteLock.writeLock().tryLock(2,TimeUnit.SECONDS)) {
                    readWriteLock.writeLock().lock();

//                        tryLock(2,TimeUnit.SECONDS)

                        try{

//                            int index=0;
//                            for(int i =0; i<UfcRoaster.setOfPlayers.size();i++){
//
//                                if(!ufcPlayers.contains(UfcRoaster.setOfPlayers.get(i))){
//                                    ufcPlayers.add(UfcRoaster.setOfPlayers.get(i));
//                                    index = i;
//                                    break;
//                                }
//
//                            }

                            // or simply use name as index
                            int index1 = Integer.parseInt(Thread.currentThread().getName());

                            UfcPlayer ufcPlayer = new UfcPlayer(UfcRoaster.setOfPlayers.get(index1).getPlayerName(),UfcRoaster.setOfPlayers.get(index1).getWeightClass());

                            ufcPlayer.setPunchMcScore(readWriteLockExample.hitPunchingMachine());

                            listOfPlayers.add(ufcPlayer);

                            System.out.println(ufcPlayer +"acquired chance to punch and has scored:" + ufcPlayer.getPunchMcScore() +"!");

                            Thread.sleep(2000);

                        }
                        catch(InterruptedException e){
                            System.out.println("Exception occured while player puching a machine" + e);
                        }
                        finally{
                            //System.out.println(ufcPlayers);
                            threadLocalHasPunched.set(true);
                            //System.out.println("Has puched:" + threadLocalHasPunched.get() + " "+Thread.currentThread().getName());
                            //System.out.println( " unlocking write lock");
                            readWriteLock.writeLock().unlock();
                        }
//                    }
//                    else{
//                        int index1 = Integer.parseInt(Thread.currentThread().getName());
//
//                        //System.out.println(UfcRoaster.setOfPlayers.get(index1).getPlayerName() +" failed to acquire lock. Try again!");
//                    }

//                }
//                catch(InterruptedException e){
//                    System.out.println("Error occured while acquiring a lock on write lock");
//                }




            }




        };


        Thread[] ufcPlayersThreads = new Thread[UfcRoaster.setOfPlayers.size()];

        for(int i =0; i<UfcRoaster.setOfPlayers.size(); i++){

            ufcPlayersThreads[i] = new Thread(punchMachinePlayer, String.valueOf(i));

            ufcPlayersThreads[i].start();

//            try {
//                ufcPlayersThreads[i].join();
//            }
//            catch(InterruptedException e){
//
//            }

        }




    }

    public void commentatorAnnounce(ReadWriteLockExample readWriteLockExample){

        //readWriteLock.readLock().lock();
        //System.out.println("\n Commentator locking read lock!");

                Thread commentator = new Thread(readWriteLockExample::announceWinner, "Bruce Buffer");
                commentator.start();



//        finally{
//            //System.out.println("Commentator "+Thread.currentThread().getName() +" unlocking read lock!");
//            readWriteLock.readLock().unlock();
//
//        }

    }

    int hitPunchingMachine(){

        return new Random().nextInt(1000);
    }

    void announceWinner(){

         readWriteLock.readLock().lock();
//        try {

                try {
                    listOfPlayers.sort(Comparator.comparingInt(UfcPlayer::getPunchMcScore).reversed());

                    //listOfPlayers.sort((a,b)-> b.getPunchMcScore()-a.getPunchMcScore());

                    bestPlayer = listOfPlayers.get(0);
                    System.out.println("\n");
                    System.out.println("Winner of game is: " + bestPlayer + " Congrats!!");


                    System.out.println("Final result for all players! " + listOfPlayers);
                }
                finally {
                    readWriteLock.readLock().unlock();
                }



//        catch(InterruptedException e){
//            System.out.println("Error occured while annoucing winner: "+ e);
//        }
    }



}



class UfcPlayer{

    String name;

    public UfcPlayer(){

    }
    public UfcPlayer(String name, String weightClass) {
        this.name = name;
        this.weightClass = weightClass;
    }

    String weightClass;

    int punchMcScore;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeightClass() {
        return weightClass;
    }

    public void setWeightClass(String weightClass) {
        this.weightClass = weightClass;
    }

    public int getPunchMcScore() {
        return punchMcScore;
    }

    public void setPunchMcScore(int punchMcScore) {
        this.punchMcScore = punchMcScore;
    }

    @Override
    public String toString() {
        return "UfcPlayer{" +
                "name='" + name + '\'' +
                ", weightClass='" + weightClass + '\'' +
                ", punchMcScore=" + punchMcScore +
                '}';
    }
}

class UfcRoaster{

    static List<UfcPlayerConstants> setOfPlayers = Arrays.asList(UfcPlayerConstants.JON, UfcPlayerConstants.CONNOR, UfcPlayerConstants.MAX, UfcPlayerConstants.TOM, UfcPlayerConstants.VOLKANOVSKI);

}

enum UfcPlayerConstants{

    JON("Jon Jones", "Heavy"),
    CONNOR("Connor Mcgregor","Light"),
    VOLKANOVSKI("Alexandar Volkanovski","Feather"),
    MAX("Max Holloway","Light"),
    TOM("Tom Aspinall","Heavy");



    private UfcPlayerConstants(String name, String weightClass){

        this.playerName = name;
        this.weightClass=weightClass;
    }


    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    String playerName;

    public String getWeightClass() {
        return weightClass;
    }

    public void setWeightClass(String weightClass) {
        this.weightClass = weightClass;
    }

    String weightClass;

    @Override
    public String toString() {
        return "UfcPlayer{" +
                "playerName='" + playerName + '\'' +
                ", weightClass='" + weightClass + '\'' +
                '}';
    }


}