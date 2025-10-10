package reflection;

import reflection.data.BasicData;

import java.util.Arrays;
import java.util.stream.Stream;

public class MethodV1 {
    public static void main(String args[]){
        //Class<BasicData> basicDataClass= BasicData.class;

        BasicData basicData = new BasicData();
        Class<? extends BasicData> basicDataClass = basicData.getClass();
        Arrays.stream(basicDataClass.getMethods()).forEach(System.out::println);
        System.out.println("===================================================");
        Arrays.stream(basicDataClass.getDeclaredMethods()).forEach(System.out::println);

    }
}
