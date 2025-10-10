package efficientfilestream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;

public class EfficientFileStreamExample {
    public static void main(String args[]) throws IOException {


        readWithBatch();
    }

    static void writeWithBatch1() throws IOException{
        FileOutputStream fos = new FileOutputStream("IOInflearn/temp/efficientfile.dat");

        byte[] batchByte = new byte[BufferConstants.BUFFER_SIZE];
        int currIndex = 0;
        for(int i =0; i< BufferConstants.FILE_SIZE;i++){
            batchByte[currIndex++] = 1;

            if(currIndex == BufferConstants.BUFFER_SIZE){
                fos.write(batchByte,0,currIndex);
                currIndex=0;
            }
        }

        //System.out.println(currIndex);

        if(currIndex >0){
            System.out.println("curr index: "+currIndex);
            System.out.println(batchByte.length);
            //fos.write(batchByte,0,batchByte.length);
            fos.write(batchByte,0,currIndex);
        }
        fos.close();
    }

    static void readWithBatch() throws IOException{
        FileInputStream fis = new FileInputStream("IOInflearn/temp/efficientfile.dat");

        byte[] batchByte = new byte[BufferConstants.BUFFER_SIZE];

        int totalSize=0;
        int size= 0;
        while((size = fis.read(batchByte))!=-1){
            System.out.println("Size of each batch "+ size);
            totalSize+= size;
        }

        System.out.println("Total size of file: "+ totalSize/1024/1024 +" MB");

        fis.close();
    }



}
