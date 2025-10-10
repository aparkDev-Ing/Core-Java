package network.tcp.v3;

import logging.MyLogger;
import network.tcp.constants.SocketConstants;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class NetworkingMainClientV3 {

    //            switch (selection){
//                case 1:
//                    Client.request(selection);
//                    //Server.handleRequest();
//                    Client.receiveResponse();
//                    break;
//
//                case 2:
//                    Client.request(selection);
//                    //Server.handleRequest();
//                    Client.receiveResponse();
//                    break;
//                case 3:
//                    Client.request(selection);
//                    //Server.handleRequest();
//                    Client.receiveResponse();
//                    break;
//                case 4:
//                    System.out.println("Finishing a program. Thank you!");
//                    //Server.closeServerConnection();
//                    Client.closeClientConnection();
//                    return;
//                default:
//                    System.out.println("Wrong choice. Please Try again!");
//
//            }
    public static void main(String args[]) throws IOException{
        launchApplication();
    }
    public static void launchApplication() throws IOException{

        connect();

        while(true){
            int selection = showPrompt();

            if(selection ==3){
                Client.request(selection);
                System.out.println("Program Exits!");
                break;
            }

            if(selection != 0 && selection !=1&& selection!=2){
                System.out.println("Invalid selection. Please try again");
                continue;
            }

            Client.request(selection);
            Client.receiveResponse();

        }

        Client.closeClientConnection();
    }

    public static int showPrompt(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Hi. Please choose from following option: \n" +" 0.Taco | 1.Pizza | 2.Pasta | 3. Exit a building");
        int selection  = scan.nextInt();
        scan.nextLine();

        return selection;
    }

    public static void connect() throws IOException{


        Client.connectClientSocket();

    }
}


class Client{

    public static Socket socket ;
    public static DataOutputStream dos ;
    public static DataInputStream dis ;
    public static void connectClientSocket() throws IOException {

         socket = new Socket("localhost",SocketConstants.PORT);
         MyLogger.log("[Client Log] | client attempting to connect server: " +socket);

    }

    public static void request( int selection) throws IOException{
        if(dos==null) {

            dos = new DataOutputStream(socket.getOutputStream());
            MyLogger.log("[Client Log] | client built output stream to send out request for the first time: " +socket);
        }
        dos.writeInt(selection);
        MyLogger.log("[Client Log] | client sent request to server: " +selection);
    }

    public static void receiveResponse() throws IOException{

        if(dis==null) {
            dis = new DataInputStream(socket.getInputStream());
            MyLogger.log("[Client Log] | client built input stream to receive the response first time: " +socket);
        }
        String food = dis.readUTF();
        MyLogger.log("[Client Log] | client has received a response: " +food);

        //return food;
    }

    public static void closeClientConnection() throws IOException{

        MyLogger.log("[Client Log] | client closing a connection: ");

        dis.close();
        dos.close();
        socket.close();
        //return food;
    }

}


