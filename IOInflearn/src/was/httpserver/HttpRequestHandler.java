package was.httpserver;


import logging.MyLogger;
import was.constants.Constants;
import was.constants.RequestConstants;
import was.httpserver.servlet.Servlet;
import was.utility.httputility.HttpRequestGenerator;
import was.utility.httputility.HttpResponseGenerator;
import was.v5.HttpServerV5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static logging.MyLogger.log;

public class HttpRequestHandler implements Runnable{


    public ServletManager servletManager;
    public final Socket socket;

    public HttpRequestHandler(Socket socket, ServletManager servletManager){
        this.socket=socket;
        this.servletManager=servletManager;
    }

    @Override
    public void run(){
        process();
    }

    public void process(){

        try(socket; BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter pw = new PrintWriter(socket.getOutputStream())){

            HttpRequest httpRequest = new HttpRequest(br);
            HttpResponse httpResponse= new HttpResponse(pw);

            String firstLine = httpRequest.firstLine;
            //String requestPath= httpRequest.requestPath;
            MyLogger.log("Server Log | Request from client received in server-> "+ firstLine);
            // MyLogger.log("Server Log | Request from client received in server-> "+ httpRequest);

            servletManager.execute(httpRequest,httpResponse);

            if(httpResponse.responseBody != null) {
                httpResponse.flush();
                MyLogger.log("Server Log | Response sent from server "+ httpResponse.responseBody.toString());
            }

        }

        catch(Exception e){
            log("Exception occurred: "+e);
            e.printStackTrace();
            //e.printStackTrace();
        }

    }






}
