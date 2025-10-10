package file;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

public class NewFilesExample {

    public static void main(String args[]) throws IOException{

        //Directory creation
        Path directory = Path.of("IOInflearn/temp/newfileexample");

        System.out.println(directory);
        try {
            Files.createDirectory(directory);
        }
        catch(FileAlreadyExistsException e){
            System.out.println("Directory already exists! "+e);
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }

        System.out.println("Is directory: "+ Files.isDirectory(directory));


        //File creation

        Path file = Path.of("IOInflearn/temp/new file.txt");

        System.out.println("File exists: "+Files.exists(file));
        System.out.println("Is regular file: "+ Files.isRegularFile(file));

        //Files.delete(file);

        try {
            Files.createFile(file);
        }
        catch(FileAlreadyExistsException e){
            System.out.println("File already exists! "+e);
        }
        catch(IOException e){
          throw new RuntimeException(e);
        }

        System.out.println("File exists: "+Files.exists(file));

        System.out.println("Is regular file: "+ Files.isRegularFile(file));

        System.out.println("File name: " + file.getFileName());

        System.out.println("File size: " + Files.size(file) +" bytes");

        System.out.println("Change file name ");

        //Files.delete(directory);

        Path newPath = Path.of("IOInflearn/temp/newfileexample/new file.txt");
//
        Files.move(file,newPath, StandardCopyOption.REPLACE_EXISTING);

        //Files.move(file,newPath, StandardCopyOption.COPY_ATTRIBUTES);

        //System.out.println("Last modified: "+ Files.getLastModifiedTime(file));
        System.out.println("Last modified: "+ Files.getLastModifiedTime(newPath));


        BasicFileAttributes attrs = Files.readAttributes(newPath, BasicFileAttributes.class);

        // 추가: readAttributes(): 파일의 기본 속성들을 한 번에 읽기
        System.out.println("===== Attributes =====");
        System.out.println("Creation time: " + attrs.creationTime());
        System.out.println("Is directory: " + attrs.isDirectory());
        System.out.println("Is regular file: " + attrs.isRegularFile());
        System.out.println("Is symbolic link: " + attrs.isSymbolicLink());
        System.out.println("Size: " + attrs.size());
    }
}
