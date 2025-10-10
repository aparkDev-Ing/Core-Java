package network.exception.connect.unknownhost;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectMain {
    public static void main(String args[]) throws IOException{

        //unknownHostException1();
        unknownHostException2();
        //connectException();

    }

    //Unknown host exception occurs as 9999.99.99.99 is unknown host (IP)
    public static void unknownHostException1() throws IOException {

        try{
         Socket socket = new Socket("9999.99.99.99", 12345);
        }
        catch(UnknownHostException e){
            System.out.println("Unknow host: "+e);
            throw new RuntimeException(e);
        }

    }

    //Unknown host exception occurs as "google.coom" is unknown host/DNS
    public static void unknownHostException2() throws IOException {

        try{
            Socket socket = new Socket("google.coom", 12345);
        }
        catch(UnknownHostException e){
            System.out.println("Unknow host: "+e);
            throw new RuntimeException(e);
        }

    }


    //Connection refused as this port is not in use. Connection refused means it did find the host
    // and but server refused to connect as there is no such port
    //서버 컴퓨터의 OS는 이때 TCP RST(Reset)라는 패킷을 보내서 연결을 거절한다.
    //클라이언트가 연결 시도 중에 RST 패킷을 받으면 이 예외가 발생한다.
    public static void connectException() throws IOException {

        try{
            Socket socket = new Socket("localhost", 45678);
        }
        catch(ConnectException e){
            System.out.println("Connection Refused: "+e);
            throw new RuntimeException(e);
        }

    }
}
