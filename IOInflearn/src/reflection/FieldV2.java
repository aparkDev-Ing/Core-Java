package reflection;

import reflection.data.Chef;
import reflection.utility.ReflectionUtilities;

import java.util.Arrays;

public class FieldV2 {
    public static void main(String args[]) throws Exception{
        Chef chef = new Chef(null,null);

        //System.out.println("Before change on fields: "+ Arrays.toString(chef.getClass().getFields()));
        System.out.println("Before applying business logic to populate default values: "+chef);
        //정적으로 필드의 값을 바꾸는방법
        //defaultValues(chef);

        //리플렉션을 이용해 동적으로 필드의 값을 바꾸는방법
        ReflectionUtilities.populateDefaultValues(chef);

        //System.out.println("After change on fields: "+Arrays.toString(chef.getClass().getFields()));
        System.out.println("After applying business logic to populate default values: "+chef);

    }

    static void defaultValues(Chef chef){
        if(chef.getChefName()==null){
            chef.setChefName("");
        }

        if(chef.getChefAge()==null){
            chef.setChefAge(0);
        }
    }
}
