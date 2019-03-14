package cc.catface.api.room.dao;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import cc.catface.api.room.domain.User;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAllUsers();

    @Insert
    void insert(User... users);

    @Update
    void update(User... users);

//    @Query("UPDATE user set user WHERE id = (:id)")
    void update(User user, int id);

    @Delete
    void delete(User... users);

    @Query("DELETE FROM user WHERE id = (:id)")
    void delete(int id);

    @Query("DELETE FROM user")
    void deleteAll();
}