package bytearraystream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;


class MyObject implements Serializable {
    private String name;
    private int id;

    public MyObject(String name, int id) {
        this.name = name;
        this.id = id;
    }

    // Getters and setters
}

public class ObjectToByteArrayConverter {
    public static byte[] convertObjectToByteArray(Object obj) throws Exception {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
             oos.writeObject(obj);

            return bos.toByteArray();
        }
    }

    public static void main(String[] args) throws Exception {
        MyObject myObj = new MyObject("Test Object", 123);
        byte[] byteArray = convertObjectToByteArray(myObj);
        System.out.println(Arrays.toString(byteArray));
        System.out.println("Object converted to byte array of length: " + byteArray.length);
    }
}