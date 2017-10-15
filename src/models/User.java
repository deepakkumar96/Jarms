/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import anotations.ID;
import anotations.Table;
import db.GenericService;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import javax.swing.JOptionPane;
import lombok.Data;
import static models.UserRole.service;
import util.ModelUtil;

/**
 *
 * @author deepak
 */

@Table(tableName = "users")
public @Data class User {
    @ID(dbField = "user_id", fieldType = int.class)
    private long userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String email;
    private String mobileNumber;
    private Date dob;
    private String password;
    private String securityQueAns;
    private String image;
    private boolean isActive = true;
    private int securityQueId;
    private int userTypeId;
    private int addressId;

    public static GenericService<User> service = new GenericService<>(User.class, User::createUserFromResultSet);
    
    public User(){}
    public User(String firstName, String middleName, String lastName, String gender, String email, String mobileNumber, Date dob, String password, String securityQueAns, String image, boolean isActive, int securityQueId, int userType, int addressId) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.dob = dob;
        this.password = password;
        this.image = image;
        this.securityQueAns = securityQueAns;
        this.userTypeId = userType;
        this.securityQueId = securityQueId;
        this.addressId = addressId;
    }
    
    public User(int userId, String firstName, String middleName, String lastName, String gender, String email, String mobileNumber, Date dob, String password, String securityQueAns, String image, boolean isActive, int securityQueId, int userType, int addressId) {
        this(firstName, middleName, lastName, gender, email, mobileNumber, dob, password, securityQueAns, image, isActive, securityQueId, userType, addressId);
        this.userId = userId;
    }
    
    
    public Optional<Address> getAddress(){
        return ModelUtil.getAddress(addressId);
    }
    
    public Optional<UserType> getUserType(){
        return ModelUtil.getUserType(userTypeId);
    }
    
    public Optional<SecurityQuestion> getSecurityQuestion(){
        return ModelUtil.getSecurityQuestion(securityQueId);
    }
    /*
        Creates a User object from ResultSet object
    */
    public static User createUserFromResultSet(ResultSet rs) throws SQLException{
        return new User(
              rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
              rs.getString(5), rs.getString(6), rs.getString(6), rs.getDate(8),
              rs.getString(9), rs.getString(11), rs.getString(12), rs.getBoolean(13),
              rs.getInt(14), rs.getInt(15), rs.getInt(16)
        );
    }
    
    
}
