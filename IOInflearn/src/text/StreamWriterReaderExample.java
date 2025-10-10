package text;

import efficientfilestream.BufferConstants;

import java.io.*;

public class StreamWriterReaderExample {
    public static void main(String args[]) throws IOException {
        launchApplication();
    }

    public static void launchApplication() throws IOException{

        String word = "Pizza";

        write(word);

        read();
    }



    static String read()throws  IOException{

        System.out.println("Decoding Starting! ");

        FileInputStream fis = new FileInputStream("IOInflearn/temp/streamwriterreadertext.txt");

        InputStreamReader isr = new InputStreamReader(fis,BufferConstants.UTF_8);

        StringBuilder b = new StringBuilder();
        int data = 0;
        
        while((data =isr.read()) != -1){
            b.append((char)data);
        }

        isr.close();

        System.out.println("Decoding ended: "+b);

        return b.toString();
    }

    static void write(String food)throws IOException {

        System.out.println("Starting to encode string: ["+food+"]");
        FileOutputStream fos = new FileOutputStream("IOInflearn/temp/streamwriterreadertext.txt");

        OutputStreamWriter osw = new OutputStreamWriter(fos,BufferConstants.UTF_8);

        osw.write(food);

        osw.close();

        System.out.println("Completed encoding word: ["+food+"]");

    }

  
}
