package network.exception.connect.connecttimeout;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

public class ConnectTimeOutExceptionExample1 {

    public static void main(String args[])throws IOException {

        //Connect Exception : Operation timed out
        // OS Level timeout setting:
//        TCP 연결을 시도했는데 연결 응답이 없다면 OS에는 연결 대기 타임아웃이 설정되어 있다.
//        Windows: 약 21초
//        Linux: 약 75초에서 180초 사이
//        mac test 75초
        long start = System.currentTimeMillis();
        try {
            Socket socket = new Socket("192.168.1.250", 45678);
        }
        catch(ConnectException e){
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();

        System.out.println("Time it took: "+(end-start));
    }
}
