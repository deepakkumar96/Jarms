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
@Table(tableName = "security_question")
public @Data class SecurityQuestion {
    @ID(dbField = "security_que_id", fieldType = Integer.class)
    private int questionId;
    private String questionType;
    
    public static GenericService<SecurityQuestion> service = new GenericService<>(SecurityQuestion.class, SecurityQuestion::createSecurityQuestionFromResultSet);
    
    
    public SecurityQuestion(String question){
        this.questionType = question;
    }
    
    public SecurityQuestion(int questionId, String question){
        this(question);
        this.questionId = questionId;
    }
    
    public static SecurityQuestion createSecurityQuestionFromResultSet(ResultSet rs) throws SQLException{
        return new SecurityQuestion(rs.getInt(1), rs.getString(2));
    }
}
