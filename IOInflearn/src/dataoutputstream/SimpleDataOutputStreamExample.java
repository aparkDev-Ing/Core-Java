package dataoutputstream;

import java.io.*;

public class SimpleDataOutputStreamExample {

    public static void main(String args[])throws IOException {

        FileOutputStream fos = new FileOutputStream("IOInflearn/temp/dataoutstream example.txt");
        DataOutputStream dos = new DataOutputStream(fos);

        dos.writeDouble(10.5);
        //dos.writeInt(10);
        dos.writeUTF("가나다라ABCD");

        dos.close();

        FileInputStream fis = new FileInputStream("IOInflearn/temp/dataoutstream example.txt");
        DataInputStream dis = new DataInputStream(fis);

        dis.close();
        System.out.println(dis.readDouble());
        //dos.writeInt(10);
        System.out.println(dis.readUTF());
    }
}
