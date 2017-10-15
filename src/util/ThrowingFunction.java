/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.SQLException;

/**
 *
 * @author deepak
 */

@FunctionalInterface
public interface ThrowingFunction<T, R> {
    R apply(T t) throws SQLException;
}
