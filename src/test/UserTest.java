/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import db.ModelServiceFactory;
import models.Article;
import models.User;
import db.SqlTable;
import java.util.Optional;
import models.Bookmark;

/**
 *
 * @author deepak
 */
public class UserTest {
    
    public static void main(String[] args) {
        User.service.all().forEach(System.out::println);  // fetch all users
        
        Optional<User> user = User.service.get(1+""); // fetching user with id = 1
        
        user.ifPresent(usr ->{
            System.out.println("\nUser :> " + usr);
            
            //checking user's address
            usr.getAddress().ifPresent(addr ->{
                System.out.println(usr.getFirstName() + "'s Address :> " + addr);
            });
            
            usr.getUserType().ifPresent(type ->{
                System.out.println(usr.getFirstName() + "'s Type :> " + type);
            });
        });
        
        System.out.println("\nARTICLES: ");
        Article.service.filter("article_content LIKE '%space%'")
                       .forEach(System.out::println);
        
        
        //Bookmarks
        System.out.print("\n-----------------------------------\nBookmark Test : ");
        Optional<Bookmark> b1 = Bookmark.service.get(1+"");
        
        b1.ifPresent(bk ->{
            System.out.println("Bookmark : " + bk);
            
            //fetching Bookmark's Document
            bk.getDocument().ifPresent(doc ->{
                System.out.println("Bookmark's Dcoument :> " + doc);
            });
        });
        
    }
    
}
