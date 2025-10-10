package file;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class OldFilePath {

    public static void main(String args[])throws IOException {

        File file = new File("IOInflearn/temp/..");
        System.out.println("Path: "+file.getPath());

        //absolute path - shows all info
        System.out.println("Absolute path: "+ file.getAbsoluteFile());

        //canonical path - calculates all path
        System.out.println("canonical path: "+ file.getCanonicalPath());
        Arrays.stream(file.listFiles()).forEach(f -> System.out.println( (f.isFile()? "F":"D") + " | " + f.getName() ));

//        File file2 = new File("temp/..");
//        System.out.println("Path2: "+file2.getPath());
//
//        //absolute path - shows all info
//        System.out.println("Absolute path: "+ file2.getAbsoluteFile());
//
//        //canonical path - calculates all path
//        System.out.println("canonical path: "+ file2.getCanonicalPath());



    }
}
