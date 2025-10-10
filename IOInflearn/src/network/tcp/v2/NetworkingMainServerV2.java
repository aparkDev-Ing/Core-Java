package network.tcp.v2;

import logging.MyLogger;
import network.tcp.constants.SocketConstants;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class NetworkingMainServerV2 {

    public static void main(String args[]) throws IOException{
        launchApplication();
    }
    public static void launchApplication() throws IOException{

        connect();

        while(true){
            if(!Server.handleRequest()){
                break;
            }
        }

        Server.closeServerConnection();
    }


    public static void connect() throws IOException{

        Server.connectServerSocket();
    }
}


class Server{

    public static Socket socket ;

    public static ServerSocket serverSocket ;
    public static DataInputStream dis ;
    public static DataOutputStream dos ;
    public static void connectServerSocket() throws IOException {
        serverSocket = new ServerSocket(SocketConstants.PORT);

        socket = serverSocket.accept();
        MyLogger.log("[Server Log] | server has established socket - now connected with client: " +socket);

    }

    public static boolean handleRequest() throws IOException{


        if(dis ==null) {
            dis = new DataInputStream(socket.getInputStream());
            MyLogger.log("[Server Log] | server built input stream to receive request for the first time: " +socket);
        }
        int selection = dis.readInt();

        MyLogger.log("[Server Log] | server has received a request: " +selection);

        if(selection == 3){
            MyLogger.log("[Server Log] | Exit request from client: " +selection);
            return false;
        }


        String food = SocketConstants.selection.get(selection);

        if(dos ==null) {
            dos = new DataOutputStream(socket.getOutputStream());
            MyLogger.log("[Server Log] | server built created output stream to respond for the first time!: " +socket);
        }

        dos.writeUTF(food);
        MyLogger.log("[Server Log] | server has responded back: " +food);

        return true;
    }

    public static void closeServerConnection() throws IOException{
        MyLogger.log("[Server Log] | server closing socket connection: " );

        dis.close();
        dos.close();
        socket.close();
        serverSocket.close();

    }

}
