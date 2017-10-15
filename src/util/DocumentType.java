/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author deepak
 */
public enum DocumentType {
    ARTICLE, JOURNAL, RESEARCH_PAPER, UNKNOWN;
    
    public static DocumentType getType(int workId){
        switch (workId) {
            case 1:
                return ARTICLE;
            case 2:
                return JOURNAL;
            case 3:
                return RESEARCH_PAPER;
            default:
                return UNKNOWN;
        }
    }
}
