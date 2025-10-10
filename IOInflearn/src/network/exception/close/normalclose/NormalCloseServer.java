package network.exception.close.normalclose;

import logging.MyLogger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NormalCloseServer {
    public static void main(String args[]) throws IOException, InterruptedException {

        ServerSocket serverSocket = new ServerSocket(12345);
        MyLogger.log("Server Log: Socket TCP connection established");
        Socket socket =serverSocket.accept();
        MyLogger.log("Server Log: Socket is created in Server");

        Thread.sleep(3000);

        socket.close();
        serverSocket.close();

        MyLogger.log("Server Log: Socket is terminated. Program exit");

    }
}
