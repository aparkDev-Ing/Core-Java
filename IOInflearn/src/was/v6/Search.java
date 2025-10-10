package was.v6;

import was.constants.Constants;
import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.servlet.Servlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Search implements Servlet {
    @Override
    public void service(HttpRequest httpRequest, HttpResponse httpResponse){
        Map<String, String> queryParams = httpRequest.queryParamsMap;

        List<String> valueList = new ArrayList<>();
        queryParams.forEach((k,v)-> valueList.add(v));

        StringBuilder str = new StringBuilder();
        str.append("<h1>Search</h1>");
        str.append("<ul>");
        str.append("<li>query: " + valueList.toString() + "</li>");
        str.append("</ul>");

        httpResponse.statusCode= Constants.SUCCESSFUL_CODE;
        httpResponse.responseBody=str;
    }
}
