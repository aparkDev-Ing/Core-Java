package network.tcp.v1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import logging.MyLogger;

public class NetworkingIOClientExample1 {

    public static int PORT = 12345;

    public static void main(String args[]) throws IOException{

        launchApplication();
    }

    static void launchApplication() throws IOException{
        connect();
    }
    static void connect() throws IOException {

        MyLogger.log("[Client log] | Client Starting");

        Socket socket = new Socket("localhost",PORT);
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        MyLogger.log("[Client log] | Socket established: "+socket);


        request(dos);
        MyLogger.log("[Client log] | Client -> Server:: sent request");

        //int index = new Random().nextInt(3);
        DataInputStream dis = new DataInputStream(socket.getInputStream());

        MyLogger.log("[Client log] | Server-> Client:: response received: " + handleResponse(dis));
        //handleResponse(dis);

        dis.close();
        dos.close();
        socket.close();

    }

    static void request(DataOutputStream dos) throws IOException{
        int request= new Random().nextInt(3);
        MyLogger.log("[Client log] | Client -> Server:: request payload: "+ request);
        dos.writeInt(request);
    }

    static String handleResponse(DataInputStream dis) throws IOException{
        return dis.readUTF();
    }

}
