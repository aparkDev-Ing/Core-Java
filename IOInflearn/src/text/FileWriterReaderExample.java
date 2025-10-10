package text;

import efficientfilestream.BufferConstants;

import java.io.*;
import java.util.Arrays;

public class FileWriterReaderExample {

    public static void main(String args[]) throws IOException {
        launchApplication();
    }

    public static void launchApplication() throws IOException{

        String word = "Pizza";

        write(word);

        read();
    }



    static String read()throws  IOException{

        System.out.println("Starting to read a word");
        FileReader fr = new FileReader("IOInflearn/temp/filewritertext.txt",BufferConstants.UTF_8);

        StringBuilder x = new StringBuilder();
        int data = 0;
        while((data = fr.read()) != -1 ){
            //System.out.println((char)data);
            x.append((char) data);
        }


        fr.close();

        System.out.println("Word from a file: "+ x);

        return x.toString();
    }

    static void write(String food)throws IOException {

        System.out.println("Starting to write a word to file: "+food);
        FileWriter fw = new FileWriter("IOInflearn/temp/filewritertext.txt",BufferConstants.UTF_8);

        fw.write(food);

        fw.close();
        System.out.println("Write Completed: "+food);

    }


}
