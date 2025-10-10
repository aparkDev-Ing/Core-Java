package network.exception.connect.connecttimeout;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ConnectTimeOutExceptionExample2 {

    public static void main(String args[])throws IOException {

        //SocketTimeoutException: Connect timed out
        long start = System.currentTimeMillis();
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("192.168.1.250", 45678),10000);
        }
        catch(ConnectException e){
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();

        System.out.println("Time it took: "+(end-start));
    }
}
