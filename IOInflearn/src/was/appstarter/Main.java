package was.appstarter;

import was.httpserver.HttpServer;
import was.httpserver.ServletManager;
import was.httpserver.controller.Controller;
import was.httpserver.servlet.Servlet;
import was.httpserver.servlet.impl.common.*;
import was.v8.SearchControllerV8;
import was.v8.SiteControllerV8;
import was.v9.SearchControllerV9;
import was.v9.SiteControllerV9;

import java.util.List;


public class Main {
    public static void main(String args[]){
        launchApp();
    }

    public static void launchApp(){

//        HttpServerV1 httpServerV1 = new HttpServerV1();
//        httpServerV1.start();

//        HttpServerV2 httpServerV2 = new HttpServerV2();
//        httpServerV2.start();

//        HttpServerV3 httpServerV3 = new HttpServerV3();
//        httpServerV3.start();

//        HttpServerV4 httpServerV4 = new HttpServerV4();
//        httpServerV4.start();

//        HttpServerV5 httpServerV5 = new HttpServerV5();
//        httpServerV5.start();


//        //V6
//        ServletManager servletManager = new ServletManager();
//        servletManager.addServlet(RequestConstants.HOME_REQUEST, new HomeServlet());
//        servletManager.addServlet(RequestConstants.SITE_1, new Site1());
//        servletManager.addServlet(RequestConstants.SITE_2, new Site2());
//        servletManager.addServlet(RequestConstants.SEARCH, new Search());
//
////        servletManager.addServlet(RequestConstants.SEARCH, (rq,rs)->{
////
////        });
//
//
//        servletManager.setDefaultServlet(new HomeServlet());
//        servletManager.setInternalServerServlet(new InternalErrorServlet());
//        servletManager.setNotFoundServlet(new NotFoundServlet());
//        servletManager.setDiscardServlet(new DiscardServlet());
//
//        servletManager.addServlet(RequestConstants.FAVICON, servletManager.discardServlet);
//
//        HttpServer httpServer = new HttpServer(servletManager);
//        httpServer.start();





        //V7 with reflection

        //List<Controller> reflectionControllers = List.of(new SiteControllerV7(), new SearchControllerV7());
//        ServletManager servletManager = new ServletManager();
//        servletManager.setDefaultServlet(new ReflectionServlet(List.of(new SiteControllerV7(), new SearchControllerV7())));
//
//        servletManager.setInternalServerServlet(new InternalErrorServlet());
//        servletManager.setNotFoundServlet(new NotFoundServlet());
//        servletManager.setDiscardServlet(new DiscardServlet());
//
//        servletManager.addServlet(RequestConstants.FAVICON, servletManager.discardServlet);
//        servletManager.addServlet("/",new HomeServlet());
//
//        new HttpServer(servletManager).start();


        //V8 with annotations

//        List<Controller> annotationControllerList = List.of(new SiteControllerV8(), new SearchControllerV8());
//        Servlet annotationServlet = new AnnotationServletV8(annotationControllerList);
//        ServletManager servletManager =new ServletManager();
//        servletManager.setDefaultServlet(annotationServlet);
//        servletManager.setInternalServerServlet(new InternalErrorServlet());
//        servletManager.setNotFoundServlet(new NotFoundServlet());
//        //servletManager.addServlet(RequestConstants.FAVICON,new DiscardServlet());
//        servletManager.setDiscardServlet(new DiscardServlet());
//
//        new HttpServer(servletManager).start();

        //V9 with annotations

//        List<Controller> annotationControllerList = List.of(new SiteControllerV9(), new SearchControllerV9());
//        Servlet annotationServlet = new AnnotationServletV9(annotationControllerList);
//        ServletManager servletManager =new ServletManager();
//        servletManager.setDefaultServlet(annotationServlet);
//        servletManager.setInternalServerServlet(new InternalErrorServlet());
//        servletManager.setNotFoundServlet(new NotFoundServlet());
//        //servletManager.addServlet(RequestConstants.FAVICON,new DiscardServlet());
//        servletManager.setDiscardServlet(new DiscardServlet());
//
//        new HttpServer(servletManager).start();


        //V10 with annotations with optimization + request path deduplication
        List<Controller> annotationControllerList = List.of(new SiteControllerV9(), new SearchControllerV9());
        Servlet annotationServlet = new AnnotationServletV10(annotationControllerList);
        ServletManager servletManager =new ServletManager();
        servletManager.setDefaultServlet(annotationServlet);
        servletManager.setInternalServerServlet(new InternalErrorServlet());
        servletManager.setNotFoundServlet(new NotFoundServlet());
        //servletManager.addServlet(RequestConstants.FAVICON,new DiscardServlet());
        servletManager.setDiscardServlet(new DiscardServlet());

        new HttpServer(servletManager).start();

    }
}
