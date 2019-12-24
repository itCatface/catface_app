package cc.catface.api.room.domain;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Entity(tableName = "user") public class User {

    @PrimaryKey(autoGenerate = true) private int id;
    private String name;
    private int age;
    private int sex;

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
    //这里的getter/setter方法是必须的
    //这里的getter/setter方法是必须的
    //这里的getter/setter方法是必须的
    //重要的事说三遍


    @Override public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + ", sex=" + sex + '}';
    }
}