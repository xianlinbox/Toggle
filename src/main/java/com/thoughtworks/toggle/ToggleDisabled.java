package com.thoughtworks.toggle;

import example.MyFeatureToggle;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ToggleDisabled Annotation, all the Toggle added into this Annotation will be turn off.
 * User: xianlinbox
 * Date: 4/3/13
 * Time: 7:20 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ToggleDisabled {
    String toggleClass();
    String[] toggleNames();

}
