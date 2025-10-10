package was.httpserver;

import ioexamples.ufcplayer.UFCPlayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    public String requestMethod;
    public String requestPath;
    public String httpVersion;
    public String firstLine;
    public Map<String,String> queryParamsMap =new HashMap<>();
    public Map<String,String> headerMap = new HashMap<>();

    public BufferedReader bufferedReader;

    public String body;


    public HttpRequest(BufferedReader bufferedReader) throws IOException {
        this.bufferedReader=bufferedReader;
        generateRequestLine();
        generateHeader();
        generateBody();
    }


    //Example -> GET /search?q=hello&b=hi HTTP/1.1

    public void generateRequestLine()throws IOException{

        String request = bufferedReader.readLine();
        this.firstLine=request;
        if(request !=null && !request.isEmpty()){
            String[] requestArray = request.split(" ");
            this.requestMethod=requestArray[0];
            String requestPathURL=requestArray[1];
            this.httpVersion=requestArray[2];

            String[] requestPathArray = requestPathURL.split("\\?");
            //System.out.println(Arrays.toString(requestPathArray));
            this.requestPath=requestPathArray[0];

            //if it has query params attached with ? -> ?q=hello&b=byte
            if(requestPathArray.length>1){
                generateQueryParams(requestPathArray[1]);
            }

        }else{
            throw new IOException("Request is null");
        }
    }

    public void generateQueryParams(String queryParams) throws IOException {

        String[] queryParamList = queryParams.split("&");
        for(int i=0;i<queryParamList.length;i++){
            String queryParam = queryParamList[i];
            String[] keyValueList = queryParam.split("=");
            this.queryParamsMap.put(URLDecoder.decode(keyValueList[0], StandardCharsets.UTF_8),URLDecoder.decode(keyValueList[1],StandardCharsets.UTF_8));
        }

    }


    //    Content-Type: text/html
    //    Content-Length: 20
    public void generateHeader()throws IOException{

        String header;
        while((header =this.bufferedReader.readLine())!=null && !header.isEmpty()){
            String[] headerList =  header.split(":");
            this.headerMap.put(headerList[0].trim(),headerList[1].trim());
        }

    }

    public void generateBody() throws IOException{
        String contentLength = this.headerMap.get("Content-Length");
        if(contentLength != null) {
            char[] contentArray = new char[Integer.parseInt(contentLength)];

            int read = bufferedReader.read(contentArray);
            if (read != Integer.parseInt(contentLength)) {
                throw new IOException("Failed to read entire body. Expected to read: " + contentLength + " | what is read: " + read);
            }

            String body = new String(contentArray);

//            String data;
//            while((data =this.bufferedReader.readLine())!=null ){
//                body=data;
//            }
//
//            System.out.println("Body: "+body);

            if ("application/x-www-form-urlencoded".equals(headerMap.get("Content-Type"))) {
                generateQueryParams(body);
            }
            this.body=body;

        }
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "requestMethod='" + requestMethod + '\'' +
                ", httpVersion='" + httpVersion + '\'' +
                ", firstLine='" + firstLine + '\'' +
                ", queryParamsMap=" + queryParamsMap +
                ", body='" + body + '\'' +
                '}';
    }
}
