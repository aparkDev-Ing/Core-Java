package ioexamples.ufcplayer.common.utility;

import ioexamples.ufcplayer.UFCPlayer;

import java.util.Scanner;


public interface UFCPlayerService {

    public int promptForUser(Scanner sc);
    public void displayPlayers();

    public void registerPlayer(Scanner sc);
}
