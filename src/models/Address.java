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
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author deepak
 */

@Table(tableName = "address")
public @Data class Address{
    @ID(dbField = "address_id", fieldType = Integer.class)
    private int addressId;
    private String zipCode;
    private String block;
    private String street;
    private int city;
    
    public static GenericService<Address> service = new GenericService<Address>(Address.class, Address::createAddressFromResultSet);
    
    public Address(String zipCode, String block, String street, int city){
        this.zipCode = zipCode;
        this.block = block;
        this.street = street;
        this.city = city;
    }
    
    public Address(int addressId, String zipCode, String block, String street, int city){
        this(zipCode, block, street, city);
        this.addressId = addressId;
    }
    
    public Optional<City> getCity(){
        Optional<City> st = City.service.get(city+"");
        if(st.isPresent())
            return st;
        else
            return Optional.empty();
    }
    
    public static Address createAddressFromResultSet(ResultSet rs) throws SQLException{
        return new Address(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
    }
    
    public static void main(String[] args){
       //Address.service.all().forEach(System.out::println);
       
        //Address adr = Address.service.get("1");
        //System.out.print(adr.getCity());
       
    }
}
