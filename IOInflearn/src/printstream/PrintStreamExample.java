package printstream;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class PrintStreamExample {

    public static void main(String args[])throws Exception{

        //System.out.println();

        PrintStream printStream = System.out;

        //byte[] byteArray = {65,66,67,68,69};

        byte[] encodedArray = "Hello".getBytes(StandardCharsets.UTF_8);

        System.out.println("\'Hello\' encoded to: "+ Arrays.toString(encodedArray));
        printStream.write(encodedArray);
        System.out.println("");

        String decodedString = new String(encodedArray,StandardCharsets.UTF_8);
        System.out.println("Decoded String: "+decodedString);
        //System.out.println(decodedString);
        //printStream.write(byteArray,0,3);



    }
}
