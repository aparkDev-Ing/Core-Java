package network.chat.server.session;


import logging.MyLogger;
import network.chat.constants.Constants;
import network.chat.userdb.UserDatabase;
import network.tcp.utilities.SocketCloseUtil;
import network.chat.userdto.User;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class Session implements Runnable{

    public User registeredUser;
    public Integer sessionId;

    Socket socket;

    DataInputStream dis;

    @Override
    public String toString() {
        return "Session{" +
                "sessionId=" + sessionId +
                ", socket=" + socket +
                ", dis=" + dis +
                ", dos=" + dos +
                ", oos=" + oos +
                ", isClosed=" + isClosed +
                '}';
    }

    DataOutputStream dos;

    ObjectOutputStream oos;

    ObjectInputStream ois;
    boolean isClosed =false;

    public Session(){


    }

    public Session(Socket socket,ObjectOutputStream oos, ObjectInputStream ois){
        this.socket= socket;
        this.oos=oos;
        this.ois= ois;

        sessionId = SessionManager.sessionId.getAndIncrement();

        SessionManager.addSession(this);
        Thread thread = new Thread(this);
        thread.start();
    }
    public Session(Socket socket,ObjectOutputStream oos, DataOutputStream dos, DataInputStream dis){
        this.socket= socket;
        this.oos=oos;
        this.dos=dos;
        this.dis= dis;

        sessionId = SessionManager.sessionId.incrementAndGet();

        SessionManager.addSession(this);
        Thread thread = new Thread(this);
        thread.start();
    }

    public synchronized void close(){
        if(isClosed){
            MyLogger.log("Server Log | Session resources are already closed | Session Id: " +sessionId );
            return;
        }
        SocketCloseUtil.closeAll(socket,dos,dis,oos);
        isClosed=true;
        MyLogger.log("Server Log | Session resources are closed now | Session Id: " +sessionId);
    }

    @Override
    public void run(){
        try {
            while (true) {
                if(handleRequest()){
                    break;
                }
            }
        }
        catch(Exception e){
           MyLogger.log("Exception occurred in session: "+sessionId +" | Exception: "+ e);
        }finally {
            SessionManager.removeSession(this);
            close();
        }
    }

    public boolean handleRequest() throws IOException, ClassNotFoundException {

        String request = ois.readObject().toString();

        if(request.split(":") != null ){

            String[] requestArray= request.split(":");

            //check split behavior first
            MyLogger.log("Server Log | Server received request: "+ Integer.parseInt(requestArray[0]));

            if(requestArray.length>1){
                switch (Integer.parseInt(requestArray[0])){
                    case 1:
                        addUser(requestArray[1]);
                        break;
                    case 2:
                        receiveGroupMessageFromClient(requestArray[1]);
                        break;
                    case 3:
                        changeName(requestArray[1],UUID.fromString(requestArray[2]));
                        break;
                    case 6:
                        receiveSingleMessageFromClient(requestArray[0],requestArray[1],UUID.fromString(requestArray[2]));
                        break;
                }
            }else if(requestArray.length ==1 && Integer.parseInt(requestArray[0]) ==4){
                returnUserList();
            }
            else if(requestArray.length ==1 && Integer.parseInt(requestArray[0]) ==5){
                handleExitRequest();
                return true;
            }
        }

        return false;
    }

    public void addUser(String user) throws IOException{

        User userObject = new User(user);
        this.registeredUser= userObject;
        UserDatabase.add(userObject);
        MyLogger.log("Server Log | User was added to DB: "+user);

        String response = "1:"+user+":"+userObject.getUserId().toString();
        oos.writeObject(response);
        oos.flush();
        MyLogger.log("Server Log | Server responded back to client: " + response);
    }

    public void receiveSingleMessageFromClient(String selection ,String chat, UUID toUserId) throws IOException{

        if(UserDatabase.returnUser()!= null && !UserDatabase.returnUser().isEmpty() ) {
//            UserDatabase.userList.stream().filter(user->user.getUserId().equals(userId)).findFirst().ifPresent((user)->{
//                SessionManager.notifyAllSessions(selection+chat+":"+this.registeredUser.getName());
//            });

           Optional<User> toUserObject = UserDatabase.userList.stream().filter(user->user.getUserId().toString().equals(toUserId.toString())).findFirst();

           if(toUserObject.isPresent()){
               SessionManager.notifySession(selection+":"+chat+":"+ " from user-> "+this.registeredUser.getName()+":"+ " to user-> "+toUserObject.get().getName(), this, toUserId.toString());
           }else{
               MyLogger.log("Server Log | User: " +toUserId +" not found in system. Could not send a message");
               oos.writeObject("selection:"+Constants.userNotFoundErrorMessage);
               oos.flush();
           }

        }else{
            MyLogger.log("User List is empty. Cannot send chat to anyone yet.");
            oos.writeObject("selection:"+Constants.userNotFoundErrorMessage);
            oos.flush();
        }

    }
    public void receiveGroupMessageFromClient(String chat) throws IOException{

        if(UserDatabase.returnUser()!= null && !UserDatabase.returnUser().isEmpty() ) {
            SessionManager.notifyAllSessions("2:"+chat+":"+this.registeredUser.getName());
        }else{
            MyLogger.log("User List is empty. Cannot send chat to anyone yet.");
            oos.writeObject("2:"+Constants.userNotFoundErrorMessage);
            oos.flush();
        }
    }
    public void sendMessage(String chat) throws IOException{
        oos.writeObject(chat);
        oos.flush();
        MyLogger.log("Server Log | Server|SessionId: "+sessionId + " has responded and sent message: "+ chat  );
    }


    public void changeName(String name, UUID userId) {

        Optional<User> optionalUser = UserDatabase.userList.stream().filter(user->user.getUserId().toString().equals(userId.toString())).findFirst();
        User userObject = optionalUser.orElseGet(()->new User());
        optionalUser.ifPresent(user -> {
            MyLogger.log("Server Log | Server|SessionId: "+sessionId + " has received request to change name of user: "+ user);

            user.setName(name);
            MyLogger.log("Server Log | Server|SessionId: "+sessionId + " has changed user name to: "+ name +" | user: "+userObject);

            try {
                oos.writeObject("3:"+name);
                oos.flush();
            }
            catch(IOException e){
                MyLogger.log("Error Occurred: "+e);
            }
            MyLogger.log("Server Log | Server|SessionId: "+sessionId + " has responded back to client: "+ name);


        });

        if(!optionalUser.isPresent()) {

            try {
                String failedMessage =Constants.failedMessageChangeName;
                oos.writeUTF("3:"+failedMessage);
                oos.flush();
                MyLogger.log("Server Log | Server|SessionId: " + sessionId + " has responded back to client: " + failedMessage);
            }
            catch(IOException e){
                MyLogger.log("Error Occurred: "+e);
            }

        }

    }

    public void returnUserList(){
        try {
            List<User> userList = UserDatabase.returnUser();
            if(userList.isEmpty()){
                oos.writeObject("4:"+Constants.userNotFoundErrorMessage);
                oos.flush();
                MyLogger.log("Server Log | Server|SessionId: " + sessionId + " has responded back to client as there is no user found to display");
            }else {
                oos.reset();  // 🔑 Clear the cache before sending
                oos.writeObject(userList);
                oos.flush();
                MyLogger.log("Server Log | Server|SessionId: " + sessionId + " has responded back to client: " + userList);
            }
        }
        catch(IOException e){
            MyLogger.log("Exception occurred: "+e);
        }

    }

    public void handleExitRequest(){

        try {
            oos.writeObject("5:"+Constants.exitResponseMessage);
            oos.flush();
            MyLogger.log("Server Log | Server|SessionId: " + sessionId + " has responded back to client: " + Constants.exitResponseMessage);
        }
        catch(IOException e){
            MyLogger.log("Exception occurred: "+e);
        }
    }




}
