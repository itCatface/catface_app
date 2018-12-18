package cc.catface.module_apis.brvah.domain;

import android.graphics.Bitmap;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class QuickBean {

    private String label;
    private Bitmap icon;

    public QuickBean(String label, Bitmap icon) {
        this.label = label;
        this.icon = icon;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }
}
