package was.httpserver.servlet.impl.common;

import was.constants.Constants;
import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.servlet.Servlet;

public class InternalErrorServlet implements Servlet {
    @Override
    public void service(HttpRequest httpRequest, HttpResponse httpResponse){
        StringBuilder str = new StringBuilder();
        str.append("<h1>Internal Server Error</h1>");
        httpResponse.statusCode = Constants.SERVER_ERROR;
        httpResponse.responseBody = str;
    }
}
