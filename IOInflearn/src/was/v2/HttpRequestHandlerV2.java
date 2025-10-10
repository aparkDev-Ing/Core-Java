package was.v2;

import was.utility.impl.ServerUtilityV1;

import java.net.Socket;

public class HttpRequestHandlerV2 implements Runnable{

    ServerUtilityV1 serverUtilityV1 = new ServerUtilityV1();
    int threadId ;

    public Socket socket;

    public HttpRequestHandlerV2(Socket socket){
        this.socket=socket;
        this.threadId= HttpServerV2.id.getAndIncrement();

        Thread thread = new Thread(this,"HTTP Request Handler "+ this.threadId);
        thread.start();
    }

    @Override
    public void run(){
        serverUtilityV1.process(socket);
    }

}
