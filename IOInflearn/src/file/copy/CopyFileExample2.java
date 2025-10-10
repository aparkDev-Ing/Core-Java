package file.copy;

import efficientfilestream.BufferConstants;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFileExample2 {

    static String sourcePath = "IOInflearn/temp/existing file with transferto.dat";
    static String destinationPath = "IOInflearn/temp/new copied and pasted file with transferto.dat";
    public static void main(String args[]) throws IOException{

//        long startTime = System.currentTimeMillis();
        writeFile();

//        long writeFileEndTime = System.currentTimeMillis();

//        System.out.println("Time it took to complete write : "+ (writeFileEndTime - startTime) +" ms");

        long startTime = System.currentTimeMillis();


        copyandPaste();

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
    static void copyandPaste() throws IOException {
        FileInputStream fis = new FileInputStream(sourcePath);
        FileOutputStream fos = new FileOutputStream(destinationPath);

       fis.transferTo(fos);

        fis.close();
    }

//    static void paste(byte[] copiedData)  throws IOException {
//        FileOutputStream fos = new FileOutputStream(destinationPath);
//
//        fos.write(copiedData);
//
//        fos.close();
//    }
}

