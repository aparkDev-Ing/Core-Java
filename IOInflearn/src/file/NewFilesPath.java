package file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;

public class NewFilesPath {

    public static void main(String[] args) throws IOException {

        Path path = Path.of("IOInflearn/temp/..");
        System.out.println("Path: "+path);

        //absolute path - shows all info
        System.out.println("Absolute path: "+ path.toAbsolutePath());

        //canonical path - calculates all path
        System.out.println("canonical path: "+ path.toRealPath());
        Stream<Path> pathStream= Files.list(path);
        pathStream.forEach(p -> System.out.println( (Files.isRegularFile(p)? "F":"D") + " | " + p.getFileName() ));
        pathStream.close();

    }
}
