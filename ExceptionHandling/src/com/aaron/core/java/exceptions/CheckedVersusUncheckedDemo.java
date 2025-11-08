package com.aaron.core.java.exceptions;
import com.sun.xml.internal.bind.v2.runtime.output.StAXExStreamWriterOutput;

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CheckedVersusUncheckedDemo {

//    public void accessFile (String file) throws FileNotFoundException {
        public void accessFile (String file) {

        //FileReader fr = new FileReader(file);

        try {
            FileReader fr1 = new FileReader(file);
        }
        catch(FileNotFoundException fnfe){
            System.out.println("File not found and checked exception caught");
            System.out.println(fnfe.getMessage());

            //throw new IOException("FileNotFoundException");
            throw new RuntimeException("File is not Found ",new Throwable("aaron.txt"));
            //throw new RuntimeException(new Throwable("FileNotFoundException"));
            //throw new RuntimeException();
        }
        System.out.println("After Exception Handling");
    }

//    public static void main (String args[]) throws FileNotFoundException{

    public static void main (String args[]) {

        CheckedVersusUncheckedDemo cvDemo = new CheckedVersusUncheckedDemo();

        cvDemo.accessFile("aaron.txt");

    }
}
