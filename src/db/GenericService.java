/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import util.ThrowingFunction;
import anotations.ID;
import anotations.Table;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

/**
 * GenericService provide generic implementation of CRUD operation
 * for any model.
 * @author deepak
 */
public class GenericService<T>{
    private final Class<T> modelCls;
    private final String table;
    private String idField;
    private final ThrowingFunction<ResultSet, T> modelCreater;
    private Class<?> idFieldType;
    
    public GenericService(Class<T> modelCls, ThrowingFunction<ResultSet, T> f){
        this.modelCls = modelCls;
        this.table = modelCls.getAnnotation(Table.class).tableName();
        this.idField = this.table+"_id"; // init id with table name
        this.modelCreater = f;
        
        //finding id field from Moldel Class
        for(Field fld : modelCls.getDeclaredFields()){
            ID id = fld.getAnnotation(ID.class);
            if(id != null){
                this.idField = id.dbField();
                this.idFieldType = id.fieldType();
            }
        }
    }
    
    /*
        Return all instances of given model
    */
    public List<T> all(){
        return ModelServiceFactory.createFetchAll(modelCls, table, modelCreater);
    }
    
    /*
        Return a particular instance of model
    */
    public Optional<T> get(String id){
        if(idFieldType.equals(String.class)){
            id = "'"+id+"'"; // covering id value with '' if id field is varchar in database
        }
        T model = ModelServiceFactory.createFetchSingle(modelCls, table, this.idField +"="+id, modelCreater);
        if(model != null)
            return Optional.of(model);
        else
            return Optional.empty();
    }
    
    /*
        Executes delete statement for given model id
    */
    public boolean delete(String id){
        String whereClause = idField;
        if(idFieldType.equals(String.class))
            whereClause += " = '" + id + "'";
        else
            whereClause += " = " + id;
        return ModelServiceFactory.createDelete("DELETE FROM "+ table + " WHERE " + whereClause);
    }
    
    
    public boolean update(T model){
        throw new UnsupportedOperationException("Not yet supported");
    }
    
    /*
        Simiar to all() but excutes with sql WHERE cluase
    */
    public List<T> filter(String whereCluase){
        return ModelServiceFactory.createFetchMultiple(modelCls, table, whereCluase, modelCreater);
    }
    
}
