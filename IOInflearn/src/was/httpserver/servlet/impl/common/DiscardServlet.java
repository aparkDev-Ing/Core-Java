package was.httpserver.servlet.impl.common;

import logging.MyLogger;
import was.constants.Constants;
import was.constants.RequestConstants;
import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.servlet.Servlet;

public class DiscardServlet implements Servlet {
    public DiscardServlet(){

    }

    @Override
    public void service(HttpRequest httpRequest, HttpResponse httpResponse){
        MyLogger.log("Server Log | Ignoring Http Request "+httpRequest.requestPath);
        httpResponse.statusCode=Constants.SUCCESSFUL_CODE;
    }
}
