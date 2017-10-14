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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import db.GenericService;
import lombok.Data;
import util.ThrowingFunction;

/**
 *
 * @author deepak
 */

@Table(tableName = "article")
public @Data class Article implements Document{
    
    @ID(dbField = "article_id", fieldType = Integer.class)
    private int articleId;
    private String articleTitle;
    private String articleImagePath;
    private String articleContent;
    private String articleSummary;
    private int articleCategory;
    private int articleUser;
    
    public static GenericService<Article> service = new GenericService<Article>(Article.class, Article::createArticleFromResultSet);
    
    
    public Article(){System.out.println("Constructing");}
    public Article(int id, String title, String image, String content, String summary, int category, int user){
        this.articleId = id;
        this.articleTitle = title;
        this.articleContent = content;
        this.articleImagePath = image;
        this.articleSummary = summary;
        this.articleCategory = category;
        this.articleUser = user;
    }
    
    public static Article createArticleFromResultSet(ResultSet rs) throws SQLException{
            return new Article(rs.getInt(1),  rs.getString(2),
                               rs.getString(3), rs.getString(4),
                               rs.getString(5),rs.getInt(6),rs.getInt(7)
            );
    }    
    
    
    @Override
    public String toString(){
        return this.articleTitle;
    }

    public static void main(String[] args){
        System.out.print(Article.service.all());
    }
}
