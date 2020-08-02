package cc.catface.app_dagger2_demo;

import java.io.Serializable;

public class User implements Serializable {

    private int id = 0;
    private String username = "";
    private String password = "";

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username='" + username + '\'' + ", password='" + password + '\'' + '}';
    }
}
