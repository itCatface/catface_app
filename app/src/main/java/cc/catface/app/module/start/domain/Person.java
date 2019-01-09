package cc.catface.app.module.start.domain;

import java.io.Serializable;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class Person implements Serializable {

    private static final long serialVersionUID = -4298488259928482555L;
    private String name;
    private int age;

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
