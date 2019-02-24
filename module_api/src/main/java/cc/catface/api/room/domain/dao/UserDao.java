package cc.catface.api.room.domain.dao;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import cc.catface.api.room.domain.User;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Dao
public interface UserDao {

    @Query("select * from User") List<User> getAll();

    @Query("select * from User where uid%2==0") List<User> getAllOdd();

    @Insert void insert(User... user);

    @Delete void delete(User... user);

    @Update void update(User... user);

}
