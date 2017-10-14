/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import util.ThrowingFunction;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 *
 * @author deepak
 */
public class ModelServiceFactory {
    
    public static <T> List<T> createFetchAll(Class<T> cls, String table, ThrowingFunction<ResultSet, T> consumer){
        return createExcecuteRawModelAndFetchMultiple(cls, "select * from "+table+";", consumer);
    }
    
    public static <T> T createFetchSingle(Class<T> cls, String table, String whereCondition, ThrowingFunction<ResultSet, T> consumer){
        return createExcecuteRawModelAndFetchSingle(cls, "select * from "+table+" WHERE "+whereCondition, consumer);
    }
    
    public static <T> List<T> createFetchMultiple(Class<T> cls, String table, String whereCondition, ThrowingFunction<ResultSet, T> consumer){
        return createExcecuteRawModelAndFetchMultiple(cls, "select * from "+table+" WHERE "+whereCondition, consumer);
    }
    
    public static <T> T createExcecuteRawModelAndFetchSingle(Class<T> cls, String query, ThrowingFunction<ResultSet, T> consumer){
        T model = null;
        //System.out.println(query);
        try{
                Statement stmt = DbManager.INSTANCE.getConnection().createStatement();
                ResultSet rs = stmt.executeQuery(query);
     
                if(rs.next()){
                    model = cls.cast(consumer.apply(rs));
                }
        }catch(SQLException sqlex){
                sqlex.printStackTrace();
        }
        return model;
    }
    
    public static <T> List<T> createExcecuteRawModelAndFetchMultiple(Class<T> cls, String query, ThrowingFunction<ResultSet, T> consumer){
        List<T> models = new ArrayList<T>();
        //System.out.println(query);
        try{
                Statement stmt = DbManager.INSTANCE.getConnection().createStatement();
                ResultSet rs = stmt.executeQuery(query);
     
                while(rs.next()){
                    models.add(cls.cast(consumer.apply(rs)));
                }
        }catch(SQLException sqlex){
                sqlex.printStackTrace();
        }
        
        return models;
    } 
    
    
    public static JoinedTable fetchRawQuery(String query){
        JoinedTable table = null;
        try{
                Statement stmt = DbManager.INSTANCE.getConnection().createStatement();
                ResultSet rs = stmt.executeQuery(query);
                ResultSetMetaData rsMeta = rs.getMetaData();
                table = new JoinedTable(rsMeta.getTableName(1).toUpperCase());
                int len = rsMeta.getColumnCount();
                
                while(rs.next()){
                    for(int i=1; i<=len; i++){
                        table.add(rsMeta.getColumnName(i), rs.getObject(i));
                    }
                }
                
        }catch(SQLException sqlex){
                sqlex.printStackTrace();
        }
        return table;
    }
    
        public static boolean createDelete(String query){
        //System.out.print(query);
        try{
                Statement stmt = DbManager.INSTANCE.getConnection().createStatement();
                stmt.executeUpdate(query);
                
        }catch(SQLException sqlex){
                sqlex.printStackTrace();
                return false;
        }
        return true;
    }
    
}
