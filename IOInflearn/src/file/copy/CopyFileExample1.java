package file.copy;

import efficientfilestream.BufferConstants;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFileExample1 {

    static String sourcePath = "IOInflearn/temp/existing file with fos fis traditional.dat";
    static String destinationPath = "IOInflearn/temp/new copied and pasted file with fos fis traditional.dat";
    public static void main(String args[]) throws IOException{

//        long startTime = System.currentTimeMillis();
        writeFile();

//        long writeFileEndTime = System.currentTimeMillis();

//        System.out.println("Time it took to complete write : "+ (writeFileEndTime - startTime) +" ms");

        long startTime = System.currentTimeMillis();
        byte[] copiedData = copy();

        paste(copiedData);

        long endTime = System.currentTimeMillis();

        System.out.println("Time it took to complete: "+ (endTime - startTime) +" ms");
        System.out.println("File Size: "+ BufferConstants.FILE_SIZE2/1024/1024 +" mb");
    }

    static void writeFile()throws IOException{
        FileOutputStream fos = new FileOutputStream(sourcePath);

        byte[] writeByte = new byte[BufferConstants.FILE_SIZE2];

        fos.write(writeByte);

        fos.close();
    }
    static byte[] copy() throws IOException {
        FileInputStream fis = new FileInputStream(sourcePath);

        byte[] dataByte = fis.readAllBytes();

        fis.close();
        return dataByte;
    }

    static void paste(byte[] copiedData)  throws IOException {
        FileOutputStream fos = new FileOutputStream(destinationPath);

        fos.write(copiedData);

        fos.close();
    }
}

