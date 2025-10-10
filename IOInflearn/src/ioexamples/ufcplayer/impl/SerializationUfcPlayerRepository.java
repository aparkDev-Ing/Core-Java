package ioexamples.ufcplayer.impl;

import ioexamples.ufcplayer.UFCPlayer;
import ioexamples.ufcplayer.UFCPlayerRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializationUfcPlayerRepository implements UFCPlayerRepository {
    @Override
    public void addPlayer(UFCPlayer ufcPlayer){

        List<UFCPlayer> ufcPlayerList = listPlayers();
        ufcPlayerList.add(ufcPlayer);
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("IOInflearn/temp/UFC Player Roaster serlizaed.dat"))){
            oos.writeObject(ufcPlayerList);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UFCPlayer> listPlayers(){

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("IOInflearn/temp/UFC Player Roaster serlizaed.dat"))){
            return (List<UFCPlayer>) ois.readObject();
        }
        catch(IOException  e){
            return new ArrayList<>();
        }
        catch( ClassNotFoundException e){
            throw new RuntimeException(e);
        }

    };
}
