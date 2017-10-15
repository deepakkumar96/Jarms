/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import db.ModelServiceFactory;
import db.SqlTable;

/**
 *
 * @author deepak
 */
public class RawQueryTest {
    
    public static void main(String...args){
        /*
            ModelServiceFactory.fetchRawQuery is used for executing those querys
            whose result is not aModel class such as a join query which can't
            be represented by a single model.
        */
        SqlTable db = ModelServiceFactory.fetchRawQuery("select state_name,city_name from city, state;"); // RAW query without model
        
        System.out.println(db);
        
        //fetches city_name column's value at 0th row
        db.get("city_name", 0).ifPresent(System.out::println);
        
        //fetches all values of city_name's column
        db.getColumnValues("city_name").ifPresent(System.out::println);
        
    }
    
}
