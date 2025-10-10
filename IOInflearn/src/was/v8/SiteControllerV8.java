package was.v8;

import logging.MyLogger;
import was.annotations.RequestMapping;
import was.constants.Constants;
import was.constants.RequestConstants;
import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.controller.Controller;

public class SiteControllerV8 extends Controller {
    @RequestMapping
    public void home(HttpRequest httpRequest, HttpResponse httpResponse){
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
    @RequestMapping(requestPath = RequestConstants.SITE_1)
    public void page1(HttpRequest httpRequest, HttpResponse httpResponse){
        StringBuilder str = new StringBuilder();
        //String site = "site "+"";
        str.append("<h1>site 1</h1>");

        httpResponse.statusCode= Constants.SUCCESSFUL_CODE;
        httpResponse.responseBody=str;
    }

    @RequestMapping(requestPath = RequestConstants.SITE_2)
    public void page2(HttpRequest httpRequest, HttpResponse httpResponse){
        StringBuilder str = new StringBuilder();
        //String site = "site "+"";
        str.append("<h1>site 2</h1>");

        httpResponse.statusCode= Constants.SUCCESSFUL_CODE;
        httpResponse.responseBody=str;
    }

    @RequestMapping(requestPath = RequestConstants.FAVICON)
    public void favicon(HttpRequest httpRequest, HttpResponse httpResponse){
        MyLogger.log("Server Log | Ignoring Http Request "+httpRequest.requestPath);
        httpResponse.statusCode=Constants.SUCCESSFUL_CODE;
    }


}
