package cc.catface.api.eleme.domain;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ElemeMainBean {

    private int icon;
    private String label;

    public ElemeMainBean(int icon, String label) {
        this.icon = icon;
        this.label = label;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
