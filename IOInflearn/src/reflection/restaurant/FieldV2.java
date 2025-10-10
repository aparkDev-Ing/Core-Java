package reflection.restaurant;

import reflection.data.Chef;
import was.httpserver.NotFoundException;

import java.lang.reflect.Field;

public class FieldV2 {
    public static void main(String args[]) throws Exception {
        Chef chef = new Chef("Aaron",15);

        System.out.println("Before name change: "+chef.getChefName());
        Class<? extends Chef> chefClass = chef.getClass();

        //Field field1 = chefClass.getField("chefName");
        Field field1 = chefClass.getDeclaredField("chefName");
        field1.setAccessible(true);

        field1.set(chef,"David");

        System.out.println("After name change: "+ chef.getChefName());
    }
}
