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
@Table(tableName = "country")
public @Data class Country {
    @ID(dbField = "country_id", fieldType = Integer.class)
    private int countryId;
    private String countryName;
    
    public static GenericService<Country> service = new GenericService<>(Country.class, Country::createCountryFromResultSet);
    
    public Country(String countryName){
        this.countryName = countryName;
    }
    
    public Country(int countryId, String countryName){
        this(countryName);
        this.countryId = countryId;
    }
    
    public static Country createCountryFromResultSet(ResultSet rs) throws SQLException{
        return new Country(rs.getInt(1), rs.getString(2));
    }
    
    public static void main(String...args){
        //Country.service.delete("5");
        Country.service.filter("country_name LIKE 'A%'")
                       .forEach(System.out::println);
    }
}
