package annotation.basic;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
//@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.METHOD,ElementType.TYPE})
@Documented
public @interface AnnoMeta {

    String value();
}
