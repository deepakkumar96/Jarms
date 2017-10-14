/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author deepak
 */
public interface SqlTable {
    String getTableName();
    Set<String> getColumns();
    Optional<List<Object>> getColumnValues(String colName);
    Optional<Object> get(String col, int i);
    int getRowCount();
    int getColumnCount();
}
