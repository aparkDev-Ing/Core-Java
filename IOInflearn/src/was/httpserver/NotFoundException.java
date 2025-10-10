package was.httpserver;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
