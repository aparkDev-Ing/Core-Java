package network.tcp.v1;

import logging.MyLogger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import network.tcp.constants.SocketConstants;

public class NetworkingIOServerExample1 {
    static List<String> selection = Arrays.asList("Pizza","Taco","Pasta");

    public static int PORT = 12345;

    public static void main(String args[]) throws IOException{

        launchApplication();
    }

    static void launchApplication() throws IOException{
        connect();
    }
    static void connect() throws IOException {

        MyLogger.log("[Server log] | Server Started");

        ServerSocket serverSocket = new ServerSocket(PORT);

        Socket socket = serverSocket.accept();

        MyLogger.log("[Server log] | Server socket connection established: "+socket);


        DataInputStream dis = new DataInputStream(socket.getInputStream());
        int index = receiveRequest(dis);
        MyLogger.log("[Server log] | Server has received request from client: " + index);


        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        respond(dos, index);
        MyLogger.log("[Server log] | Server responded back to client "  );

        //Thread.sleep(1000);

        MyLogger.log("[Server log] | Server closing all connections "  );
        dos.close();
        dis.close();
        socket.close();
        serverSocket.close();

    }

    static void respond(DataOutputStream dos, int index) throws IOException{

        String food = SocketConstants.selection.get(index);
        MyLogger.log("[Server log] | Final Response back to client: " +food );
        dos.writeUTF(food);
    }

    static int receiveRequest(DataInputStream dis) throws IOException{
        return dis.readInt();
    }
}
