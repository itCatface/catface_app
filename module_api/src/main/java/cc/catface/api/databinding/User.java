package cc.catface.api.databinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;
import cc.catface.api.BR;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class User extends BaseObservable {

    private boolean isChecked;
    private String name;
    private boolean isMale;
    private int age;
    public final ObservableField<String> description = new ObservableField<>();


    public User(boolean isChecked, String name, boolean isMale, int age) {
        this.isChecked = isChecked;
        this.name = name;
        this.isMale = isMale;
        this.age = age;
    }

    @Bindable public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
        notifyPropertyChanged(BR.checked);
    }

    @Bindable public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        this.isMale = male;
        notifyPropertyChanged(BR.male);
    }

    @Bindable public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }

//    @Bindable
//    public String getShowDescription(String description) {
//        return description.substring(5, description.length());
//    }
}
