package was.v6;

import was.constants.Constants;
import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.servlet.Servlet;

public class HomeServlet implements Servlet {

    public HomeServlet(){

    }
    @Override
    public void service(HttpRequest httpRequest, HttpResponse httpResponse){
        StringBuilder str = new StringBuilder();
        str.append("<h1>home</h1>");
        str.append("<ul>");
        str.append("<li><a href='/site1'>site1</a></li>");
        str.append("<li><a href='/site2'>site2</a></li>");
        str.append("<li><a href='/search?q=hello'>검색</a></li>");
        str.append("</ul>");

        httpResponse.statusCode= Constants.SUCCESSFUL_CODE;
        httpResponse.responseBody=str;
    }
}
