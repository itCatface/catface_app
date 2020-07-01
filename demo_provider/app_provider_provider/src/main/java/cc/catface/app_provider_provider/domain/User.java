package cc.catface.app_provider_provider.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Entity(tableName = "user")
public class User {

    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) private int id;
    @ColumnInfo(name = "name") private String name;
    @ColumnInfo(name = "age") private int age;
    @ColumnInfo(name = "sex") private int sex;  // 777由client操作provider设置的
    @ColumnInfo(name = "create_time") private long createTime;

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public User() { }

    public User(String name, int age, long createTime) {
        this.name = name;
        this.age = age;
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" + name + '-' + age + "-" + sex + "-" + createTime + "}";
    }
}