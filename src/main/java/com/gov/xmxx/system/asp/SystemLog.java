package com.gov.xmxx.system.asp;


import java.lang.annotation.*;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SystemLog {
    String description() default "";

    boolean isWrite() default true;

}
