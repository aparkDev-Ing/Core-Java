package reflection;

import reflection.data.BasicData;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


public class ConstructV2 {
    public static void main(String[] args) throws Exception {
        Class<?> basicDataClass = Class.forName("reflection.data.BasicData");

        Constructor<?> constructor = basicDataClass.getDeclaredConstructor(String.class);

        constructor.setAccessible(true);
        BasicData instance = (BasicData) constructor.newInstance("Hello");
        System.out.println("Basic data: "+instance);

        Method hello = basicDataClass.getDeclaredMethod("hello",String.class);
        Object obj = hello.invoke(instance,"David");
        System.out.println(obj);

    }
}
