package was.v1;

import logging.MyLogger;
import was.constants.Constants;
import was.utility.impl.ServerUtilityV1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class HttpServerV1 {

    ServerUtilityV1 serverUtilityV1 = new ServerUtilityV1();
    static AtomicInteger id = new AtomicInteger(0);
    public void start(){
        try {
            ServerSocket serverSocket = new ServerSocket(Constants.port);
            MyLogger.log("Server Log | TCP Connection established: "+serverSocket);

            while(true){
                Socket socket = serverSocket.accept();
                new Thread(()->{
                    serverUtilityV1.process(socket);
                },"Session Thread ID | "+String.valueOf(id.getAndIncrement())).start();

            }
        }
        catch(IOException e){
            MyLogger.log("Server Log | Exception occurred: "+e);
        }

    }

}
