package network.chat.server;

import logging.MyLogger;
import network.chat.constants.Constants;
import network.chat.server.session.Session;
import network.chat.server.session.SessionManager;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class UserServer {

    public static void main(String args[]){
        launchApplication();
    }

    public static void launchApplication(){

        try {
            accept();
        }
        catch(Exception e){
            MyLogger.log("Server Log | Exception Occured: "+ e);
        }
    }
    public static void accept()throws IOException{

        ServerSocket serverSocket = new ServerSocket(Constants.port);
        MyLogger.log("Server Log | Server Socket opened: "+ serverSocket);
        //serverSocket.setSoTimeout(10_000); // 10 seconds

        Runtime.getRuntime().addShutdownHook(new Thread(()->{

                triggerShutDownHook(serverSocket);

        },"Shutdown Hook Thread"));


        try {
            while (true) {
                Socket socket = serverSocket.accept();
                Session session =new Session(socket, new ObjectOutputStream(socket.getOutputStream()),new ObjectInputStream(socket.getInputStream()));

            }
        }
        catch(SocketTimeoutException e){
            MyLogger.log("Socket Timeout Exception after 10 sec: " +e);
        }
        catch(IOException e){
            MyLogger.log("Exception Occurred: " +e);
        }

    }

    public static void triggerShutDownHook(ServerSocket serverSocket){
        try {
            MyLogger.log("Server Log | Shutdownhook triggered ");
            SessionManager.clearSession();
            closeServerSocket(serverSocket);
            Thread.sleep(1000);
            MyLogger.log("Server Log | Shutdownhook finished ");
        }
        catch(Exception e){
            MyLogger.log("Server Log | Error Occured: "+e);
        }
    }

    public static void closeServerSocket(ServerSocket serverSocket){
        try {
            serverSocket.close();
            MyLogger.log("Server Log | Server Socket closed. Application terminated ");
        }
        catch(IOException e){
            System.out.println("Exception occurred: "+e);
        }
    }
}
