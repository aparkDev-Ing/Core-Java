package network.exception.close.forceclose;

import logging.MyLogger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ForceCloseClient {

    public static void main(String args[]) throws IOException,InterruptedException {

        Socket socket = new Socket("localhost",12345);
        MyLogger.log("Client Log | Socket Established");
        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();
        Thread.sleep(2000);

        outputStream.write(1);
        MyLogger.log("Client Log | Client sent request after receiving FIN: "+1);

        Thread.sleep(2000);
        try {
            //throws java.net.SocketException: Connection reset
            int i = inputStream.read();
            //outputStream.write(1);
        }
        catch(IOException e){
            MyLogger.log("Client Log | Error Occured while reading after receiving RST: "+e);
            e.printStackTrace();
        }

        try {
            MyLogger.log("Client Log | Client trying to send one mor request after getting RST Exception ");
            outputStream.write(1);
        }
        catch(IOException e){
            //throws java.net.SocketException: Broken pipe
            MyLogger.log("Client Log | Error Occured while getting RST Exception: "+e);
            e.printStackTrace();
        }finally{
            inputStream.close();
            outputStream.close();
            socket.close();
        }

        try {
            inputStream.read();
        }
        catch(IOException e){
            MyLogger.log("Client Log | Error Occured trying to read after connection closed: "+e);
            e.printStackTrace();
        }

        try {
            outputStream.write(1);
        }
        catch(IOException e){
            MyLogger.log("Client Log | Error Occured trying to write after connection closed: "+e);
            e.printStackTrace();
        }

    }
}
