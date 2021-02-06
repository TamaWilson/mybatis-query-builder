package br.com.tamawilson.mybatisquerybuilder.model.annotation;


import br.com.tamawilson.mybatisquerybuilder.model.type.MyBatisNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MyBatisColumn {

    String name() default "";

    Class<?> nestedClass() default MyBatisNull.class;

    Class<?> rootAs() default MyBatisNull.class;
}

