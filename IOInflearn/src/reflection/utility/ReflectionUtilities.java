package reflection.utility;

import reflection.data.Chef;

import java.lang.reflect.Field;
import java.util.Arrays;

public class ReflectionUtilities {

    public static void populateDefaultValues(Object object) throws Exception{

            Class<?> objectClass = object.getClass();

            //objectClass.getFields();

//         Field field1 = objectClass.getDeclaredField("chefName");
//         field1.set(object,"Kevin");

            Arrays.stream(objectClass.getDeclaredFields()).forEach(field -> {
                field.setAccessible(true);
                try {
                    //Object instance =field.get(object);
                    //System.out.println("Returned Object : "+ objectField);
                    if (field.get(object) == null ) {
//                        if(instance instanceof String){
//                            field.set(object,"");
//                        }else if(instance instanceof  Integer){
//                            field.set(object,0);
//                        }
                      if(field.getType() == String.class){
                          field.set(object,"");
                      }else if(field.getType() == Integer.class){
                          field.set(object,0);
                      }

                    }
                }catch(Exception e){
                    System.out.println("Exception Occured: "+e);
                    e.printStackTrace();
                }

            });



    }

}
