package was.utility.impl;

import logging.MyLogger;
import was.utility.interfaces.ServerUtility;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.SimpleTimeZone;

import static efficientfilestream.BufferConstants.UTF_8;

public class ServerUtilityV1 implements ServerUtility {

    @Override
    public void process(Socket socket){

        try(socket;
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(socket.getInputStream(), UTF_8));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(),
                    false, UTF_8)) {

            String request = parseRequest(reader);

            MyLogger.log("\nServer Log | Request from client received in server-> \n" );
            System.out.println(request);

            if(request.toString().contains("/favicon.ico"))
            {
//                MyLogger.log("Server Log | Request from client contains favicon. Server will ignore this request-> " + request);
                MyLogger.log("Server Log | Request from client contains favicon. Server will ignore this request");
                return;
            }

            sleep(5000);
            String response = generateResponse();
            writer.println(response);
            writer.flush();
            MyLogger.log("Server Log | Response to client sent in server-> " );
            System.out.println(
                    response
            );

        }
        catch(Exception e){
            MyLogger.log("Server Log | Exception occurred while receiving a request from client-> " + e);
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

    public static String generateResponse() throws IOException {


            //MyLogger.log("Server Log | Server preparing a response to client...");

            String body = "<h1>Hello  World</h1>";
            int length = body.getBytes(StandardCharsets.UTF_8).length;
            StringBuilder response = new StringBuilder();
            response.append("HTTP/1.1 200 OK\r\n");
            response.append("Content-Type: text/html\r\n");
            response.append("Content-Length: ").append(length).append("\r\n");
            response.append("\r\n"); // header, body 구분 라인

//        response.append("HTTP/1.1 200 OK").append("\r\n");
//
//        response.append("Content-Type: text/html").append("\r\n");
//        response.append("Content-Length: ").append(length).append("\r\n");
//        response.append("\r\n"); // header, body 구분 라인

            response.append(body);

        //MyLogger.log("Server Log | Response to client generated in server-> " + response);

            return response.toString();
    }

    public static void sleep(int millis){
        try{
            Thread.sleep(millis);
        }catch(InterruptedException e){
            MyLogger.log("Server Log | Exception occurred while thread sleeping: " + e);
        }
    }
}
