package was.httpserver;

import logging.MyLogger;
import was.constants.Constants;
import was.v5.HttpRequestHandlerV5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class HttpServer {


    public ServletManager servletManager;
    final ExecutorService executorService = Executors.newFixedThreadPool(Constants.coreSize);

    public HttpServer(ServletManager servletManager){
        this.servletManager = servletManager;
    }

    //public final ServletManager servletManager = new ServletManager();
    public void start(){
        try {
            ServerSocket serverSocket = new ServerSocket(Constants.port);
            MyLogger.log("Server Log | TCP Connection established: "+serverSocket);

            while(true){
                Socket socket =  serverSocket.accept();
                executorService.submit(new HttpRequestHandler(socket,this.servletManager));
            }
        }
        catch(IOException e){
            MyLogger.log("Server Log | Exception occurred: "+e);
        }

    }

}
