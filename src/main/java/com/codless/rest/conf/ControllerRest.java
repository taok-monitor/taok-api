package com.codless.rest.conf;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.inject.Stereotype;

import com.codless.exception.ExceptionRest;

@Stereotype
@Documented
@Retention(RUNTIME)
@Target({ TYPE, FIELD, METHOD })
@ExceptionRest
public @interface ControllerRest {

}
