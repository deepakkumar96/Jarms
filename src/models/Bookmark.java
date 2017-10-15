
package models;

import util.DocumentType;
import anotations.ID;
import anotations.Table;
import db.GenericService;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import lombok.Data;
import util.ModelUtil;

/**
 *
 * @author deepak
 */

@Table(tableName="bookmark")
public @Data class Bookmark {
    @ID(dbField = "bookmark_id", fieldType = Integer.class)
    private int bookmarkId;
    private Date bookmarkDate;
    private int belongTo;
    private int workId;
    private int userId;
    
    public static GenericService<Bookmark> service = new GenericService<>(Bookmark.class, Bookmark::createBookmarkFromResultSet);
    
    public Bookmark(Date bookmarkDate, int belongTo, int workId, int userId){
        this.bookmarkDate = bookmarkDate;
        this.belongTo = belongTo;
        this.workId = workId;
        this.userId = userId;
    }
    
    public Bookmark(int bookmarkId, Date bookmarkDate, int belongTo, int workId, int userId){
        this(bookmarkDate, belongTo, workId, userId);
        this.bookmarkId = bookmarkId;
    }
    
    /*
        This assumes work_id is of correct type
        It returns instance of Document based on workId
    */
    public Optional<? extends Document> getDocument(){
        return ModelUtil.getDocument(belongTo, workId);
    }
    
    public Optional<User> getUser(){
        return ModelUtil.getUser(userId);
    }
    
    public static Bookmark createBookmarkFromResultSet(ResultSet rs) throws SQLException{
        return new Bookmark(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
    }
    
    // test
    public static void main(String...args){
        Bookmark.service.filter("NOT work_id = 1").forEach(System.out::println);
        
        Optional<Bookmark> bm = Bookmark.service.get("1");
        
        bm.ifPresent(bmk ->{
            bmk.getDocument().ifPresent(System.out::println);
        });
        
    }
}
