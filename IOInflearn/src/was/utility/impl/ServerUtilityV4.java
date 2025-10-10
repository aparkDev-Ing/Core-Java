package was.utility.impl;

import logging.MyLogger;
import was.constants.RequestConstants;
import was.utility.interfaces.ServerUtility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URLDecoder;

import static efficientfilestream.BufferConstants.UTF_8;

public class ServerUtilityV4 implements ServerUtility {

    @Override
    public void process(Socket socket){

        try(socket;
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(socket.getInputStream(), UTF_8));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(),
                    false, UTF_8)) {

            String request = parseRequest(reader);

            MyLogger.log("Server Log | Request from client received in server-> \n" );
            System.out.println(request);

            if(request.toString().contains("/favicon.ico"))
            {
                MyLogger.log("Server Log | Request from client contains favicon. Server will ignore this request");
                return;
            }

            handleRequest(request,writer);

            MyLogger.log("Server Log | Server has responded back to Client.");

        }
        catch(Exception e){
            MyLogger.log("Server Log | Exception occurred while receiving a request from client-> " + e);
        }
    }

    public static void handleRequest(String request,PrintWriter pw) throws IOException{

        if(request.startsWith(RequestConstants.HOME_REQUEST)){
            pw.println("HTTP/1.1 200 OK");
            pw.println("Content-Type: text/html; charset=UTF-8");
            pw.println();
            pw.println("<h1>home</h1>");
            pw.println("<ul>");
            pw.println("<li><a href='/site1'>site1</a></li>");
            pw.println("<li><a href='/site2'>site2</a></li>");
            pw.println("<li><a href='/search?q=hello'>검색</a></li>");
            pw.println("</ul>");
            pw.flush();
        }else if(request.startsWith(RequestConstants.SITE_1)){
            pw.println("HTTP/1.1 200 OK");
            pw.println("Content-Type: text/html; charset=UTF-8");
            pw.println();
            pw.println("<h1>site1</h1>");
        }else if(request.startsWith(RequestConstants.SITE_2)){
            pw.println("HTTP/1.1 200 OK");
            pw.println("Content-Type: text/html; charset=UTF-8");
            pw.println();
            pw.println("<h1>site2</h1>");
        }else if(request.startsWith(RequestConstants.SEARCH)){
            int startIndex = request.indexOf("q=");
            int endIndex = request.indexOf(" ", startIndex+2);
            String query = request.substring(startIndex+2, endIndex);
            String decode= URLDecoder.decode(query, UTF_8);
            pw.println("HTTP/1.1 200 OK");
            pw.println("Content-Type: text/html; charset=UTF-8");
            pw.println();
            pw.println("<h1>Search</h1>");
            pw.println("<ul>");
            pw.println("<li>query: " + query + "</li>");
            pw.println("<li>decode: " + decode + "</li>");
            pw.println("</ul>");
            pw.flush();
        }else{
            pw.println("HTTP/1.1 404 Not Found");
            pw.println("Content-Type: text/html; charset=UTF-8");
            pw.println();
            pw.println("<h1>Not Found!</h1>");
        }



    }

    public static String parseRequest(BufferedReader reader) throws IOException{

        //MyLogger.log("Server Log | Server parsing client request started-> " );

        StringBuilder request = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            if(line.isEmpty()){
                break;
            }
            request.append(line).append("\r\n");
        }
        //MyLogger.log("Server Log | Server parsing client request finished-> " );

        return request.toString();
    }



    public static void sleep(int millis){
        try{
            Thread.sleep(millis);
        }catch(InterruptedException e){
            MyLogger.log("Server Log | Exception occurred while thread sleeping: " + e);
        }
    }
}
