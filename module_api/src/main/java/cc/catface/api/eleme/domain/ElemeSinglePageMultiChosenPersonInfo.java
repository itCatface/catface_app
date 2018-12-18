package cc.catface.api.eleme.domain;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ElemeSinglePageMultiChosenPersonInfo {

    private String text;
    private int age;
    private boolean isChosen = false;

    public ElemeSinglePageMultiChosenPersonInfo(String text, int age, boolean isChosen) {
        this.text = text;
        this.age = age;
        this.isChosen = isChosen;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isChosen() {
        return isChosen;
    }

    public void setChosen(boolean chosen) {
        isChosen = chosen;
    }
}
