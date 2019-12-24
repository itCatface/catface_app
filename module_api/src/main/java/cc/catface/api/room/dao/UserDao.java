package cc.catface.api.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import cc.catface.api.room.domain.User;

@Dao
public interface UserDao {

    @Query("select count(*) from user")
    long count();

    @Query("SELECT * FROM user ORDER BY CASE WHEN NAME='--公正' THEN 0 ELSE 1 END, id DESC")
    List<User> sort();

    @Query("SELECT * FROM user")
    List<User> getAllUsers();

    @Query("SELECT * FROM user WHERE id % 2 != 0")
    List<User> getOddUsers();

    @Query("SELECT * FROM user ORDER BY id DESC")
    List<User> getAllUsersDesc();

    @Insert
    void insert(User... users);

    @Query("update user set name=:name where id=:id")
    void update(String name, int id);

//    @Query("UPDATE user set user WHERE id = (:id)")
//    void update(User user, int id);

    @Delete
    void delete(User... users);

    @Query("DELETE FROM user WHERE id = (:id)")
    void delete(int id);

    @Query("DELETE FROM user")
    void deleteAll();
}