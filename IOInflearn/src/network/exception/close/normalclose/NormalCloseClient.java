package network.exception.close.normalclose;

import logging.MyLogger;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class NormalCloseClient {

    public static void main(String args[]) throws IOException, InterruptedException {

      Socket socket = new Socket("localhost",12345);
        MyLogger.log("Client Log: Socket TCP connection established");
      InputStream inputStream = socket.getInputStream();

        readByInputStream(inputStream,socket);
        readByBufferedReader(inputStream,socket);
        readByDataInputStream(inputStream,socket);

        MyLogger.log("Client Log: Socket is terminated. Program exit");

    }

    public static void readByInputStream (InputStream inputStream, Socket socket) throws IOException{

        int i = inputStream.read();
        if(i==-1){
            MyLogger.log("EOF: readByInputStream | "+ i);
            inputStream.close();
            socket.close();
        }
    }

    public static void readByBufferedReader(InputStream inputStream, Socket socket) throws IOException{

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String x = bufferedReader.readLine();
        if(x == null){
            MyLogger.log("EOF: readByBufferedReader | "+ x);
            inputStream.close();
            socket.close();
        }
    }

    public static void readByDataInputStream(InputStream inputStream, Socket socket) throws IOException{

        DataInputStream dataInputStream = new DataInputStream(inputStream);

        try {
            String x = dataInputStream.readUTF();
        }
        catch(IOException e){
            MyLogger.log("Exception occured while read by data input stream: "+e);
        }
    }

}
