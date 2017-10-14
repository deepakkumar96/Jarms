/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import db.GenericService;

/**
 *
 * @author deepak
 */
public abstract class Model {
    public static GenericService<Model> service;
    
    public Model(GenericService<Model> service){
        Model.service = service;
    }
            
}
