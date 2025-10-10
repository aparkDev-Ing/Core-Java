package was.v5;


import logging.MyLogger;
import was.constants.Constants;
import was.constants.RequestConstants;
import was.utility.httputility.HttpRequestGenerator;
import was.utility.httputility.HttpResponseGenerator;
import was.utility.impl.ServerUtilityV4;
import was.v4.HttpServerV4;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static logging.MyLogger.log;

public class HttpRequestHandlerV5 implements Runnable{

    int threadId ;
    public final Socket socket;
    public HttpRequestHandlerV5(Socket socket){
        this.socket=socket;
        this.threadId= HttpServerV5.id.getAndIncrement();
    }

    @Override
    public void run(){
        process();
    }

    public void process(){

        try(socket; BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter pw = new PrintWriter(socket.getOutputStream())){

            HttpRequestGenerator httpRequestGenerator = new HttpRequestGenerator(br);
            HttpResponseGenerator httpResponseGenerator;


            String firstLine = httpRequestGenerator.firstLine;
            String requestPath=httpRequestGenerator.requestPath;
            MyLogger.log("Server Log | Request from client received in server-> "+ firstLine);

            if (requestPath.equals("/favicon.ico")) {
                log("favicon 요청 무시!");
                return;
            }

            if(requestPath.equals(RequestConstants.HOME_REQUEST)){
                httpResponseGenerator= new HttpResponseGenerator(Constants.SUCCESSFUL_CODE,homeBody(),pw);
            }else if(requestPath.equals(RequestConstants.SEARCH)){
                httpResponseGenerator= new HttpResponseGenerator(Constants.SUCCESSFUL_CODE,searchBody(httpRequestGenerator),pw);
            }else if(requestPath.equals(RequestConstants.SITE_1)){
                httpResponseGenerator= new HttpResponseGenerator(Constants.SUCCESSFUL_CODE,site1(),pw);
            }else if(requestPath.equals(RequestConstants.SITE_2)){
                httpResponseGenerator= new HttpResponseGenerator(Constants.SUCCESSFUL_CODE,site2(),pw);
            }else{
                log("Invalid Request: "+requestPath);
                httpResponseGenerator= new HttpResponseGenerator(Constants.NOT_FOUND,notFound(),pw);
            }

            httpResponseGenerator.flush();

            MyLogger.log("Server Log | Response sent from server "+ httpResponseGenerator.responseBody.toString());

        }catch(Exception e){
            log("Exception occurred: "+e);
        }

    }

    public StringBuilder homeBody(){
        StringBuilder str = new StringBuilder();
        str.append("<h1>home</h1>");
        str.append("<ul>");
        str.append("<li><a href='/site1'>site1</a></li>");
        str.append("<li><a href='/site2'>site2</a></li>");
        str.append("<li><a href='/search?q=hello'>검색</a></li>");
        str.append("</ul>");

        return str;
    }

    public StringBuilder searchBody(HttpRequestGenerator httpRequestGenerator){

        Map<String, String> queryParams = httpRequestGenerator.queryParamsMap;

        List<String> valueList = new ArrayList<>();
          queryParams.forEach((k,v)-> valueList.add(v));

        StringBuilder str = new StringBuilder();
        str.append("<h1>Search</h1>");
        str.append("<ul>");
        str.append("<li>query: " + valueList.toString() + "</li>");
        str.append("</ul>");

        return str;
    }

    public StringBuilder site1(){

        StringBuilder str = new StringBuilder();
        //String site = "site "+"";
        str.append("<h1>site 1</h1>");

        return str;
    }
    public StringBuilder site2(){

        StringBuilder str = new StringBuilder();
        //String site = "site "+"";
        str.append("<h1>site 2</h1>");

        return str;
    }

    public StringBuilder notFound(){

        StringBuilder str = new StringBuilder();
        //String site = "site "+"";
        str.append("<h1>not found 2</h1>");

        return str;
    }


}
