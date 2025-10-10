package ioexamples.ufcplayer.impl;

import ioexamples.ufcplayer.UFCPlayer;
import ioexamples.ufcplayer.UFCPlayerRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataStreamUfcPlayerRepository implements UFCPlayerRepository {
    @Override
    public void addPlayer(UFCPlayer ufcPlayer){
       try(DataOutputStream dos = new DataOutputStream(new FileOutputStream("IOInflearn/temp/UFC player roaster datastream.txt",true))){
           dos.writeUTF(ufcPlayer.name);
           dos.writeUTF(ufcPlayer.weightClass);
           dos.writeUTF(ufcPlayer.playerId.toString());
       }catch(IOException e){
           throw new RuntimeException(e);
       }
    }

    @Override
    public List<UFCPlayer> listPlayers(){

        List<UFCPlayer> ufcPlayerList = new ArrayList<>();
        try(DataInputStream dis = new DataInputStream(new FileInputStream("IOInflearn/temp/UFC player roaster datastream.txt"))){

            while(dis.available()>0){
                UFCPlayer ufcPlayer = new UFCPlayer(dis.readUTF(), dis.readUTF());
                ufcPlayer.playerId = UUID.fromString(dis.readUTF());
                ufcPlayerList.add(ufcPlayer);
            }
        }
        catch(FileNotFoundException e){
            return new ArrayList<>();
            //return List.of();
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }

        return ufcPlayerList;
    }
}
