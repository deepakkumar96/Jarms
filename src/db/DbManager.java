/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public enum DbManager {
    INSTANCE;
    private Connection con;
    
    public Connection getConnection(){
        if(con == null)
        { 
            try{
                Class.forName("org.postgresql.Driver");
                con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/jarms_db","jarms", "jarms"
                );
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return con;
    }
    
}
