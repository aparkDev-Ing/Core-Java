package network.tcp.v1;

import java.io.IOException;
import java.net.InetAddress;

public class InetAddressExample {
    public static void main(String args[]) throws IOException {
        InetAddress localhost = InetAddress.getByName("localhost");
        InetAddress googleIP = InetAddress.getByName("google.com");

        System.out.println("Local host: "+ localhost);
        System.out.println("Google: "+ googleIP);
    }
}
