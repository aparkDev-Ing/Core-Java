package file.copy;

import efficientfilestream.BufferConstants;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class CopyFileExample3 {

    static String sourcePath = "IOInflearn/temp/existing file with Files.COPY.txt";
    static String destinationPath = "IOInflearn/temp/new copied and pasted with Files.COPY.txt";
    public static void main(String args[]) throws IOException{

//        long startTime = System.currentTimeMillis();
        writeFile1();

//        long writeFileEndTime = System.currentTimeMillis();

//        System.out.println("Time it took to complete write : "+ (writeFileEndTime - startTime) +" ms");

        long startTime = System.currentTimeMillis();

        copyandPaste();

        long endTime = System.currentTimeMillis();

        System.out.println("Time it took to complete: "+ (endTime - startTime) +" ms");
        System.out.println("File Size: "+ BufferConstants.FILE_SIZE2/1024/1024 +" mb");
    }

    static void writeFile1()throws IOException{

        byte[] writeByte = new byte[BufferConstants.FILE_SIZE2];

        Files.write(Path.of(sourcePath),writeByte);

    }

//    static void writeFile2()throws IOException{
//        FileOutputStream fos = new FileOutputStream(sourcePath);
//
//        byte[] writeByte = new byte[BufferConstants.FILE_SIZE2];
//
//        fos.write(writeByte);
//
//        fos.close();
//    }
    static void copyandPaste() throws IOException {
        Files.copy(Path.of(sourcePath),Path.of(destinationPath), StandardCopyOption.REPLACE_EXISTING);
    }


}

