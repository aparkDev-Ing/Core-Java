package efficientfilestream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteReadAllExample {

    public static void main(String args[]) throws IOException {


        //writeAll();
        //readAll();
        readWithBytes();
    }

    static void writeAll() throws IOException{
        FileOutputStream fos = new FileOutputStream("IOInflearn/temp/writereadllall.dat");

        byte[] writeAll = new byte[BufferConstants.FILE_SIZE];

        long startTime = System.currentTimeMillis();
        for(int i = 0; i<BufferConstants.FILE_SIZE;i++){
            writeAll[i] = 5;
        }

        fos.write(writeAll);
        fos.close();
        long endTime = System.currentTimeMillis();

        System.out.println("Time it took to complete: "+ (endTime - startTime)+" ms");
        System.out.println("File Size: "+ (BufferConstants.FILE_SIZE/1024/1024) + " MB");


    }
    static void readAll() throws IOException{
        FileInputStream fis = new FileInputStream("IOInflearn/temp/writereadllall.dat");

        byte[] readAll = new byte[BufferConstants.FILE_SIZE];

        long startTime = System.currentTimeMillis();

        readAll = fis.readAllBytes();
        fis.close();
        long endTime = System.currentTimeMillis();

//        for(byte a: readAll){
//            System.out.println(a);
//        }

        System.out.println("Time it took to complete: "+ (endTime - startTime)+" ms");
        System.out.println("File Size: "+ (readAll.length/1024/1024) + " MB");


    }

    static void readWithBytes() throws IOException{
        FileInputStream fis = new FileInputStream("IOInflearn/temp/writereadllall.dat");

        byte[] readAll = new byte[BufferConstants.FILE_SIZE];

        long startTime = System.currentTimeMillis();

        int size = 0;
        while((size=fis.read(readAll,0,readAll.length)) != -1){
            //System.out.println("size: "+size);
            //System.out.println("how many times");
        }
        fis.close();
        long endTime = System.currentTimeMillis();

//        for(byte a: readAll){
//            System.out.println(a);
//        }

        System.out.println("Time it took to complete: "+ (endTime - startTime)+" ms");
        System.out.println("File Size: "+ (size/1024/1024) + " MB");


    }
}
