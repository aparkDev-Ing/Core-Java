package was.v4;


import was.utility.impl.ServerUtilityV4;

import java.net.Socket;

public class HttpRequestHandlerV4 implements Runnable{
    ServerUtilityV4 serverUtilityV4 = new ServerUtilityV4();
    int threadId ;
    public Socket socket;
    public HttpRequestHandlerV4(Socket socket){
        this.socket=socket;
        this.threadId= HttpServerV4.id.getAndIncrement();
    }

    @Override
    public void run(){
        serverUtilityV4.process(socket);
    }



}
