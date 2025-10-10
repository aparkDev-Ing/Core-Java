package was.httpserver.servlet.impl.common;

import logging.MyLogger;
import was.annotations.RequestMapping;
import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.NotFoundException;
import was.httpserver.controller.Controller;
import was.httpserver.servlet.Servlet;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AnnotationServletV8 implements Servlet {
    List<Controller> controllerList = new ArrayList<>();

    public AnnotationServletV8(List<Controller> controllerList){
        this.controllerList=controllerList;
    }

    @Override
    public void service(HttpRequest httpRequest, HttpResponse httpResponse) {
        String requestPath = httpRequest.requestPath;

        try{
            for(Controller controller: this.controllerList){
                Class<? extends Controller> controllerClass = controller.getClass();
                for(Method method: controllerClass.getDeclaredMethods()){
                    if(method.isAnnotationPresent(RequestMapping.class)){
                        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                        if(requestMapping.requestPath().equals(requestPath)){
                            method.invoke(controller,httpRequest,httpResponse);
                            return;
                        }
                    }
                }

            }

        }catch(Exception e){
            MyLogger.log("Server Log | Exception Occurred: "+e);
        }

        throw new NotFoundException("Request Path "+requestPath +" not found.");
    }

}
