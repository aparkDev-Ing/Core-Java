package reflection;

import java.util.Arrays;

public class ConstructV1 {
    public static void main(String[] args) throws Exception {
        Class<?> basicDataClass = Class.forName("reflection.data.BasicData");

//        System.out.println(basicDataClass.getName());
//        System.out.println(basicDataClass.getSimpleName());
//        System.out.println(basicDataClass.getPackage());
//        System.out.println(basicDataClass.getPackageName());

        //constuctor does not have concept of inheritance, so its technically just public constructors
        System.out.println("Constructors()");
        Arrays.stream(basicDataClass.getConstructors()).forEach(System.out::println);

        System.out.println();
        System.out.println("Declared Constructors()");
        Arrays.stream(basicDataClass.getDeclaredConstructors()).forEach(System.out::println);


    }
}
