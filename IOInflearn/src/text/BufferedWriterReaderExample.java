package text;

import efficientfilestream.BufferConstants;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BufferedWriterReaderExample {

    public static void main(String args[]) throws IOException {
        launchApplication();
    }

    public static void launchApplication() throws IOException{

     
//        String word = "Taco\nChicken";
//
//        write(word);
//
//        read();
    }



    static String read()throws  IOException{

        System.out.println("Starting to read a word");
        FileReader fr = new FileReader("IOInflearn/temp/Buffered Writer Text.txt",BufferConstants.UTF_8);
        BufferedReader br = new BufferedReader(fr,BufferConstants.BUFFER_SIZE);

        StringBuilder x = new StringBuilder();

        String line;
        while((line = br.readLine()) != null ){
            x.append(line).append("\n");
        }

        br.close();

        System.out.println("Word from a file: "+ x);

        return x.toString();
    }

    static void write(String food)throws IOException {

        System.out.println("Starting to write a word to file: "+food);
        FileWriter fw = new FileWriter("IOInflearn/temp/Buffered Writer Text.txt", BufferConstants.UTF_8);
        BufferedWriter bw = new BufferedWriter(fw,BufferConstants.BUFFER_SIZE);

        bw.write(food);

        bw.close();
        System.out.println("Write Completed: "+food);

    }
}
