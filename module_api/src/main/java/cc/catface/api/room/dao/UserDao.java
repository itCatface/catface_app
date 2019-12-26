package cc.catface.api.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import cc.catface.api.room.domain.User;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Dao
public interface UserDao {

    /** 查 */
    @Query("SELECT * FROM user")
    List<User> allUsers();

    @Query("SELECT * FROM user WHERE id % 2 != 0")
    List<User> oddUsers();

    @Query("SELECT * FROM user ORDER BY id DESC")
    List<User> descUsers();

    @Query("SELECT * FROM user ORDER BY CASE WHEN NAME like '%和谐%' THEN 0 ELSE 1 END, age DESC")
    List<User> sortUsers();

    @Query("select count(*) from user")
    long totalCount();


    /** 增 */
    @Insert
    void insert(User... users);

    /** 改 */
    @Query("update user set age=:age where id=:id")
    void update(int age, int id);

    /** 删 */
    @Query("DELETE FROM user")
    void deleteAll();

    @Delete
    void delete(User... users);

    @Query("DELETE FROM user WHERE id = (:id)")
    void delete(int id);


//    @Query("select ca.name ")
//    List<User> q();

}