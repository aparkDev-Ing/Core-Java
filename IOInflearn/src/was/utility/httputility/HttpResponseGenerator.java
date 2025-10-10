package was.utility.httputility;

import was.constants.Constants;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import static was.constants.Constants.DEFAULT_CONTENT_TYPE;

public class HttpResponseGenerator {



    public int statusCode;

    public String statusString;

    public String contentType;
    public int contentLength;
    public StringBuilder responseBody;
    public PrintWriter pw;

    public HttpResponseGenerator(){

    }
    public HttpResponseGenerator(int statusCode,StringBuilder body, PrintWriter pw){
        this.pw=pw;
        this.statusCode=statusCode;
        generateResponse(body);
        generateResponseStatusString();
        generateFirstLine();
        generateHeader();
//        this.pw.flush();
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

    public void generateResponse(StringBuilder body){
        responseBody = body;

        //추후에바꾸기
        this.contentType=DEFAULT_CONTENT_TYPE;
    }

    public void generateFirstLine(){
        this.pw.println("HTTP/1.1 "+this.statusCode+" "+this.statusString);

    }

    public void generateHeader(){
        this.pw.println("Content-Type: "+this.contentType);
        this.pw.println("Content-Length: "+this.responseBody.toString().getBytes(StandardCharsets.UTF_8).length);
        this.pw.println();
        this.pw.println(this.responseBody);
    }

    public void flush(){
        this.pw.flush();
    }
}
