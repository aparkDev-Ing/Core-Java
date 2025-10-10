package was.v8;

import was.annotations.RequestMapping;
import was.constants.Constants;
import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.controller.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchControllerV8 extends Controller {

    @RequestMapping(requestPath = "/search")
    public void search(HttpRequest httpRequest, HttpResponse httpResponse){
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
