package was.v6;

import was.constants.Constants;
import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.servlet.Servlet;

public class Site2 implements Servlet {
    @Override
    public void service(HttpRequest httpRequest, HttpResponse httpResponse){
        StringBuilder str = new StringBuilder();
        //String site = "site "+"";
        str.append("<h1>site 2</h1>");

        httpResponse.statusCode= Constants.SUCCESSFUL_CODE;
        httpResponse.responseBody=str;
    }
}
