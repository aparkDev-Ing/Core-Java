package network.chat.userdb;

import network.chat.userdto.*;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase {

    public static List<User> userList = new ArrayList<>();

    public static void add(User user){
        synchronized (userList) {
            userList.add(user);
        }
    }

    public static List<User> returnUser(){
        return userList;
    }

    public void displayUsers(){
        userList.stream().forEach(s-> System.out.println("User: "+ s));
    }

}
