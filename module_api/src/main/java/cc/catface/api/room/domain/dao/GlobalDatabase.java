package cc.catface.api.room.domain.dao;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import cc.catface.api.room.domain.User;
import cc.catface.app_base.ARouterApp;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Database(entities = {User.class}, version = 1)
public abstract class GlobalDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    private static GlobalDatabase javaDatabase;

    public static GlobalDatabase instance() {
        if (javaDatabase == null) {
            synchronized (GlobalDatabase.class) {
                if (javaDatabase == null) {
                    javaDatabase = Room.databaseBuilder(ARouterApp.getContext(), GlobalDatabase.class, "test").
                            addCallback(new Callback() {
                                @Override public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    System.out.println("onCreate===========" + db.getVersion() + "===" + db.getPath());
                                }

                                @Override public void onOpen(@NonNull SupportSQLiteDatabase db) {
                                    super.onOpen(db);
                                    System.out.println("onOpen===========" + db.getVersion() + "===" + db.getPath());
                                }
                            }).allowMainThreadQueries()//允许在主线程查询数据
                            .addMigrations(migration)//迁移数据库使用，下面会单独拿出来讲
                            .fallbackToDestructiveMigration()//迁移数据库如果发生错误，将会重新创建数据库，而不是发生崩溃
                            .build();
                }
            }
        }
        return javaDatabase;
    }

    //数据库升级用的
    static Migration migration = new Migration(1, 4) {
        @Override public void migrate(@NonNull SupportSQLiteDatabase database) {
            System.out.println("migrate============" + database.getVersion());
            database.execSQL("ALTER TABLE User " + " ADD COLUMN address TEXT");
        }
    };

}
