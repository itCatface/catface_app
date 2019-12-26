package cc.catface.api.room;

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
import cc.catface.ctool.context.TContext;

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
 *
 * 3.初始化数据库文件只能在系统目录内
 */
@Database(entities = {User.class, Book.class, Cat.class}, version = 4, exportSchema = true) public abstract class DBHelper extends RoomDatabase {

    private static final String DB_DIR = Environment.getExternalStorageDirectory().getPath() + "/db_catface/";
    private static final String DB_NAME = "db_catface";

    static synchronized DBHelper getInstance() {
        return Holder.instance;
    }

    private static class Holder {
        // return Room.databaseBuilder(TContext.getContext(), DBHelper.class, DB_DIR + "/" + DB_NAME).
        private static DBHelper instance = Room.databaseBuilder(TContext.getContext(), DBHelper.class, DB_NAME).
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

    /* 升级删除字段√ */
    private static final Migration MIGRATION_x_x = new Migration(-1, -1) {
        @Override public void migrate(@NonNull SupportSQLiteDatabase database) {
            // 创建一个新的表并且复制数据到新表中
            database.execSQL("CREATE TABLE new_stock_goods (goods_barcode TEXT NOT NULL," + " goods_name TEXT NOT NULL, goods_price REAL NOT NULL, id INTEGER NOT NULL, goods_id TEXT NOT NULL," + " shop_id INTEGER NOT NULL, count INTEGER NOT NULL, PRIMARY KEY(goods_barcode))");
            // 复制旧表中的所需数据到新表之中
            database.execSQL("INSERT INTO new_stock_goods(goods_barcode, goods_name, goods_price, id, goods_id, shop_id, count) " + "SELECT goods_barcode, goods_name, goods_price, id, goods_id, shop_id, count FROM stockgoods");
            // 删除旧表
            database.execSQL("DROP TABLE IF EXISTS stockgoods");
            // 修改新表的名称为旧表
            database.execSQL("ALTER TABLE new_stock_goods RENAME TO stockgoods");
        }
    };
}