package annotation.validator;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Range {
    int totalMatch() default 5;
    String message() default "Total match needs to exceed at least {{totalMatch}} matches";
}
