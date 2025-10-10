package network.tcp.v6;

import logging.MyLogger;
import network.tcp.constants.SocketConstants;
import network.tcp.utilities.SocketCloseUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.UUID;

public class SessionV6 implements Runnable{

    public final DataInputStream dis ;
    public final DataOutputStream dos ;
    //Thread thread;
    public final Socket socket;
    UUID sessionId;

    boolean isClosed = false;

    public SessionV6(Socket socket, DataInputStream dis, DataOutputStream dos){
        this.socket=socket;
        this.sessionId= UUID.randomUUID();
        Thread thread=new Thread(this, "Thread: | "+  this.sessionId);
        this.dis=dis;
        this.dos=dos;

        SessionManagerV6.add(this);
        MyLogger.log("[Server Log] | server session created to receive request for the first time: " +this);

        //CompletableFuture<Void> cf1= CompletableFuture.runAsync(this);

        //threadPoolExecutor.submit(this);
        thread.start();
    }
    public boolean handleRequest() throws IOException {

        int selection = dis.readInt();
        //Session Thread gets blocked until it receives a request from client

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

//        try(socket;dis;dos) {
//            while (true) {
//                if (!handleRequest()) {
//                    break;
//                }
//            }
//        }
//        catch(IOException e){
//            MyLogger.log(e);
//        }

        try {
            while (true) {
                if (!handleRequest()) {
                    break;
                }
            }
        }
        catch(IOException e){
            //Session Thread gets SocketClosed Exception when server is terminated while waiting for Client Request
            //Shuntdown hook thread closes Socket, dos, dis closing all resources
            MyLogger.log("Error occured while handling a client request: "+e);
        }
        finally{
            SessionManagerV6.remove(this);
            close();
        }

    }

    public synchronized void close(){
        if(isClosed){
            MyLogger.log("Session already closed by different process!");
            return;
        }
        SocketCloseUtil.closeAll(socket,dos,dis);
        this.isClosed = true;
        MyLogger.log("Resources for session: "+this +" closed. Server session closed!");
        //yLogger.log("Is Socket Closed: "+ socket.isClosed());
    }

    @Override
    public String toString() {
        return "SessionV6{" +
                "dis=" + dis +
                ", dos=" + dos +
                ", socket=" + socket +
                ", sessionId=" + sessionId +
                '}';
    }
}
