package ioexamples.ufcplayer.impl;

import ioexamples.ufcplayer.UFCPlayer;
import ioexamples.ufcplayer.UFCPlayerRepository;

import java.util.ArrayList;
import java.util.List;

public class MemoryUFCPlayerRepository implements UFCPlayerRepository {

    List<UFCPlayer> ufcPlayers = new ArrayList<>();

    @Override
    public void addPlayer(UFCPlayer ufcPlayer){
        ufcPlayers.add(ufcPlayer);
    }

    @Override
    public List<UFCPlayer> listPlayers(){

        return this.ufcPlayers;
    };

}
