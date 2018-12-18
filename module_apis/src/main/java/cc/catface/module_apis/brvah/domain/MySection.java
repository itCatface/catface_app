package cc.catface.module_apis.brvah.domain;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class MySection extends SectionEntity<Video> {
    private boolean isMore;

    public MySection(boolean isHeader, String header, boolean isMore) {
        super(isHeader, header);
        this.isMore = isMore;
    }

    public MySection(Video t) {
        super(t);
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean more) {
        isMore = more;
    }
}
