/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import anotations.ID;
import anotations.Table;
import db.GenericService;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.Data;

/**
 *
 * @author deepak
 */
@Table(tableName = "user_type")
public @Data class UserType {
    @ID(dbField = "user_type_id", fieldType = Integer.class)
    private int userTypeId;
    private String userType;
    
    public static GenericService<UserType> service = new GenericService<>(UserType.class, UserType::createUserTypeFromResultSet);
    
    
    public UserType(String userType){
        this.userType = userType;
    }
    
    public UserType(int userTypeId, String userType){
        this(userType);
        this.userTypeId = userTypeId;
    }
    
    public static UserType createUserTypeFromResultSet(ResultSet rs) throws SQLException{
        return new UserType(rs.getInt(1), rs.getString(2));
    }
}
