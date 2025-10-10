package ioexamples.ufcplayer.impl;

import efficientfilestream.BufferConstants;
import ioexamples.ufcplayer.UFCPlayer;
import ioexamples.ufcplayer.UFCPlayerRepository;
import ioexamples.ufcplayer.common.utility.UFCPlayerService;
import ioexamples.ufcplayer.common.utility.UFCPlayerServiceImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileUFCPlayerRepository implements UFCPlayerRepository {

    final static String DELIMETER =",";
    @Override
    public void addPlayer(UFCPlayer ufcPlayer){

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("IOInflearn/temp/UFC Players Roaster.txt", BufferConstants.UTF_8,true))){
            String info = ufcPlayer.name + DELIMETER + ufcPlayer.playerId + DELIMETER + ufcPlayer.weightClass;
            bw.write(info);
            bw.newLine();
//            bw.close();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        //FileWriter fw = new FileWriter("IOInflearn/temp/UFC Players Roaster.txt", BufferConstants.UTF_8);

    }

    @Override
    public List<UFCPlayer> listPlayers(){

        List<UFCPlayer> ufcPlayers = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader("IOInflearn/temp/UFC Players Roaster.txt", BufferConstants.UTF_8))){

            String data  ;
            while((data = br.readLine()) !=null){
                UFCPlayer ufcPlayer = new UFCPlayer(data.split(DELIMETER)[0],data.split(DELIMETER)[2]);
                ufcPlayer.playerId =  UUID.fromString(data.split(DELIMETER)[1]);
                ufcPlayers.add(ufcPlayer);
            }

        }
        catch(FileNotFoundException e){
            return new ArrayList<>();
        }catch(IOException e){
            throw new RuntimeException(e);
        }

        return ufcPlayers;
    }
}
