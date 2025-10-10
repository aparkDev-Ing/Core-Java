package ioexamples.ufcplayer;

import java.util.List;

public interface UFCPlayerRepository {

    public void addPlayer(UFCPlayer ufcPlayer);

    public List<UFCPlayer> listPlayers();
}
