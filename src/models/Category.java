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
import java.util.ArrayList;
import lombok.*;
/**
 *
 * @author deepak
 */

@Table(tableName = "category")
@AllArgsConstructor
public @Data class Category {
    @ID(dbField = "category_id", fieldType = int.class)
    private int id;
    private String name;
    private String discription;
    
    public static GenericService<Category> service = new GenericService<Category>(Category.class, Category::createCategoryFromResultSet);
    
    public static Category createCategoryFromResultSet(ResultSet rs) throws SQLException{
            new ArrayList<Integer>().stream();
            return  null;//Category(rs.getInt(1),  rs.getString(2), rs.getString(2)); 
    } 
    
}
