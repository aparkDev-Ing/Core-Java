package was.httpserver.servlet.impl.common;

import logging.MyLogger;
import was.constants.Constants;
import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.servlet.Servlet;

public class NotFoundServlet implements Servlet{
    @Override
    public void service(HttpRequest httpRequest, HttpResponse httpResponse){
        StringBuilder str = new StringBuilder();
        str.append("<h1>not found </h1>");
        httpResponse.statusCode = Constants.NOT_FOUND;
        httpResponse.responseBody = str;

    }
}
