/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author deepak
 */
public class JoinedTable implements SqlTable{
    
    /*
    data stores the whole table data in HashMap
    Each column is represented as HashMap key
    */
    private final Map<String, List<Object>> data = new HashMap<>(); 
    private final String tableName;
    
    public JoinedTable(String name){
        this.tableName = name;
    }
    
    public boolean add(String col, Object obj){
        data.putIfAbsent(col, new ArrayList<>());
        data.get(col).add(obj);
        return true;
    }
    
    @Override
    public Optional<Object> get(String col, int i) {
        List<Object> colValues = data.get(col);
        if(colValues != null){
            if(i < 0 || i >= colValues.size()) 
                return Optional.empty();
            else
                return Optional.of(colValues.get(i));
        }
        return Optional.empty();
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("#"+getTableName()+"#\n");
        data.entrySet().forEach(entry ->{
            sb.append(entry.getKey()).append("\t\t=> ");
            entry.getValue().forEach(v -> sb.append(v+"\t"));
            sb.append("\n");
        });
        return sb.toString();
    }

    @Override
    public int getRowCount() {
        return data.values().size();
    }

    @Override
    public int getColumnCount() {
        return data.keySet().size();
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public Set<String> getColumns() {
        return data.keySet();
    }

    @Override
    public Optional<List<Object>> getColumnValues(String colName) {
        if(data.get(colName) != null)
            return Optional.of(data.get(colName));
        return Optional.empty();
    }
    
}
