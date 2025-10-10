package reflection;

import reflection.data.BasicData;

import java.lang.reflect.Method;

public class MethodV2 {
    public static void main(String args[])throws Exception{
        //정적 메소드 호풀
        BasicData basicData = new BasicData();
        basicData.call();

        Class<? extends BasicData> basicDataClass = basicData.getClass();

        //assume that this is from request or scan
        String methodCall = "hello";
        Method method1 = basicDataClass.getDeclaredMethod(methodCall,String.class);
        //Method method1 = basicDataClass.getMethod(methodCall,String.class);

        System.out.println("Method Information: "+method1);
        Object result = method1.invoke(basicData,"hi");

        System.out.println("Return value: "+ result);

    }
}
