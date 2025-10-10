package text;

import java.util.Arrays;

public class Main {

    public static void main(String args[]){
        String[] strArray = "4".split(":");
        System.out.println(
                strArray[0]
        );
        Arrays.stream(strArray).forEach(System.out::println);
    }
}
