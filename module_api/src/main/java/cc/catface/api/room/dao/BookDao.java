package cc.catface.api.room.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import cc.catface.api.room.domain.Book;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Dao public interface BookDao {

    @Query("select * from book") List<Book> allBook();

}
