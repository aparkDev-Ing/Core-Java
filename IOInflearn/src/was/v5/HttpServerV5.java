package was.v5;

import logging.MyLogger;
import was.constants.Constants;
import was.v4.HttpRequestHandlerV4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class HttpServerV5 {

    final ExecutorService executorService = Executors.newFixedThreadPool(Constants.coreSize);
    static AtomicInteger id = new AtomicInteger(0);
    public void start(){
        try {
            ServerSocket serverSocket = new ServerSocket(Constants.port);
            MyLogger.log("Server Log | TCP Connection established: "+serverSocket);

            while(true){
                Socket socket =  serverSocket.accept();
                executorService.submit(new HttpRequestHandlerV5(socket));
            }
        }
        catch(IOException e){
            MyLogger.log("Server Log | Exception occurred: "+e);
        }

    }

}
