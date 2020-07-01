package cc.catface.app_provider_provider.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import cc.catface.app_provider_provider.domain.User;


/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Dao
public interface UserDao {

    /**
     * 查
     */
    @Query("SELECT * FROM user order by create_time desc")
    List<User> allUsers();

    @Query("select count(*) from user")
    long totalCount();


    /**
     * 增
     */
    @Insert
    void insert(User... users);

    /**
     * 改
     */
    /* 主键相同时更新数据 */
    @Update
    void update(User... user);

    /**
     * 删
     */
    @Query("DELETE FROM user")
    void deleteAll();

    /* 主键相同时更新数据 */
    @Delete
    void delete(User... users);

    @Query("DELETE FROM user WHERE id = (:id)")
    void delete(int id);

}