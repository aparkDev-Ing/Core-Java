package reflection;

import reflection.data.BasicData;
import was.httpserver.NotFoundException;

public class BasicV1 {
    public static void main(String[] args) throws ClassNotFoundException {

        //1. Find through Class Object - 클래스의 정보를담고있는 클래스객체
        Class<BasicData> basicDataClass1 = BasicData.class;
        //class reflection.data.BasicData
        System.out.println("Basic Data Class Info1: "+basicDataClass1);

        //2. Find through instance variable
        BasicData basicData = new BasicData();
        Class<? extends BasicData> basicDataClass2 = basicData.getClass();
        System.out.println("Basic Data Class Info2: "+basicDataClass2);


        //3. 문자로찾기
        String className="reflection.data.BasicData";
        Class<?> basicDataClass3 = Class.forName(className);
        System.out.println("Basic Data Class In=fo3: "+basicDataClass3);

    }
}
