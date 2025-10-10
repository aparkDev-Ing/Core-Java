package was.httpserver.servlet.impl.common;

import annotation.validator.Player;
import logging.MyLogger;
import was.annotations.RequestMapping;
import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.NotFoundException;
import was.httpserver.controller.Controller;
import was.httpserver.servlet.Servlet;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AnnotationServletV10 implements Servlet {
    //List<Controller> controllerList = new ArrayList<>();

    public Map<String, ControllerMethod> controllerMethodMap ;

    public AnnotationServletV10(List<Controller> controllerList){
        controllerMethodMap = new HashMap<>();
        initializeMap(controllerList);
    }

    @Override
    public void service(HttpRequest httpRequest, HttpResponse httpResponse) {

            String requestPath = httpRequest.requestPath;
            ControllerMethod controllerMethod = this.controllerMethodMap.get(requestPath);

            if (controllerMethod == null) {
                throw new NotFoundException("Request Path " + requestPath + " not found.");
            }

            try {
                controllerMethod.invoke(httpRequest, httpResponse);
            }
            catch(Exception e){
                throw new RuntimeException(e);
            }
    }

    public void initializeMap(List<Controller> controllerList){

        for (Controller controller:controllerList){
            for(Method method: controller.getClass().getDeclaredMethods()){
                if(method.isAnnotationPresent(RequestMapping.class)){

                    RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);

                    if(controllerMethodMap.containsKey(requestMapping.requestPath())){
                        throw new IllegalStateException("This request path already exists: "+ requestMapping.requestPath()+ " | Controller: "+ controller +" | Method: "+ method+" | Deduplication...");
                    }
                    this.controllerMethodMap.putIfAbsent(requestMapping.requestPath(),new ControllerMethod(controller,method));

                }

            }
        }

    }

    public static class ControllerMethod{
        Controller controller;
        Method method;

        public ControllerMethod(){

        }

        public ControllerMethod(Controller controller, Method method){
            this.controller=controller;
            this.method=method;
        }
        @Override
        public String toString() {
            return "ControllerMethod{" +
                    "controller=" + controller +
                    ", method=" + method +
                    '}';
        }

        public void invoke(HttpRequest httpRequest, HttpResponse httpResponse) throws Exception{

            Class<?>[] parameterTypes= method.getParameterTypes();

            Object[] objArs = new Object[parameterTypes.length];

            for(int i=0; i<parameterTypes.length;i++){
                if(parameterTypes[i] == HttpRequest.class){
                    objArs[i] = httpRequest;
                }else if(parameterTypes[i] == HttpResponse.class){
                    objArs[i] = httpResponse;
                }else{
                    throw new IllegalArgumentException("Not a proper argument class type "+parameterTypes[i] );
                }
            }

            method.invoke(controller,objArs);

        }
    }

}
