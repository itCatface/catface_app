package cc.catface.api.room.domain;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Entity(tableName = "cat") public class Cat {

    @PrimaryKey(autoGenerate = true) private int id;
    private String name;
    private int age;

    @Override public String toString() {
        return "Cat{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + '}';
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
}
