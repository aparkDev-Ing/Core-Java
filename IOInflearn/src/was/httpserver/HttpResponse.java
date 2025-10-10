package was.httpserver;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import static was.constants.Constants.DEFAULT_CONTENT_TYPE;

public class HttpResponse {

    public int statusCode;

    public String statusString;

    public String contentType;
    public int contentLength;
    public StringBuilder responseBody;
    public PrintWriter pw;

    public HttpResponse(){

    }
    public HttpResponse(PrintWriter pw){
        this.pw=pw;
    }

    public void generateResponseStatusString(){
        switch (this.statusCode) {
            case 200:
                this.statusString="OK";
                break;
            case 404:
                this.statusString="Not Found";
                break;
            case 500:
                this.statusString="Internal Server Error";
                break;
            default:
                this.statusString="Unknown Status";
                break;
        }
    }

    public void generateBody(){
        this.pw.println(this.responseBody);
    }

    public void generateFirstLine(){
        this.pw.println("HTTP/1.1 "+this.statusCode+" "+this.statusString);

    }

    public void generateHeader(){
        this.pw.println("Content-Type: "+this.contentType);
        this.pw.println("Content-Length: "+this.responseBody.toString().getBytes(StandardCharsets.UTF_8).length);
        this.pw.println();

    }

    public void flush(){

        //generateResponse(body);
        this.contentType=DEFAULT_CONTENT_TYPE;

        //generate status string code
        generateResponseStatusString();

        //add firstline
        generateFirstLine();

        //add header
        generateHeader();

        //add body
        generateBody();

        this.pw.flush();
    }
}
