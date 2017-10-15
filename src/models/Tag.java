/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import anotations.ID;
import anotations.Table;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.GenericService;
import lombok.*;
/**
 *
 * @author deepak
 */

@Table(tableName = "tag")
public @Data class Tag {
    @ID(dbField = "tag_id", fieldType = int.class)
    private int id;
    private String name;
    
    //service
    public static GenericService<Tag> service = new GenericService<>(Tag.class, Tag::createTagFromResultSet);
    
    public Tag(String name){
        this.name = name;
    }
    
    public Tag(int id, String name){
        this(name);
        this.id = id;
    }
    
    public static Tag createTagFromResultSet(ResultSet rs) throws SQLException{
            return new Tag(rs.getInt(1),  rs.getString(2)); 
    }
    
    
    public static void main(String...args){
        System.out.println("Java");
        Tag.service.all();
        
    }
}
