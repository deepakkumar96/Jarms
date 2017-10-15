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
import java.util.Optional;
import lombok.Data;

/**
 *
 * @author deepak
 */
@Table(tableName = "review_rating")
public @Data class Rating {
    @ID(dbField = "rating_id", fieldType = Integer.class)
    private int ratingId;
    private String ratingDiscription;
    private double ratingStar;
    
    public static GenericService<Rating> service = new GenericService<>(Rating.class, Rating::createRatingFromResultSet);
    
    
    public Rating( double ratingStar, String ratingDiscription){
        this.ratingDiscription = ratingDiscription;
        this.ratingStar = ratingStar;
    }
    
    public Rating(int ratingId, double ratingStar, String ratingDiscription){
        this(ratingStar, ratingDiscription);
        this.ratingId = ratingId;
    }
    
     public static Rating createRatingFromResultSet(ResultSet rs) throws SQLException{
        return new Rating(rs.getInt(1), rs.getDouble(2), rs.getString(3));
    }
     
    public static void main(String...args){
        Rating.service.all().forEach(System.out::println);
    }
}
