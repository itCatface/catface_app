package cc.catface.api.banner.domain;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class BannerBean {

    private int icon;
    private String title;


    public BannerBean(int icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
