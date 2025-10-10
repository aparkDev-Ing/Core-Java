package bytearraystream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class ByteArrayStreamExample {

    public static void main(String args[]) throws Exception{

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] exampleBytes = {55,24,32};

        baos.write(exampleBytes);
        baos.close();

        //System.out.println(Arrays.toString(baos.toByteArray()));

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

       // byte[] resultBytes = bais.readAllBytes();

        byte[] resultBytes = new byte[3];

        bais.read(resultBytes,0,resultBytes.length);

        System.out.println(Arrays.toString(resultBytes));

    }
}
