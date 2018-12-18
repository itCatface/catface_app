package cc.catface.module_apis.brvah.domain;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import cc.catface.module_apis.brvah.adapter.ExpandableAdapter;


/**
 * Created by luoxw on 2016/8/10.
 */
public class Level0Item extends AbstractExpandableItem<Level1Item> implements MultiItemEntity {
    public String title;
    public String subTitle;

    public Level0Item(String title, String subTitle) {
        this.subTitle = subTitle;
        this.title = title;
    }

    @Override
    public int getItemType() {
        return ExpandableAdapter.TYPE_LEVEL_0;
    }

    @Override
    public int getLevel() {
        return 0;
    }
}
