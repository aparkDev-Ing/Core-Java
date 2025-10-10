package annotation.basic;

import java.util.Arrays;

public class ElementDataMain1 {

    public static void main(String[] args) {

        Class<? extends ElementData1> elementData1Class = ElementData1.class;

        AnnoElement annoElement = elementData1Class.getAnnotation(AnnoElement.class);

        System.out.println("Value: "+ annoElement.value());

        System.out.println("Count: "+ annoElement.count());

        System.out.println("Tags: "+ Arrays.toString(annoElement.tags()));


    }

}
