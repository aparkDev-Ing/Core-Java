package was.v3;

import logging.MyLogger;
import was.constants.Constants;
import was.v2.HttpRequestHandlerV2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

public class HttpServerV3 {

    final ExecutorService executorService = Executors.newFixedThreadPool(Constants.coreSize);
    static AtomicInteger id = new AtomicInteger(0);
    public void start(){
        try {
            ServerSocket serverSocket = new ServerSocket(Constants.port);
            MyLogger.log("Server Log | TCP Connection established: "+serverSocket);

            while(true){
                Socket socket =  serverSocket.accept();
                executorService.submit(new HttpRequestHandlerV3(socket));
            }
        }
        catch(IOException e){
            MyLogger.log("Server Log | Exception occurred: "+e);
        }

    }

}
