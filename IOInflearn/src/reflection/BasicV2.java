package reflection;

import reflection.data.BasicData;

import java.lang.reflect.Modifier;
import java.util.Arrays;

public class BasicV2 {

    public static void main(String args[]){

        Class<BasicData> basicDataClass = BasicData.class;

        System.out.println("Class name with package: "+ basicDataClass.getName());
        System.out.println("Simple class name: "+ basicDataClass.getSimpleName());

        System.out.println("Package Name: "+ basicDataClass.getPackage());
        System.out.println("Parent class information: "+basicDataClass.getSuperclass());
        System.out.println("Interface classes information: "+ Arrays.toString(basicDataClass.getInterfaces()));

        System.out.println("Is interface? "+ basicDataClass.isInterface());
        System.out.println("Is enum? "+ basicDataClass.isEnum());
        System.out.println("Is annotation? "+ basicDataClass.isAnnotation());

        int modifiers= basicDataClass.getModifiers();
        System.out.println("Modifiers: "+modifiers);
        System.out.println("Ispublic: " + Modifier.isPublic(modifiers));

        System.out.println("Modifier.toString(): " + Modifier.toString(modifiers));

    }




}
