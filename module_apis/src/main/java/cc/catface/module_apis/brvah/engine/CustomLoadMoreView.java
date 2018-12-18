package cc.catface.module_apis.brvah.engine;

import com.chad.library.adapter.base.loadmore.LoadMoreView;

import cc.catface.module_apis.R;

/**
 * 仅供PullToRefresh类型使用
 */

public final class CustomLoadMoreView extends LoadMoreView {

    @Override public int getLayoutId() {
        return R.layout.brvah_item_view_load_more;
    }

    @Override protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
