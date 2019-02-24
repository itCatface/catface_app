package cc.catface.api.toutiao.adapter;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.viewpager.widget.PagerAdapter;

public class MoreSplendidViewPagerAdapter extends PagerAdapter {
    private List<View> list;

    public MoreSplendidViewPagerAdapter(List<View> list) {
        super();
        this.list = list;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));// 删除页卡
    }

    // 这个方法用来实例化页卡
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position), 0);// 添加页卡
        return list.get(position);
    }

    // 返回页卡的数量
    public int getCount() {
        return null == list ? 0 : list.size();
    }

    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;// 官方提示这样写
    }
}