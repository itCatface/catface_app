package cc.catface.api.banner;

import android.view.View;

import java.util.Arrays;

import androidx.recyclerview.widget.RecyclerView;
import cc.catface.api.R;
import cc.catface.api.banner.edit.RVCustomBannerView;
import cc.catface.api.banner.edit.RVDefaultBannerView;
import cc.catface.api.databinding.ApiActivityBannerBinding;
import cc.catface.base.core_framework.base_normal.NormalFragment;
import cc.catface.base.utils.android.common_print.dialog.normal.TDialogNormal;
import cc.catface.base.utils.android.common_print.toast.TToast;
import cc.catface.base.utils.android.common_title.TitleFontAwesome;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class DemoBannerFm extends NormalFragment<ApiActivityBannerBinding> {
    @Override public int layoutId() {
        return R.layout.api_activity_banner;
    }

    private String[] mImgUrls = {
            "http://wallpapersqq.net/wp-content/uploads/2015/11/allen-iverson-wallpapers-wide-desktop-wallpapers.jpg",
            "http://img.dianying.fm/media/backdrops/7a/7ax1vTwU5MpkRojxqejkX9fqlHq.jpg",
            "http://pic1.win4000.com/wallpaper/7/50921fa77c258.jpg",
            "http://n.sinaimg.cn/sinacn/20161226/5fca-fxyxute3388729.jpg",
            "http://t1.huanqiu.cn/6b6e291d95d20583c238613337c48d70.jpeg"};

    @Override public void createView() {
        initTitle();

        mBinding.rvdbv.setVisibility(View.VISIBLE);
        mBinding.rvcbv.setVisibility(View.VISIBLE);

        /*  */
        mBinding.rvdbv.init(Arrays.asList(mImgUrls), new RVDefaultBannerView.OnItemClickListener() {
            @Override public void onItemClick(int position) {
                TToast.get(mActivity).showBShortView("点击：" + position + " - " + mImgUrls[position], TToast.B_SUCCESS);
            }

            @Override public void onItemLongClick(int position) {
                TToast.get(mActivity).showBShortView("长按：" + position + " - " + mImgUrls[position], TToast.B_SUCCESS);
            }
        });
        mBinding.rvdbv.play();
        mBinding.rvcbv.init(Arrays.asList(mImgUrls), new RVCustomBannerView.OnItemClickListener() {
            @Override public void onItemClick(int position) {
                TToast.get(mActivity).showBShortView("点击：" + position + " - " + mImgUrls[position], TToast.B_SUCCESS);
            }

            @Override public void onItemLongClick(int position) {
                TToast.get(mActivity).showBShortView("长按：" + position + " - " + mImgUrls[position], TToast.B_SUCCESS);
            }
        });
        mBinding.rvcbv.play();
    }


    private void initTitle() {
        mBinding.tfaBanner.setTitle("rv-->banner").setIcon1(R.string.fa_chevron_left).setIcon4("模式").setOnClickListener((TitleFontAwesome.OnClickListener) view -> {
            if (view.getId() == R.id.ttv4) {
                TDialogNormal.get(mActivity).list("选择轮播模式", new String[]{"正常纯轮播页", "正常带标题及指示器", "纵向纯轮播页"}, chosenPosition -> {
                    if (chosenPosition == 0) {
                        mBinding.rvdbv.setLayoutManager(RecyclerView.HORIZONTAL);
                        mBinding.rvdbv.showTitle(false);
                        mBinding.rvdbv.showIndicator(false);
                        mBinding.rvcbv.setLayoutManager(RecyclerView.HORIZONTAL);
                        mBinding.rvcbv.showTitle(false);
                        mBinding.rvcbv.showIndicator(false);
                    } else if (chosenPosition == 1) {
                        mBinding.rvdbv.setLayoutManager(RecyclerView.HORIZONTAL);
                        mBinding.rvdbv.showTitle(true);
                        mBinding.rvdbv.showIndicator(true);
                        mBinding.rvcbv.setLayoutManager(RecyclerView.HORIZONTAL);
                        mBinding.rvcbv.showTitle(true);
                        mBinding.rvcbv.showIndicator(true);
                    } else if (chosenPosition == 2) {
                        mBinding.rvdbv.setLayoutManager(RecyclerView.VERTICAL);
                        mBinding.rvcbv.setLayoutManager(RecyclerView.VERTICAL);
                    }
                });
            }
        });
    }
}
