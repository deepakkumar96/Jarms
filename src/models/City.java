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
@Table(tableName = "city")
public @Data class City {
    @ID(dbField = "city_id", fieldType = Integer.class)
    private int cityId;
    private String cityName;
    private int state;
    
    public static GenericService<City> service = new GenericService<>(City.class, City::createCityFromResultSet);
    
    public City(String cityName, int state){
        this.cityName = cityName;
        this.state = state;
    }
    
    public City(int cityId, String cityName, int state){
        this(cityName, state);
        this.cityId = cityId;
    }
    
    public Optional<State> getState(){
        Optional<State> st = State.service.get(state+"");
        if(st.isPresent())
            return st;
        else
            return Optional.empty();
    }
    
    public static City createCityFromResultSet(ResultSet rs) throws SQLException{
        return new City(rs.getInt(1), rs.getString(2), rs.getInt(3));
    }
    
    public static void main(String...args){
        City.service.all().forEach(System.out::println);
    }
}
