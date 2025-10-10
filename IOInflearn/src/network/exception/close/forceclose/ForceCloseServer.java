package network.exception.close.forceclose;

import logging.MyLogger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ForceCloseServer {
    public static void main(String args[]) throws IOException, InterruptedException {

        try(ServerSocket serverSocket = new ServerSocket(12345); Socket socket = serverSocket.accept();) {
            //ServerSocket serverSocket = new ServerSocket(12345);
            //Socket socket = serverSocket.accept();

            MyLogger.log("Server Log | Socket established: " + socket);
            //Thread.sleep(1000);

            //            socket.close();
            //            serverSocket.close();
        }
        catch(IOException e){
            System.out.println(e);
        }


    }
}
