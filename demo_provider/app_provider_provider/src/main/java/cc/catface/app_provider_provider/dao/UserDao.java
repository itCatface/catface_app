package cc.catface.app_provider_provider.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import cc.catface.app_provider_provider.domain.User;


/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 *
 * 该dao包含基础的表操作，分为四大类
 * ----增
 * --------返回值: long/long[]/List<Long>
 * --------添加冲突策略: @Insert(onConflict = OnConflictStrategy.*)
 * ------------OnConflictStrategy.REPLACE   冲突策略是取代旧数据同时继续事务
 * ------------OnConflictStrategy.ROLLBACK  冲突策略是回滚事务
 * ------------OnConflictStrategy.ABORT     冲突策略是终止事务[room默认值 && 会崩溃]
 * ------------OnConflictStrategy.FAIL      冲突策略是事务失败
 * ------------OnConflictStrategy.IGNORE    冲突策略是忽略冲突
 * ----删
 * ----改
 * --------返回值: int
 * --------添加冲突策略: @Insert(onConflict = OnConflictStrategy.*)
 * ----查
 */
@Dao
public interface UserDao {

    /******
     * 增
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertReplace(User user);

    // android.database.sqlite.SQLiteException: cannot rollback - no transaction is active (code 1 SQLITE_ERROR)
    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    long insertRollback(User user);

    // android.database.sqlite.SQLiteConstraintException: UNIQUE constraint failed: user.id (code 1555 SQLITE_CONSTRAINT_PRIMARYKEY)
    @Insert(onConflict = OnConflictStrategy.ABORT)
    long insertAbort(User user);

    // android.database.sqlite.SQLiteConstraintException: UNIQUE constraint failed: user.id (code 1555 SQLITE_CONSTRAINT_PRIMARYKEY)
    @Insert(onConflict = OnConflictStrategy.FAIL)
    long insertFail(User user);

    // 忽略冲突不插入或更新记录
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertIgnore(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertReturnList(User... users);

    // android.database.sqlite.SQLiteConstraintException: UNIQUE constraint failed: user.id (code 1555 SQLITE_CONSTRAINT_PRIMARYKEY)
    // 所有记录均不会插入或更新到表中
    @Insert(onConflict = OnConflictStrategy.ABORT)
    List<Long> insertAbortReturnList(User... users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertReturnArray(User... users);


    /**
     * 查
     */
    @Query("SELECT * FROM user order by create_time desc")
    List<User> allUsers();

    @Query("select count(*) from user")
    long totalCount();


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