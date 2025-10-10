package ioexamples.ufcplayer.common.utility;

import ioexamples.ufcplayer.UFCPlayer;
import ioexamples.ufcplayer.impl.DataStreamUfcPlayerRepository;
import ioexamples.ufcplayer.impl.FileUFCPlayerRepository;
import ioexamples.ufcplayer.impl.MemoryUFCPlayerRepository;
import ioexamples.ufcplayer.impl.SerializationUfcPlayerRepository;

import java.util.List;
import java.util.Scanner;


public class UFCPlayerServiceImpl implements UFCPlayerService {

    //public static SerializationUfcPlayerRepository ufcRepository = new SerializationUfcPlayerRepository();
    //public static DataStreamUfcPlayerRepository ufcRepository = new DataStreamUfcPlayerRepository();
    public static FileUFCPlayerRepository ufcRepository = new FileUFCPlayerRepository();
    //public static MemoryUFCPlayerRepository ufcRepository = new MemoryUFCPlayerRepository();

    public int promptForUser(Scanner sc){

        System.out.println("1. Register a UFC Player | 2. Display current roaster | 3. Exit a Progarm");
        int choice = sc.nextInt();
        sc.nextLine();

        return choice;
    }
    public void displayPlayers(){

        if(ufcRepository.listPlayers() != null && ufcRepository.listPlayers().isEmpty() ){
            System.out.println("No players in Roaster as of now.");
        }else {
            System.out.println("Displaying a current roaster!");
            ufcRepository.listPlayers().forEach(s -> System.out.println("UFC Player: " + s));
        }
    }

    public void registerPlayer(Scanner sc){

        System.out.println("Please enter player's name: ");
        String name = sc.nextLine();


        System.out.println("Please enter player's weight class: ");
        String weightClass = sc.nextLine();

        UFCPlayer ufcPlayer = new UFCPlayer(name,weightClass);
        ufcRepository.addPlayer(ufcPlayer);

        System.out.println("UFC Player added to roaster successfully!");
    }
}
