/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Used to mark a field as Primary Key.
 * @author deepak
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface ID {
    String dbField();
    Class<?> fieldType();
}
