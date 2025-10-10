package reflection;

import reflection.data.BasicData;

import java.util.Arrays;

public class FieldV1 {

    public static void main(String args[]){
        Class<BasicData> basicDataClass = BasicData.class;

        //System.out.println("Fields: ");
        Arrays.stream(basicDataClass.getFields()).forEach(s -> System.out.println("Field: "+s));
        System.out.println("=========================================");

        Arrays.stream(basicDataClass.getDeclaredFields()).forEach(s -> System.out.println("Declared Field: "+s));


    }
}
