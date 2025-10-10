package efficientfilestream;

import java.io.*;
import java.sql.SQLOutput;

public class BufferedStreamExample {
    public static void main(String args[]) throws IOException{

        //writeInBuffer();
        readBuffer();
    }


    static void writeInBuffer() throws IOException {

        FileOutputStream fos = new FileOutputStream("IOInflearn/temp/bufferedfile.dat");
        BufferedOutputStream bos = new BufferedOutputStream(fos,BufferConstants.BUFFER_SIZE);

        long startTime = System.currentTimeMillis();
        for(int i = 0; i < BufferConstants.FILE_SIZE;i++){
            bos.write(3);
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Time it took to complete: "+ (endTime-startTime) +"ms");
        bos.close();
    }

    static void readBuffer() throws IOException{

        FileInputStream fis = new FileInputStream("IOInflearn/temp/bufferedfile.dat");
        BufferedInputStream bis = new BufferedInputStream(fis,BufferConstants.BUFFER_SIZE);

        long startTime = System.currentTimeMillis();


        int fileSize= 0;
        int data = 0;
        while((data = bis.read())!= -1){
            //System.out.println(size);
          fileSize++;
            //System.out.println(data);
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Total file size: "+ fileSize/1024/1024 +" MB");
        System.out.println("Time it took to complete: "+ (endTime-startTime) +"ms");
        bis.close();
    }

}
