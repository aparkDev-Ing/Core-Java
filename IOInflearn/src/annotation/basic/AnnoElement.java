package annotation.basic;

import logging.MyLogger;

import java.lang.annotation.*;
import java.util.List;

//@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnoElement {

    //자바가 제공하는 기본타입 만 가능
    String value();
    
    int count() default 0;
    String[] tags() default{};

    //MyLogger data();
    Class<? extends MyLogger> annoData() default MyLogger.class;

    Class<? extends List> listData() default List.class;
}
