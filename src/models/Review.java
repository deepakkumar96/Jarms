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
import lombok.Data;
import util.ModelUtil;

/**
 *
 * @author deepak
 */
@Table(tableName = "review")
public @Data class Review {
    @ID(dbField = "review_id", fieldType = Integer.class)
    private int reviewId;
    private String reviewDiscription;
    private Date reviewDate;
    private int belongTo;
    private int workId;
    private int userId;
    private int ratingId;
    
    public static GenericService<Review> service = new GenericService<>(Review.class, Review::createReviewFromResultSet);
    
    
    public Review(String reviewDiscription, Date reviewDate, int belongTo, int workId, int userId, int ratingId){
        this.reviewDiscription = reviewDiscription;
        this.reviewDate = reviewDate;
        this.belongTo = belongTo;
        this.workId = workId;
        this.userId = userId;
        this.ratingId = ratingId;
    }
    
    public Review(int reviewId, String reviewDiscription, Date reviewDate, int belongTo, int workId, int userId, int ratingId){
        this(reviewDiscription, reviewDate, belongTo, workId, userId, ratingId);
        this.reviewId = reviewId;
    }
    
    public Optional<? extends Document> getDocument(){
        return ModelUtil.getDocument(belongTo, workId);
    }
    
    public Optional<User> getUser(){
        return ModelUtil.getUser(userId);
    }
    
    public Optional<Rating> getRating(){
        return ModelUtil.getRating(ratingId);
    }
    
    public static Review createReviewFromResultSet(ResultSet rs) throws SQLException{
            return new Review(rs.getInt(1),  rs.getString(2),
                               rs.getDate(3), rs.getInt(4),
                               rs.getInt(5),rs.getInt(6),rs.getInt(7)
            );
    } 
    
    public static void main(String...args){
        Review rw = Review.service.get(1+"").orElse(null);
        rw.getDocument();
        rw.getUser();
    }
}
