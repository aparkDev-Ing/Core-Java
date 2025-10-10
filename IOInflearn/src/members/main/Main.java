package members.main;

import ioexamples.ufcplayer.common.utility.UFCPlayerServiceImpl;
import members.controllers.MembersController;
import was.httpserver.HttpServer;
import was.httpserver.ServletManager;
import was.httpserver.controller.Controller;
import was.httpserver.servlet.impl.common.AnnotationServletV10;
import was.httpserver.servlet.impl.common.DiscardServlet;
import was.httpserver.servlet.impl.common.InternalErrorServlet;
import was.httpserver.servlet.impl.common.NotFoundServlet;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        launchApplication();
    }

    public static void launchApplication(){

        List<Controller> controllersList = List.of(new MembersController(new UFCPlayerServiceImpl()));

        ServletManager servletManager = new ServletManager();
        servletManager.setDiscardServlet(new DiscardServlet());
        servletManager.setNotFoundServlet(new NotFoundServlet());
        servletManager.setInternalServerServlet(new InternalErrorServlet());
        servletManager.setDefaultServlet(new AnnotationServletV10(controllersList));

        new HttpServer(servletManager).start();

    }
}
