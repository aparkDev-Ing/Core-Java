package network.tcp.v5;

import logging.MyLogger;
import network.tcp.constants.SocketConstants;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkingMainServerV5 {

    public static void main(String args[]) throws IOException{
        launchApplication();
    }
    public static void launchApplication() throws IOException{

        connect();

        Server.closeServerSocket();
    }


    public static void connect() throws IOException{

        Server.connectServerSocket();
    }
}


class Server{


    public static ServerSocket serverSocket ;

    public static void connectServerSocket() throws IOException {
        serverSocket = new ServerSocket(SocketConstants.PORT);

        while(true)
         {
                Socket socket = serverSocket.accept();
                MyLogger.log("[Server Log] | server has established socket - now connected with client: " + socket);
             SessionV5 session = new SessionV5(socket, new DataInputStream(socket.getInputStream()), new DataOutputStream(socket.getOutputStream()));
         }

    }

   public static void closeServerSocket() throws IOException{
        serverSocket.close();
       MyLogger.log("[Server Log] | Server Socket closed. No more TCP 3 way handshake avaliable: " );
   }



}
