package annotation.validator.utility;

import annotation.validator.NotEmpty;
import annotation.validator.Player;
import annotation.validator.Range;

import java.lang.reflect.Field;
import java.util.Arrays;

public class ValidatorUtil {
    public static<T extends Player> void validate(T player) throws Exception{


       for(Field field: player.getClass().getDeclaredFields()) {
           field.setAccessible(true);
           //System.out.println("Player: "+ player+" Field: "+ field);
           if(field.isAnnotationPresent(NotEmpty.class)){
               NotEmpty notEmpty = field.getAnnotation(NotEmpty.class);

               String name = (String) field.get(player);

               if(name==null ||name.isEmpty()){
                   throw new RuntimeException(notEmpty.message());
               }
           }

           if(field.isAnnotationPresent(Range.class)){
               Range range = field.getAnnotation(Range.class);
               int totalMatch = (int) field.get(player);

               String newMessage = range.message().replace("{{totalMatch}}",String.valueOf(range.totalMatch()));

               if(totalMatch<range.totalMatch() ){
                   throw new RuntimeException(newMessage);
               }
           }

       }

        System.out.println("Professional player validation completed: "+player);
    }
}
