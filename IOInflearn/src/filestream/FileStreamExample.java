package filestream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class FileStreamExample {


    static Charset UTF_8 = StandardCharsets.UTF_8;
    static int dictionarySize= 10;
    static int batchSize= 5;
    static void startApplication() throws Exception{

        writeDictionary();

        readDictionaryWithBatch(batchSize);
        //readDictionary();
    }

    static void writeDictionary() throws Exception{

        FileOutputStream fos = new FileOutputStream("IOInflearn/temp/dictionary.dat");

        byte[] byteArray = new byte[dictionarySize];
        byte a = 65;
        for(int i =0; i<dictionarySize; i++){
            byteArray[i] = a;
            a++;
        }
        fos.write(byteArray);

        fos.close();

    }

    static void readDictionaryWithBatch(int batchSize) throws Exception{

        FileInputStream fis = new FileInputStream("IOInflearn/temp/dictionary.dat");

        byte[] byteArray = new byte[batchSize];

//        for(int i =0; i<dictionarySize;i+=batchSize) {
//
//            int count = fis.read(byteArray);
//
//            System.out.println("Count of read: " + count);
//            System.out.println(Arrays.toString(byteArray));
//        }

        int count;
        int iteartion = 1;

        while( (count = fis.read(byteArray,0,batchSize)) != -1 ){

            System.out.println("Count of read: " + count);
            System.out.println("Iteration: "+ iteartion +" " + Arrays.toString(byteArray));
            System.out.println("Iteration: "+ iteartion +" in string: " + decode(byteArray,UTF_8));
            iteartion++;
        }

        fis.close();

    }

    static void readDictionary() throws Exception{

        FileInputStream fis = new FileInputStream("IOInflearn/temp/dictionary.dat");

        byte[] byteArray = fis.readAllBytes();

        System.out.println("All characters in dictionary: "+ Arrays.toString(byteArray));

        System.out.println("All characters in dictionary [String]: "+ decode(byteArray,UTF_8));

        fis.close();

    }

    static String decode(byte[] encodedString, Charset standard){

        String decodedString = new String(encodedString, standard);

        return decodedString;
    }

    public static void main(String args[]) throws Exception{

        startApplication();
    }
}
