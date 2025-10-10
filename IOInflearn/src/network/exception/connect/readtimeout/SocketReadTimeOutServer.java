package network.exception.connect.readtimeout;

import logging.MyLogger;

import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketReadTimeOutServer {
    public static void main(String args[]) throws IOException {

        ServerSocket serverSocket = new ServerSocket(12345);
        MyLogger.log("Server Log | Server socket created: "+serverSocket);

        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        MyLogger.log("Server Log | socket created. Now able to respond to client requests: "+socket);

        try(socket; inputStream;outputStream;serverSocket){
            int request = inputStream.read();
            MyLogger.log("Server Log | server has received a request: "+request);
            Thread.sleep(1000000);

            outputStream.write(1);
            MyLogger.log("Server Log | server has responded back to client: "+1);

        }catch(Exception e){
            System.out.println("Exception occured: "+e);
        }

    }
}
