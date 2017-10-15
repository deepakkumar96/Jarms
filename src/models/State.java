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
import util.ModelUtil;

/**
 *
 * @author deepak
 */
@Table(tableName = "state")
public @Data class State {
    @ID(dbField = "state_id", fieldType = Integer.class)
    private int stateId;
    private String stateName;
    private int countryId = - 1;
    
    public static GenericService<State> service = new GenericService<>(State.class, State::createStateFromResultSet);
    
    
    public State(String stateName, int countryId){
        this.stateName = stateName;
        this.countryId = countryId;
    }
    
    public State(int stateId, String stateName, int countryId){
        this(stateName, countryId);
        this.stateId = stateId;
    }
    
    public Optional<Country> getCountry(){
        return ModelUtil.getCountry(countryId);
    }
    
    public static State createStateFromResultSet(ResultSet rs) throws SQLException{
        return new State(rs.getInt(1), rs.getString(2), rs.getInt(3));
    }
    
    public static void main(String... args){
        State st = State.service.get("10").orElse(null);
        
        System.out.println(st);
        
        Country c = st.getCountry().get();  
        
        //st.getCountry().ifPresent(System.out::println);
        
    }
}
