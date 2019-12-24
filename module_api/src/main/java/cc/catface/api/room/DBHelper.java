package cc.catface.api.room;

import android.content.Context;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import cc.catface.api.room.dao.BookDao;
import cc.catface.api.room.dao.CatDao;
import cc.catface.api.room.dao.UserDao;
import cc.catface.api.room.domain.Book;
import cc.catface.api.room.domain.Cat;
import cc.catface.api.room.domain.User;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 * -
 * 注意事项
 * 1.新增或建表时的integer字段需要添加not null default default_value[Migration didn't properly handle]
 *
 * 2.表结构变动后
 * a.) version不变 || 增加version不提供migration--crash
 * b.) 增加version使用fallback migration--数据被清除
 * c.)增加version并提供migration--正常升级
 */
@Database(entities = {User.class, Book.class, Cat.class}, version = 4, exportSchema = true) public abstract class DBHelper extends RoomDatabase {

    private static final String DB_DIR = Environment.getExternalStorageDirectory().getPath()/* + "/db_catface/"*/;
    private static final String DB_NAME = "db_catface.db";


    private static volatile DBHelper instance;

    static synchronized DBHelper getInstance(Context context) {
        if (null == instance) {
            synchronized (DBHelper.class) {
                if (null == instance) {
                    instance = create(context);
                }
            }
        }
        return instance;
    }

    private static DBHelper create(final Context context) {
        return Room.databaseBuilder(context, DBHelper.class, DB_DIR + "/" + DB_NAME).
                // fallbackToDestructiveMigration().   // 找不到迁移规则时销毁重建数据库
                // fallbackToDestructiveMigrationFrom(1, 4).   // 从1到4版本升级销毁重建数据库
                        allowMainThreadQueries().   // 允许在主线程中执行操作(影响流畅性)
                addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4).build();
    }

    public abstract UserDao getUserDao();

    /* 升级添加字段√ */
    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("alter table user add column sex integer not null default 0");

        }
    };

    /* 升级添加表√ */
    public abstract BookDao getBookDao();

    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("create table if not exists 'book' ('id' integer primary key autoincrement not null, 'name' text, 'price' integer not null default 0)");
        }
    };

    public abstract CatDao getCatDao();

    private static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("create table if not exists 'cat' ('id' integer primary key autoincrement not null, 'name' text, 'age' integer not null default 0)");
        }
    };
}