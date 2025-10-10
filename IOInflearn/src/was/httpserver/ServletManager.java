package was.httpserver;

import logging.MyLogger;
import was.httpserver.servlet.Servlet;

import java.util.HashMap;
import java.util.Map;

public class ServletManager {


    public Map<String, Servlet> servletMap = new HashMap<>();

    public Servlet defaultServlet;

    public Servlet notFoundServlet;

    public Servlet internalServerServlet;

    public Servlet discardServlet;


    public ServletManager(){

    }

    public void setDefaultServlet(Servlet defaultServlet){
        this.defaultServlet=defaultServlet;
    }

    public void setNotFoundServlet(Servlet notFoundServlet){
        this.notFoundServlet=notFoundServlet;
    }

    public void setDiscardServlet(Servlet discardServlet){
        this.discardServlet=discardServlet;
    }

    public void setInternalServerServlet(Servlet internalServerServlet){
        this.internalServerServlet=internalServerServlet;
    }

    public void addServlet(String path,Servlet servlet){
        this.servletMap.put(path,servlet);
    }

    public void execute(HttpRequest httpRequest, HttpResponse httpResponse){
        try {
            Servlet servlet = this.servletMap.getOrDefault(httpRequest.requestPath, this.defaultServlet);
            //Servlet servlet = this.servletMap.get(httpRequest.requestPath);
            if(servlet ==null){
                //throw new NotFoundException("Request Path Not Found "+ httpRequest.requestPath);
                this.notFoundServlet.service(httpRequest,httpResponse);
            }else{
                servlet.service(httpRequest,httpResponse);
            }

        }
        catch(NotFoundException e){
            MyLogger.log("Server Log | Error Occurred (Path Not Found): "+e);
            this.notFoundServlet.service(httpRequest,httpResponse);
        }
        catch(Exception e){
            MyLogger.log("Server Log | Error Occurred: "+e);
            throw new RuntimeException(e);
        }
    }

    public void executeNotFound(HttpRequest httpRequest, HttpResponse httpResponse){
        this.notFoundServlet.service(httpRequest,httpResponse);
    }

}
