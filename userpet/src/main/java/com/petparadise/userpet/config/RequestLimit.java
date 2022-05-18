package com.petparadise.userpet.config;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Inherited
@Documented
@Order(Ordered.HIGHEST_PRECEDENCE) //最高优先级
public @interface RequestLimit {
    /**
     *
     * 允许访问的次数，默认值MAX_VALUE
     */
    int count() default Integer.MAX_VALUE;

    /**
     *
     * 时间段，单位为毫秒，默认半分钟
     */
    long time() default 3000;
}
