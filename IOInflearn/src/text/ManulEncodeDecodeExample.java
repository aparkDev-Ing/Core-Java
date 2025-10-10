package text;

import efficientfilestream.BufferConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ManulEncodeDecodeExample {

    public static void main(String args[]) throws IOException{
        launchApplication();
    }

    public static void launchApplication() throws IOException{

        String word = "Pizza";

        encode(word);

        decode();
    }

    static void encode(String food) throws IOException{

        System.out.println("Starting to encode string: ["+food+"]");
        byte[] writeByte = food.getBytes(BufferConstants.UTF_8);

        write(writeByte);

        System.out.println("Encoding Completed!");
    }

    static String read()throws  IOException{

        FileInputStream fis = new FileInputStream("IOInflearn/temp/manualencodedecode.txt");

        byte[] readByte= fis.readAllBytes();
        System.out.println("Starting to decode a byte: ["+ Arrays.toString(readByte)+"]");

        fis.close();

        return new String(readByte,BufferConstants.UTF_8);
    }

    static void write(byte[] writeByte)throws IOException {

        FileOutputStream fos = new FileOutputStream("IOInflearn/temp/manualencodedecode.txt");

        fos.write(writeByte);

        fos.close();

    }

    static String decode() throws IOException{

        String decodedWord = read();
        System.out.println("Decoding completed: ["+decodedWord+"]");
        return decodedWord;
    }
}
