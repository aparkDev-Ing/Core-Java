package network.tcp.v4;

import logging.MyLogger;
import network.tcp.constants.SocketConstants;
import network.tcp.utilities.SocketCloseUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.UUID;

public class SessionV4 implements Runnable{

    public DataInputStream dis ;
    public DataOutputStream dos ;
    //Thread thread;
    Socket socket;
    UUID sessionId;
    //static ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(4);


    public SessionV4(Socket socket, DataInputStream dis, DataOutputStream dos){
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


    @Override
    public void run(){

        try {
            while (true) {

                if (!handleRequest()) {
                    break;
                }
            }
        }
        catch(IOException e){
            MyLogger.log(e);
        }
        finally {
            SocketCloseUtil.closeAll(socket,dos,dis);
        }

//        try {
//            closeServerConnection();
//        }
//        catch(IOException e){
//            throw new RuntimeException(e);
//        }
    }
}
