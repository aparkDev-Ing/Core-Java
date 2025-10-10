package was.v4;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class URLEncoderDecoderExample {

    public static void main(String args[]){

        String korean= "가";
        String percentEncodedASCii= URLEncoder.encode(korean, StandardCharsets.UTF_8);
        System.out.println("URL ASCII Encoded string: "+percentEncodedASCii);

        String decodedString = URLDecoder.decode(percentEncodedASCii,StandardCharsets.UTF_8);
        System.out.println("URL UTF-8 Decoded string: "+decodedString);
    }
}
