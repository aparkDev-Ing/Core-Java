package ioexamples.ufcplayer.memoryio;

import ioexamples.ufcplayer.common.utility.UFCPlayerServiceImpl;
import ioexamples.ufcplayer.impl.DataStreamUfcPlayerRepository;
import ioexamples.ufcplayer.impl.FileUFCPlayerRepository;
import ioexamples.ufcplayer.impl.MemoryUFCPlayerRepository;
import ioexamples.ufcplayer.impl.SerializationUfcPlayerRepository;

import java.util.Arrays;
import java.util.Scanner;

public class UFCPlayerExample {

    static UFCPlayerServiceImpl ufcPlayerService = new UFCPlayerServiceImpl();
    public static void main(String args[]){

        //System.out.println(Arrays.toString("ABC".getBytes()));
        launchApplication();
    }

    static void launchApplication(){

        Scanner sc = new Scanner(System.in);

        while(true){
            int choice = promptForUser(sc);

            switch (choice){
                case 1:
                    registerPlayer(sc);
                    break;

                case 2:
                    displayRoaster();
                    break;
                case 3:
                    System.out.println("Finishing a program. Thank you!");
                    //System.exit(0);
                    return;
                default:
                    System.out.println("Wrong choice. Please Try again!");

            }
        }
    }

    static int promptForUser(Scanner sc){

       return ufcPlayerService.promptForUser(sc);
    }

    static void registerPlayer(Scanner sc){

        ufcPlayerService.registerPlayer(sc);

    }

    static void displayRoaster(){

        ufcPlayerService.displayPlayers();
    }
}
