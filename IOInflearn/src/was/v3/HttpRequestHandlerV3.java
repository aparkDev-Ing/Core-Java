package was.v3;

import was.utility.impl.ServerUtilityV1;

import java.net.Socket;

public class HttpRequestHandlerV3 implements Runnable{
    ServerUtilityV1 serverUtilityV1 = new ServerUtilityV1();
    int threadId ;
    public Socket socket;
    public HttpRequestHandlerV3(Socket socket){
        this.socket=socket;
        this.threadId= HttpServerV3.id.getAndIncrement();
    }

    @Override
    public void run(){
        serverUtilityV1.process(socket);
    }

}
