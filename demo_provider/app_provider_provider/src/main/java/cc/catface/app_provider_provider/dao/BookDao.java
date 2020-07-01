package cc.catface.app_provider_provider.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import cc.catface.app_provider_provider.domain.Book;


/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Dao
public interface BookDao {

    @Query("select * from book")
    List<Book> allBook();

}
