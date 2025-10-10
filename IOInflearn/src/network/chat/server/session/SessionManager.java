package network.chat.server.session;

import logging.MyLogger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SessionManager {

    public static AtomicInteger sessionId = new AtomicInteger(0);
    public static List<Session> sessionList = new ArrayList<>();

    public static void addSession(Session session){
        synchronized (sessionList) {
            sessionList.add(session);
            MyLogger.log("Server Log | Session is added: " + session);
        }
    }

    public static void removeSession(Session session){
        synchronized (sessionList) {
            if(sessionList.contains(session)) {
                sessionList.remove(session);
                MyLogger.log("Server Log | Session is removed");
            }else{
                MyLogger.log("Server Log | Session is already removed.");
            }

        }
    }

    public static void notifySession(String chat, Session currentsession, String toUserId) {
        synchronized (sessionList) {
            sessionList.stream().filter(session->session.registeredUser.getUserId().toString().equals(toUserId)).findFirst().ifPresent((session)->{
                try {
                    session.sendMessage(chat);
                    currentsession.sendMessage(chat+ ": Server Response-> server has successfully sent message to the user-> "+session.registeredUser.getName());
                }
                catch(IOException e){
                    MyLogger.log("Server Log | exception occured: " + e);
                }
            });

        }
    }
    public static void notifyAllSessions(String chat) {
        synchronized (sessionList) {
           sessionList.stream().forEach(s -> {
               try {
                   s.sendMessage(chat);
               }
               catch(IOException e){
                   MyLogger.log("Server Log | exception occured: " + e);
               }
           } );
        }
    }
    public static void clearSession(){

        synchronized (sessionList) {
            if (!sessionList.isEmpty()) {
                sessionList.stream().forEach(s -> s.close());
                MyLogger.log("Server Log | All Sessions are closed: " + sessionList);
                sessionList.clear();
            } else {
                MyLogger.log("Server Log | Nothing to close. Session Manager is already empty: " + sessionList);
            }
        }

    }



}
