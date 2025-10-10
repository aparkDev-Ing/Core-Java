package was.v7;

import was.constants.Constants;
import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.controller.Controller;

public class SiteControllerV7 extends Controller {
    public SiteControllerV7(){

    }
    public void site1(HttpRequest httpRequest, HttpResponse httpResponse){
        StringBuilder str = new StringBuilder();
        //String site = "site "+"";
        str.append("<h1>site 1</h1>");

        httpResponse.statusCode= Constants.SUCCESSFUL_CODE;
        httpResponse.responseBody=str;
    }

    public void site2(HttpRequest httpRequest, HttpResponse httpResponse){
        StringBuilder str = new StringBuilder();
        //String site = "site "+"";
        str.append("<h1>site 2</h1>");

        httpResponse.statusCode= Constants.SUCCESSFUL_CODE;
        httpResponse.responseBody=str;
    }
}

