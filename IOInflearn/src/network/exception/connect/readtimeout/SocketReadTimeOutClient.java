package network.exception.connect.readtimeout;

import logging.MyLogger;

import javax.xml.crypto.Data;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

public class SocketReadTimeOutClient {
    public static void main(String args[]) throws IOException{

            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("localhost",12345),2000);
            MyLogger.log("[Client Log] | TCP Connection established.");

        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();
            try(outputStream;inputStream;socket){
                socket.setSoTimeout(5000);
                outputStream.write(1);
                MyLogger.log("[Client Log] | Client has sent request: " + 1);
                int i= inputStream.read();
                MyLogger.log("[Client Log] | Client has received response: "+i);
            }catch(Exception e){
                System.out.println("Exception occured "+e);
            }

    }

}
