package members.controllers;

import ioexamples.ufcplayer.UFCPlayer;
import ioexamples.ufcplayer.common.utility.UFCPlayerServiceImpl;
import logging.MyLogger;
import was.annotations.RequestMapping;
import was.constants.Constants;
import was.constants.RequestConstants;
import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.controller.Controller;
import was.httpserver.servlet.Servlet;

import java.util.List;
import java.util.Scanner;

public class MembersController extends Controller {

    UFCPlayerServiceImpl UFCPlayerServiceImpl;

    public MembersController(UFCPlayerServiceImpl UFCPlayerServiceImpl){
        this.UFCPlayerServiceImpl=UFCPlayerServiceImpl;
    }

    @RequestMapping
        public void home(HttpResponse httpResponse) {

        String str = "<html><body>" +
                "<h1>Member Manager</h1>" +
                "<ul>" +
                "<li><a href='/members'>Member List</a></li>" +
                "<li><a href='/add-member-form'>Add New Member</a></li>" +
                "</ul>" +
                "</body></html>";

        httpResponse.statusCode= Constants.SUCCESSFUL_CODE;
        httpResponse.responseBody=new StringBuilder(str);

    }

    @RequestMapping(requestPath = "/members")
    public void displayList(HttpResponse httpResponse) {

        List<UFCPlayer> ufcPlayerList= UFCPlayerServiceImpl.ufcRepository.listPlayers();

        StringBuilder page = new StringBuilder();
        page.append("<html><body>");
        page.append("<h1>Member List</h1>");
        page.append("<ul>");
        for (UFCPlayer ufcPlayer : ufcPlayerList) {
            page.append("<li>")
                    .append("ID: ").append(ufcPlayer.playerId)
                    .append(", Name: ").append(ufcPlayer.name)
                    .append(", Weight Class: ").append(ufcPlayer.weightClass)
                    .append("</li>");
        }
        page.append("</ul>");
        page.append("<a href='/'>Back to Home</a>");
        page.append("</body></html>");

        httpResponse.statusCode= Constants.SUCCESSFUL_CODE;
        httpResponse.responseBody=new StringBuilder(page);

    }

    @RequestMapping(requestPath = "/add-member-form")
    public void addMemberForm(HttpResponse httpResponse) {

        //UFCPlayerServiceImpl.ufcRepository.addPlayer();

        String body = "<html><body>" +
                "<h1>Add New Member</h1>" +
                "<form method='POST' action='/add-member'>" +
                "Name: <input type='text' name='name'><br>" +
                "Weight: <input type='text' name='weight'><br>" +
                "<input type='submit' value='Add'>" +
                "</form>" +
                "<a href='/'>Back to Home</a>" +
                "</body></html>";

        httpResponse.responseBody = new StringBuilder(body);
        httpResponse.statusCode= Constants.SUCCESSFUL_CODE;


    }

    @RequestMapping(requestPath = "/add-member")
    public void addMember(HttpRequest httpRequest,HttpResponse httpResponse) {

        UFCPlayer ufcPlayer = new UFCPlayer(httpRequest.queryParamsMap.get("name"), httpRequest.queryParamsMap.get("weight"));
        UFCPlayerServiceImpl.ufcRepository.addPlayer(ufcPlayer);

        home(httpResponse);
        //httpResponse.responseBody = new StringBuilder(body);
        //httpResponse.statusCode= Constants.SUCCESSFUL_CODE;


    }

    @RequestMapping(requestPath = RequestConstants.FAVICON)
    public void favicon(HttpRequest httpRequest, HttpResponse httpResponse){
        MyLogger.log("Server Log | Ignoring Http Request "+httpRequest.requestPath);
        httpResponse.statusCode=Constants.SUCCESSFUL_CODE;
    }
}
