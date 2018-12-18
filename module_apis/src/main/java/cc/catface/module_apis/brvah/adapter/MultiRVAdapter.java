package cc.catface.module_apis.brvah.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.MultipleItemRvAdapter;

import java.util.List;

import cc.catface.module_apis.brvah.adapter.provider.ImgItemProvider;
import cc.catface.module_apis.brvah.adapter.provider.TextImgItemProvider;
import cc.catface.module_apis.brvah.adapter.provider.TextItemProvider;
import cc.catface.module_apis.brvah.domain.NormalMultipleEntity;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class MultiRVAdapter extends MultipleItemRvAdapter<NormalMultipleEntity, BaseViewHolder> {
    public static final int TYPE_TEXT = 100;
    public static final int TYPE_IMG = 200;
    public static final int TYPE_TEXT_IMG = 300;

    public MultiRVAdapter(@Nullable List<NormalMultipleEntity> data) {
        super(data);
        finishInitialize();
    }

    @Override protected int getViewType(NormalMultipleEntity normalMultipleEntity) {
        if (normalMultipleEntity.type == NormalMultipleEntity.SINGLE_TEXT) {
            return TYPE_TEXT;
        } else if (normalMultipleEntity.type == NormalMultipleEntity.SINGLE_IMG) {
            return TYPE_IMG;
        } else {
            return TYPE_TEXT_IMG;
        }
    }

    @Override public void registerItemProvider() {
        mProviderDelegate.registerProvider(new TextItemProvider());
        mProviderDelegate.registerProvider(new ImgItemProvider());
        mProviderDelegate.registerProvider(new TextImgItemProvider());
    }
}
