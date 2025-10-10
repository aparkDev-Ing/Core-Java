package ioexamples.ufcplayer.serilization;

import ioexamples.ufcplayer.UFCPlayer;

import java.io.*;
import java.util.UUID;

public class SerlizationExample {

    public static void main(String args[]){

        //serialize();
        UFCPlayer ufcPlayer = deSerialize();

        System.out.println(ufcPlayer);
    }

    static void serialize(){

        UFCPlayer ufcPlayer = new UFCPlayer("Connor Mcgregor","170");
        ufcPlayer.playerId= UUID.fromString("71789aeb-8984-4f9b-93af-bacd754cd586");
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("IOInflearn/temp/test serilization.dat"))){
            oos.writeObject(ufcPlayer);
        }catch(IOException e){
            System.out.println(e);
        }
    }

    static UFCPlayer deSerialize(){

        UFCPlayer ufcPlayer = new UFCPlayer();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("IOInflearn/temp/test serilization.dat"))){
            ufcPlayer= (UFCPlayer) ois.readObject();

        }catch(IOException |ClassNotFoundException e){
            System.out.println("Error occured and failed to deserilize");
            throw new RuntimeException(e);
        }

        return ufcPlayer;
    }
}
