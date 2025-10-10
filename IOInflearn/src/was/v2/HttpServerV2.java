package was.v2;

import logging.MyLogger;
import was.constants.Constants;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class HttpServerV2 {

    static AtomicInteger id = new AtomicInteger(0);
    public void start(){
        try {
            ServerSocket serverSocket = new ServerSocket(Constants.port);
            MyLogger.log("Server Log | TCP Connection established: "+serverSocket);

            while(true){
                Socket socket = serverSocket.accept();
                new HttpRequestHandlerV2(socket);
            }
        }
        catch(IOException e){
            MyLogger.log("Server Log | Exception occurred: "+e);
        }

    }

}
