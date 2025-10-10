package file;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class OldFileExample {

    public static void main(String args[]) throws IOException {
        File file1 = new File("IOInflearn/temp/old file example.txt");

        File directory1 = new File("IOInflearn/temp/oldfileexample");

        System.out.println("Does file exist: "+file1.exists());

        System.out.println("IsFile?: "+file1.isFile());

        System.out.println("IsDirectory?: "+file1.isDirectory());

        System.out.println("After file creation: "+ file1.createNewFile());

        System.out.println("File name: "+file1.getName());

        //System.out.println("File Deleted: "+ file1.delete());

        //System.out.println("After deletion. Does file exist: "+file1.exists());

        System.out.println("File size: "+file1.length() +" Bytes");

        System.out.println("After directory creation: "+directory1.mkdir());

        System.out.println("Is directory ? : "+directory1.isDirectory());


        //file 이름도맞지만 사실 다이렉토리를 바꾼다
        File newFile = new File("IOInflearn/temp/oldfileexample/old file example new name.txt");
        System.out.println("File renamed: "+file1.renameTo(newFile));

        System.out.println("Last modified info: "+new Date(newFile.lastModified()));


    }
}
