package cc.catface.app.module.start.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class Student implements Parcelable {

    private String name;
    private int score;

    public Student() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(score);
    }

    private Student(Parcel in) {
        name = in.readString();
        score = in.readInt();
    }
}
