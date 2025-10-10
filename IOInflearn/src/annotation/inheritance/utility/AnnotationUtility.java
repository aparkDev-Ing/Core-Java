package annotation.inheritance.utility;

import java.util.Arrays;

public class AnnotationUtility {

    public static void print(Class<?> clazz){

        System.out.println("Class: "+clazz);
        Arrays.stream(clazz.getAnnotations()).forEach(annotation -> System.out.println("Annotation: " +annotation.annotationType().getName()));
        //Arrays.stream(clazz.getDeclaredAnnotations()).forEach(annotation -> System.out.println("Annotation: " +annotation.annotationType().getName()));

        System.out.println();
    }
}
