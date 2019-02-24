package cc.catface.api.room.domain;


import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Entity
public class User {

    @PrimaryKey(autoGenerate = true) public int uid;

    @ColumnInfo(name = "first_name") public String firstName;
    @ColumnInfo(name = "last_name") public String lastName;

    @Ignore Bitmap bitmap;


    public User(String firstName, String lastName, Bitmap bitmap) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bitmap = bitmap;
    }
}
