package annotation.mapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class TestControllerMain {
    public static void main(String[] args) {

        Class<? extends TestController> testControllerClass = TestController.class;

        for(Method method: testControllerClass.getDeclaredMethods()){
            if(method.getAnnotation(SimpleMapping.class) != null){

                SimpleMapping simpleMapping = method.getAnnotation(SimpleMapping.class);
                System.out.println("Method name: "+method +" Annotation Mapping: "+simpleMapping.value());

            }
        }

    }
}
