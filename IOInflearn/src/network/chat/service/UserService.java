package network.chat.service;

import logging.MyLogger;
import network.chat.client.UserClientV2;
import network.chat.constants.Constants;
import network.chat.userdb.UserDatabase;
import network.chat.userdto.User;
import network.tcp.utilities.SocketCloseUtil;

import java.io.*;
import java.net.Socket;
import java.util.*;

import static network.chat.userdb.UserDatabase.userList;

public class UserService{

    public static String promptUser(Scanner scan) throws InterruptedException{

        Thread.sleep(200);
        System.out.println("\nPlease choose from following options: " +"1.Join the server | 2.Drop a message to group | 3.Change user name | 4.Display all users | 5.Exit |  6.Send a message to a person");
        String selection = scan.nextLine();
//        scan.nextLine();

        return selection;
    }

    public static void joinServer(Scanner scan, ObjectOutputStream oos, ObjectInputStream ois) throws IOException, ClassNotFoundException {

        System.out.println("Please tell us your name");
        String name = scan.nextLine();

        if(name == null || name.equals("")){
            System.out.println("Invalid input. Failed to add user!");

        }else {
            String request = Constants.selectionList.get(0).toString()+":"+name;
            oos.writeObject(request);
            oos.flush();
            MyLogger.log("Client Log | Client has sent request to server to add new user: " + request);

//            response = name +":"+ois.readObject().toString();
//            MyLogger.log("Client Log | Client has received a response from server: " + response);

        }

    }

    public static void handleJoinServerResponse(String[] responseArray){

       User user = new User(responseArray[1],UUID.fromString(responseArray[2]));
       UserClientV2.user = user;
       UserClientV2.name=responseArray[1];
       MyLogger.log("Client Log | User is assigned on client side: "+ UserClientV2.user);
    }

    public static void sendMessageToGroup(Scanner scan, ObjectOutputStream oos, ObjectInputStream ois) throws IOException, ClassNotFoundException {

        System.out.println("Please give us chat message to send: ");
        String message = scan.nextLine();

        if(message == null || message.equals("")){
            System.out.println("Invalid input. Failed to send message!");

        }else {
            String request = Constants.selectionList.get(1).toString()+":"+message;
            oos.writeObject(request);
            oos.flush();
            MyLogger.log("Client Log | Client has sent request to server to send message to all users: " + request);

//            String chat = ois.readObject().toString();
//            MyLogger.log("Client Log | Client has received a response from server: " + chat);
        }

    }

    public static void sendMessage(Scanner scan, ObjectOutputStream oos) throws IOException, ClassNotFoundException {

        System.out.println("Please give us a name of user to send message to: ");
        String toUser = scan.nextLine();

        System.out.println("Please give us chat message to send: ");
        String message = scan.nextLine();

        if((message == null || message.equals("")) || (toUser== null || toUser.equals(""))){
            System.out.println("Invalid input. Failed to send message!");
        }else {

            Optional<User> toUserObject = UserClientV2.userList.stream().filter(user -> user.getName().equals(toUser)).findFirst();

            if(toUserObject.isPresent()){
                String userId = toUserObject.get().getUserId().toString();
                String request = Constants.selectionList.get(5).toString()+":"+message+":"+userId;
                oos.writeObject(request);
                oos.flush();
                MyLogger.log("Client Log | Client has sent request to server to send message one specific user: " + toUser +" | request: "+request);
            }else{
                MyLogger.log("Client Log | Client was not able to send message as user dose not exist: "+toUser );
            }

        }

    }

    public static void handleSendMessageResponse(String response){
        MyLogger.log("Client Log | Client has received a response from server: " + response);
    }

    public static void changeName(Scanner scan, ObjectOutputStream oos, ObjectInputStream ois, UUID userId) throws IOException, ClassNotFoundException {

        System.out.println("Please give us a name to change to: ");
        String name = scan.nextLine();

        if(name == null || name.equals("")){
            System.out.println("Invalid input. Failed to send message!");

        }else {
            oos.writeObject(Constants.selectionList.get(2).toString()+":"+name+":"+userId.toString());
            oos.flush();
            MyLogger.log("Client Log | Client has sent request to server to change name: " + name);

        }

    }

    public static void handleChangeNameResponse(String response){
        if(response.equals(Constants.failedMessageChangeName)){
            MyLogger.log("Client Log | Client has received a failed message from server: " + response);
            //return null;
        }else if(response != null && response!="") {
            UserClientV2.user.setName(response);
            UserClientV2.name = response;
            MyLogger.log("Client Log | Client has received a response from server: " + response +" Client user is updated as well." + UserClientV2.user);
        }
    }

    public static void displayUsers(ObjectOutputStream oos, ObjectInputStream ois) throws IOException, ClassNotFoundException {
            oos.writeObject(Constants.selectionList.get(3).toString());
            oos.flush();
            MyLogger.log("Client Log | Client has sent request to server to display all user names " );

    }

    public static void exitRequest(ObjectOutputStream oos, ObjectInputStream ois) throws IOException, ClassNotFoundException {

        oos.writeObject(Constants.selectionList.get(4).toString());
        oos.flush();
        MyLogger.log("Client Log | Client has sent request to exit a program. " );

    }


    //response receiver thread
    public static void receiveResponse(ObjectInputStream ois, ObjectOutputStream oos, Socket socket,Scanner scan) {
        try {
            //MyLogger.log("Client Log | Listener Thread Starts");
            while(true) {

                Object response = ois.readObject();
                if (response instanceof String) {

                    String strResponse = response.toString();
                    String[] strResponseArray = strResponse.split(":");

                    if (strResponseArray[0].equals("1")) {
                        MyLogger.log("Client Log | Client has received a response after adding a user: " + Arrays.toString(strResponseArray));
                        handleJoinServerResponse(strResponseArray);

                    } else if (strResponseArray[0].equals("2")) {
                        MyLogger.log("Client Log | Client has received a message from a user: "+strResponseArray[2] +" | response -> "+ Arrays.toString(strResponseArray));
                        handleSendMessageResponse(strResponseArray[1]);
                    } else if (strResponseArray[0].equals("3")) {
                        MyLogger.log("Client Log | Client has received a response after changing a name: " + Arrays.toString(strResponseArray));
                        handleChangeNameResponse(strResponseArray[1]);
                    } else if (strResponseArray[0].equals("4")) {
                            MyLogger.log("Client Log | Client has received a empty response from server as there is no active user registered: " +Arrays.toString(strResponseArray));
                    }
                    else if (strResponseArray[0].equals("5")) {
                        MyLogger.log("Client Log | Client has received a response after server has closed socket connection: " + Arrays.toString(strResponseArray));
                        break;
                    }else if (strResponseArray[0].equals("6")) {

                        if(strResponseArray.length==4) {
                            MyLogger.log("Client Log | Client has received a message from a user: " + strResponseArray[2] + " | response -> " + Arrays.toString(strResponseArray));
                            handleSendMessageResponse(strResponseArray[1]);
                        }else{
                            MyLogger.log("Client Log | Client has received a message from a server: "+ Arrays.toString(strResponseArray));
                            //handleSendMessageResponse(strResponseArray[1]);
                        }
                    }


                } else if (response instanceof List<?>) {
                    List<User> userList = (List<User>) response;

                    UserClientV2.userList=userList;
                        MyLogger.log("Client Log | Client has received a response from server: ->");
                        userList.stream().forEach(s -> System.out.println("User: " + s));

                }
            }
        }
        catch(Exception e){
            MyLogger.log("Client Log | Exception Occurred while receiving a response from server: "+e);
        }finally{
            SocketCloseUtil.closeAll(socket,oos,ois);
            scan.close();
        }

    }


}
