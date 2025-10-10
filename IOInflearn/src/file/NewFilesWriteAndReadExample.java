package file;

import efficientfilestream.BufferConstants;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class NewFilesWriteAndReadExample {

    static String PATH = "IOInflearn/temp/Files write and read string.txt";

    public static void main(String args[]) throws IOException{

        String food = "바나나\nApple\n사과";

        Path path = Path.of(PATH);

        write(food,path);
        //readAll(path);
        //readByLine1(path);
        readByLine2(path);
    }

    static void write(String food, Path path)throws IOException {

        Files.writeString(path, food, BufferConstants.UTF_8);
    }

    static void readAll(Path path) throws IOException{
        System.out.println(Files.readString(path, BufferConstants.UTF_8));
    }

    // load all data to memory at once
    static void readByLine1(Path path) throws IOException{
        Files.readAllLines(path, BufferConstants.UTF_8).forEach(System.out::println);
    }

    // load one line to memory and so on
    static void readByLine2(Path path) throws IOException{

//        try(Stream<String> foodStream=  Files.lines(path, BufferConstants.UTF_8)){
//            foodStream.forEach(System.out::println);
//        }catch(IOException e){
//            System.out.println(e);
//        }
          Files.lines(path, BufferConstants.UTF_8).forEach(System.out::println);

    }

}
