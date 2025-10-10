package printstream;

import efficientfilestream.BufferConstants;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

public class PrintStreamExample2 {

    public static void main(String args[])throws IOException {

        FileOutputStream fos = new FileOutputStream("IOInflearn/temp/Print stream example.dat");

        PrintStream ps = new PrintStream(fos);

//        PrintStream printStream = System.out;
//
//        printStream.write(5);
//
//        printStream.close();

        ps.println(5);
        ps.println("안녕");
        ps.write(5);
        ps.close();

    }
}
