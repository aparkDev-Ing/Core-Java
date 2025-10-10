package network.tcp.v6;

import logging.MyLogger;
import network.tcp.constants.SocketConstants;
import network.tcp.v5.SessionV5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

public class NetworkingMainServerV6 {

    public static void main(String args[]) throws IOException{
        launchApplication();
    }
    public static void launchApplication() throws IOException{

        connect();

        //Server.closeServerSocket();
    }


    public static void connect() throws IOException{

        Server.connectServerSocket();
    }
}


class Server{


    public static ServerSocket serverSocket ;

    public static void connectServerSocket() throws IOException {

        serverSocket = new ServerSocket(SocketConstants.PORT);
        serverSocket.setSoTimeout(10_000); // 10 seconds

        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            try {
                MyLogger.log("Shutdown hook starts");
                SessionManagerV6.closeAll();
                closeServerSocket();
                MyLogger.log("Shutdown hook finished. Server is completely terminated now.");
                Thread.sleep(1000);
            }
            catch(Exception e){
                System.out.println("Exception occured while shutdownhook "+e);
            }
        },"Shutdown"));

        try {
            while (true) {
                Socket socket = serverSocket.accept();
                //Main thread blocked awaiting for client socket connection request
                //Shuntdown hook thread closes server socket
                MyLogger.log("[Server Log] | server has established socket - now connected with client: " + socket);
                SessionV6 session = new SessionV6(socket, new DataInputStream(socket.getInputStream()), new DataOutputStream(socket.getOutputStream()));
            }
        }
        catch(SocketTimeoutException e){
            MyLogger.log("Socket Timeout Exception after 10 sec: " +e);
        }
        catch(Exception e){
            //main thread gets exception when serverSocket is closed by shutdown hook thread
            MyLogger.log("Server terminated: " +e);
        }

    }

   public static void closeServerSocket() throws IOException{
        serverSocket.close();
       MyLogger.log("[Server Log] | Server Socket closed. No more TCP 3 way handshake avaliable " );
   }



}
