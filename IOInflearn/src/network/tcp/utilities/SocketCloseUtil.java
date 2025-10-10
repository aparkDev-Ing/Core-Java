package network.tcp.utilities;

import logging.MyLogger;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class SocketCloseUtil {

    public static void closeAll(Socket socket, OutputStream outputStream, InputStream inputStream){
        close(outputStream);
        close(inputStream);
        close(socket);
    }

    public static void closeAll(Socket socket, OutputStream outputStream, InputStream inputStream, ObjectOutputStream objectOutputStream){
        close(outputStream);
        close(inputStream);
        close(objectOutputStream);
        close(socket);
    }

    public static<T extends Closeable> void close(T object){

        if(object != null){

            try {
                object.close();
                MyLogger.log("Closing a resource: "+ object);
            }
            catch(IOException e){
                MyLogger.log(e);
            }
        }

    }

}
