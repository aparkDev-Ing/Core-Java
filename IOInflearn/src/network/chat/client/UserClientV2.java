package network.chat.client;

import logging.MyLogger;
import network.chat.constants.Constants;
import network.chat.service.UserService;
import network.chat.userdto.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class UserClientV2 {

     static public String name =null;
     static public User user = null;

     static public List<User> userList = new ArrayList<>();

    public static void main(String args[]){
        launchApplication();
    }

    public static void launchApplication(){

        try(Socket socket = new Socket(Constants.localhost,Constants.port); ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream()); ObjectInputStream ois = new ObjectInputStream(socket.getInputStream()); Scanner scan = new Scanner(System.in); ){
            MyLogger.log("Client Log | TCP Connection established" );

            new Thread(()->{
                UserService.receiveResponse(ois,oos,socket,scan);
            },"Listener Thread").start();

            while(true) {
                String response = UserService.promptUser(scan);
                int i = 0;
                try{
                    i = Integer.parseInt(response);
                }catch(Exception e){
                    MyLogger.log("Client Log | Exception occurred while getting Client input for selection: "+ e);
                    System.out.println("Invalid input. Please enter an only number!");
                    continue;
                }

                switch (i){
                    case 1:
                        if(name == null || user ==null) {
                            UserService.joinServer(scan, oos, ois);
                            //System.out.println(user);
                        }else{
                            MyLogger.log("Client Log | User already registered: "+user);
                        }
                        break;
                    case 2:
                        if(name != null && user !=null) {
                            UserService.sendMessageToGroup(scan, oos, ois);
                        }else{
                            MyLogger.log("Client Log | User not registered yet. Try again later after registration.");
                        }
                        break;
                    case 3:
                        if(user!=null && name != null) {
                            UserService.changeName(scan, oos, ois, user.getUserId());
                        }else{
                            MyLogger.log("Client Log | User is not yet registered. Try again after User Registration");
                        }
                        break;
                    case 4:
                        UserService.displayUsers(oos,ois);
                        break;
                    case 5:
                        System.out.println("Terminating a program. Thank you!");
                        UserService.exitRequest(oos,ois);
                        break;
                    case 6:
                        UserService.sendMessage(scan,oos);
                        break;
                    default:
                        System.out.println("Invalid Selection. Please try again!");
                        break;
                }

                if(i == 5){
                    break;
                }
            }
        }catch(IOException e){
            MyLogger.log("Client Log | Exception occurred: "+e);
            throw new RuntimeException(e);
        }
        catch(ClassNotFoundException e){
            MyLogger.log("Client Log | Exception occurred: "+e);
            throw new RuntimeException(e);
        }
        catch(InterruptedException e){
            MyLogger.log("Client Log | Exception occurred: "+e);
        }
    }

}
