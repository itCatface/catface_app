package cc.catface.app_provider_provider.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Entity(tableName = "book")
public class Book {
    @PrimaryKey(autoGenerate = true) private int id;
    @ColumnInfo(defaultValue = "book_name") private String name;
    private int price;

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", name='" + name + '\'' + ", price=" + price + '}';
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
