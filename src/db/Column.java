/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**
 *
 * @author deepak
 */
public class Column<T>{
    
    private String name;
    private T value;
    
    public Column(String name, T value){
        this.value = value;
        this.name = name;
    }
    
    @Override
    public String toString(){
        return value.toString();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
    
    
}
