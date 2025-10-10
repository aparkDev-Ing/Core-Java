package was.httpserver.servlet.impl.common;

import logging.MyLogger;
import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.NotFoundException;
import was.httpserver.controller.Controller;
import was.httpserver.servlet.Servlet;
import was.v7.SiteControllerV7;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectionServlet implements Servlet {
    List<Controller> controllers = new ArrayList<>();

    public ReflectionServlet(){

    }
    public ReflectionServlet(List<Controller> controllers){
        this.controllers=controllers;
    }
    @Override
    public void service(HttpRequest httpRequest, HttpResponse httpResponse) {

        String path = httpRequest.requestPath;
        try{
            //String path = httpRequest.requestPath;
            // /site1
            for(Controller controller: controllers){
                Class<? extends Controller> controllerClass = controller.getClass();
                //Class<? extends Controller> controllerClass = SiteControllerV7.class;
                try {
                    Method method = controllerClass.getDeclaredMethod(path.substring(1), HttpRequest.class, HttpResponse.class);
                    if(("/"+method.getName()).equals(path)) {
                        method.invoke(controller, httpRequest, httpResponse);
                        return;
                    }
                    //break;
                }
                catch(NoSuchMethodException e){
                    MyLogger.log("Server Log | No Such Method Exception Occurred: "+e);
                }
            }

        }catch(Exception e){
            MyLogger.log("Server Log | Exception Occurred: "+e);
        }

        throw new NotFoundException("Request Path "+path +" not found.");
    }


}
