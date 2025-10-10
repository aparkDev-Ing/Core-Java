package network.tcp.v6;

import logging.MyLogger;

import java.util.ArrayList;
import java.util.List;

public class SessionManagerV6 {

    public static List<SessionV6> sessions = new ArrayList<>();

    public static void add(SessionV6 session){
        synchronized (sessions) {
            sessions.add(session);
            MyLogger.log("Session added to a session manager: "+ session);
        }
    }

    public static void remove(SessionV6 session){
        synchronized (sessions) {
            if(sessions.contains(session)) {
                sessions.remove(session);
                MyLogger.log("Session removed from a session manager: " + session);
            }else{
                MyLogger.log("[Failed to remove] Session is already removed from session manager list by different process: " + session);
            }
        }
    }

    public static void closeAll(){
        synchronized (sessions) {
            if(!sessions.isEmpty()) {
                sessions.stream().forEach(s -> s.close());
                sessions.clear();
                MyLogger.log("All sessions removed from a session manager: " + sessions);
            }else{
                MyLogger.log("Session Manager is already empty " + sessions);
            }
        }
    }

}
