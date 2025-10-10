package core.java.io.textencoding;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.SortedMap;

enum Transcript{

    KOREAN("Korean","가"),
    ENGLISH("English","A");

    //J("English","Hello, this documentation includes.....");


    Transcript(String language, String description){

        this.language = language;
        this.description = description;
    }

    String language;

    String description;


}
public class TextEncodingExample {

    static int noOfCustoemrs = 2;
    static List<Transcript> transcriptList = Arrays.asList(Transcript.ENGLISH,Transcript.KOREAN);

    private static final Charset EUC_KR = Charset.forName("EUC-KR");
    private static final Charset MS_949 = Charset.forName("MS949");

    private static final Charset USASCII = Charset.forName("US-ASCII");

    //private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final Charset UTF_8 =  StandardCharsets.UTF_8;


    static void performTranscriptTransfer(){

        byte[] englishEncoded =new byte[1];
        byte[] koreanEncoded = new byte[2] ;


        for(int i =0; i<noOfCustoemrs;i++){

            //int index = new Random().nextInt(2);
//            if(transcriptList.get(0).language.equals("Korean")){
//               koreanEncoded = encode(transcriptList.get(index).description, MS_949);
//            }else{
//               englishEncoded = encode(transcriptList.get(index).description, UTF_8);
//            }

            if(transcriptList.get(i).language.equals("Korean")){
                koreanEncoded = encode(transcriptList.get(i).description, MS_949);
            }else{
                englishEncoded = encode(transcriptList.get(i).description, UTF_8);
            }
        }

        decode(koreanEncoded,MS_949);
        decode(englishEncoded,UTF_8);

    }

    static byte[] encode(String text, Charset encodeSet){

        byte[] encodeBytes = text.getBytes(encodeSet);

        System.out.printf("%s encoded to -> [%s] | byte size: [%s] through [%s]", text,Arrays.toString(encodeBytes), encodeBytes.length, encodeSet);
        System.out.println("");

        return encodeBytes;
    }

    static String decode(byte[] encodedByteArray, Charset decodeSet){

        String decodedString = new String(encodedByteArray, decodeSet);

        System.out.printf("%s decoded to -> [%s] | byte size: [%s] through [%s]", Arrays.toString(encodedByteArray), decodedString ,encodedByteArray.length, decodeSet);
        System.out.println("");

        return decodedString;
    }

    public static void main(String args[]){

        performTranscriptTransfer();
    }


}
