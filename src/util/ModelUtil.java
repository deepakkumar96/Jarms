/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import db.GenericService;
import java.util.Optional;
import models.Address;
import models.Article;
import models.Category;
import models.City;
import models.Country;
import models.Document;
import models.Rating;
import models.SecurityQuestion;
import models.State;
import models.User;
import models.UserRole;
import models.UserType;

/**
 *
 * @author deepak
 */
public class ModelUtil<T> {
    
    public static <T> Optional<T> getModelById(GenericService service, int id){
        Optional<T> model = service.get(id+"");
        if(model.isPresent()) return model;
        else return Optional.empty();
    }
    
    public static Optional<User> getUser(int userId){
        Optional<User> usr = User.service.get(userId+"");
        if(usr.isPresent()) return usr;
        else return Optional.empty();
    }
    
    public static Optional<Category> getCategory(int category){
        Optional<Category> catg = Category.service.get(category+"");
        if(catg.isPresent()) return catg;
        else return Optional.empty();
    }
    
    public static Optional<UserType> getUserType(int userTypeId){
        Optional<UserType> userType = UserType.service.get(userTypeId+"");
        if(userType.isPresent()) return userType;
        else return Optional.empty();
    }
    
    public static Optional<UserRole> getUserRole(int userRoleId){
        Optional<UserRole> usrRole = UserRole.service.get(userRoleId+"");
        if(usrRole.isPresent()) return usrRole;
        else return Optional.empty();
    }
    
    public static Optional<Country> getCountry(int country){
        Optional<Country> st = Country.service.get("coutryOi"+"");
        if(st.isPresent())
            return st;
        else
            return Optional.empty();
    }
    
    public static Optional<State> getState(int state){
        Optional<State> st = State.service.get(state+"");
        if(st.isPresent())
            return st;
        else
            return Optional.empty();
    }
    
    public static Optional<City> getCity(int city){
        Optional<City> st = City.service.get(city+"");
        if(st.isPresent())
            return st;
        else
            return Optional.empty();
    }
    
    public static Optional<Address> getAddress(int addr){
        Optional<Address> address = Address.service.get(addr+"");
        if(address.isPresent()) return address;
        else return Optional.empty();
    }
    
    public static Optional<SecurityQuestion> getSecurityQuestion(int id){
        Optional<SecurityQuestion> secQue = SecurityQuestion.service.get(id+"");
        if(secQue.isPresent()) return secQue;
        else return Optional.empty();
    }
    
    public static Optional<Rating> getRating(int rateingId){
        Optional<Rating> st = Rating.service.get(rateingId+"");
        if(st.isPresent())
            return st;
        else
            return Optional.empty();
    }
    
    /*
        This assumes work_id is of correct type
        It returns instance of Document based on workId
    */
    public static Optional<? extends Document> getDocument(int belongsTo, int workId){
        Optional<? extends Document> document = null;
        switch(DocumentType.getType(belongsTo)){
            case ARTICLE:
                document = Article.service.get(workId+"");
        }
        if(document.isPresent()) return document;
        else return Optional.empty();
    }
    
}
