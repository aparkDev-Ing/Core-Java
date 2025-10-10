package network.tcp.v4;

import logging.MyLogger;
import network.tcp.constants.SocketConstants;
import network.tcp.utilities.SocketCloseUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class NetworkingMainClientV4 {

    public static void main(String args[]) throws IOException{
        launchApplication();
    }
    public static void launchApplication() throws IOException{

        try {
            connect();

            while (true) {
                int selection = showPrompt();

                if (selection == 3) {
                    Client.request(selection);
                    System.out.println("Program Exits!");
                    break;
                }

                if (selection != 0 && selection != 1 && selection != 2) {
                    System.out.println("Invalid selection. Please try again");
                    continue;
                }

                Client.request(selection);
                Client.receiveResponse();

            }

        }
        catch(IOException e) {
            MyLogger.log(e);
        }finally{
            Client.closeClientConnection();
        }
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

        SocketCloseUtil.closeAll(socket, dos,dis);
        MyLogger.log("[Client Log] | client closed a connection: ");

        //return food;
    }

}


