package com.thoughtworks.toggle;

import example.MyFeatureToggle;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ToggleEnabled Annotation, all the Toggle added into this Annotation will be turn on.
 * User: xianlinbox
 * Date: 4/3/13
 * Time: 4:34 PM
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ToggleEnabled {
    //    MyFeatureToggle[] value();
    String toggleClass();
    String[] toggleNames();
}
