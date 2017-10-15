/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import anotations.ID;
import anotations.Table;
import com.sun.xml.internal.ws.api.policy.ModelUnmarshaller;
import db.GenericService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import lombok.Data;
import util.ModelUtil;

/**
 *
 * @author deepak
 */
@Table(tableName = "user_role")
public @Data class UserRole {
    @ID(dbField = "user_role_id", fieldType = Integer.class)
    private int userRoleId;
    private int userId;
    private int userTypeId;
    
    public static GenericService<UserRole> service = new GenericService<>(UserRole.class, UserRole::createUserRoleFromResultSet);
    
    
    public UserRole(int user, int userTypeId){
        this.userId = user;
        this.userTypeId = userTypeId;
    }
    
    public UserRole(int userRoleId, int user, int userTypeId){
        this(user, userTypeId);
        this.userRoleId = userRoleId;
    }
    
    public Optional<User> getUser(){
        Optional<User> usr = User.service.get(userId+"");
        if(usr.isPresent()) return usr;
        else return Optional.empty();
    }
    
    public Optional<UserType> getUserType(){
        return ModelUtil.getModelById(service, userId);
    }
    
    public static UserRole createUserRoleFromResultSet(ResultSet rs) throws SQLException{
        return new UserRole(rs.getInt(1), rs.getInt(2), rs.getInt(3));
    }
    
    public static void main(String...args){
        
    }
}
