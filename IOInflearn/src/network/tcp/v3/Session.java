package network.tcp.v3;

import logging.MyLogger;
import network.tcp.constants.SocketConstants;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Comparator;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Session implements Runnable{

    public DataInputStream dis ;
    public DataOutputStream dos ;
    //Thread thread;
    Socket socket;
    UUID sessionId;
    //static ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(4);


    public Session(Socket socket, DataInputStream dis, DataOutputStream dos){
        this.socket=socket;
        this.sessionId= UUID.randomUUID();
        Thread thread=new Thread(this, "Thread: | "+ this.sessionId);
        this.dis=dis;
        this.dos=dos;
        MyLogger.log("[Server Log] | server session created to receive request for the first time: " +socket);

        //CompletableFuture<Void> cf1= CompletableFuture.runAsync(this);

        //threadPoolExecutor.submit(this);
        thread.start();
    }
    public boolean handleRequest() throws IOException {


        int selection = dis.readInt();

        MyLogger.log("[Server Log] | server has received a request: " +selection);

        if(selection == 3){
            MyLogger.log("[Server Log] | Exit request from client: " +selection);
            return false;
        }


        String food = SocketConstants.selection.get(selection);


        dos.writeUTF(food);
        MyLogger.log("[Server Log] | server has responded back: " +food);

        return true;
    }

    public void closeServerConnection() throws IOException{
        MyLogger.log("[Server Log] | server closing socket and resources" );
        dis.close();
        dos.close();
        socket.close();
        //threadPoolExecutor.shutdown();

    }
    @Override
    public void run(){

        while(true){
            try {
                if(!handleRequest()){
                    break;
                }
            }
            catch(IOException e){
                throw new RuntimeException(e);
            }
        }

        try {
            closeServerConnection();
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
