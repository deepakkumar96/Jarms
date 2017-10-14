/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.function.Consumer;

/**
 *
 * @author deepak
 */

public class Row implements Iterable<Column<?>>{
    
    public List<Column<?>> columns;
    
    private Map<String, List<?>> cols;
    
    public Row(int size){
        columns = new ArrayList<>(size);
    }
    
    public boolean add(Column<?> col){
        columns.add(col);
        return true;
    }
    
    @Override
    public String toString(){
        return columns.toString();
    }
    
    public static Row of(Column<?>...cols){
        Row row = new Row(cols.length);
        for(Column<?> col: cols)
            row.add(col);
        return row;
    }

    @Override
    public Iterator<Column<?>> iterator() {
        return columns.iterator();
    }

    @Override
    public void forEach(Consumer<? super Column<?>> action) {
        Iterable.super.forEach(action); //To change body of generated methods, choose Tools | Templates.
    }



    
    
}
