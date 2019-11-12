package cc.catface.api.toutiao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityToutiaoBinding;
import cc.catface.api.toutiao.adapter.ToutiaoAdapter;
import cc.catface.api.toutiao.domain.TempDataOfToutiao;
import cc.catface.api.toutiao.domain.ToutiaoCommonBean;
import cc.catface.api.toutiao.toutiao_refresh.TodayNewsHeader;
import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.base.utils.android.Timer.TTimer;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 * -
 * 说明：仿今日头条新闻首页，包含如下内容
 *
 * 1.多条目的RecyclerView使用
 */
public class DemoToutiaoFm extends LightFm<LightPresenter, ApiActivityToutiaoBinding> {

    @Override public int layoutId() {
        return R.layout.api_activity_toutiao;
    }

    private List<ToutiaoCommonBean> mData;

    @Override protected void initView() {
        mBinding.srl.setRefreshHeader(new TodayNewsHeader(mActivity));
        mBinding.srl.setOnRefreshListener(refreshLayout -> {
            mBinding.srl.finishRefresh(2_000);
            TTimer.timeFinished(2_000, () -> {
                topText.setCommentTimes(random.nextInt(60_000));
                mBinding.rv.getAdapter().notifyDataSetChanged();
            });
        });
    }


    /** 制造多条的数据 */
    Random random = new Random();

    @Override public void initData() {
        mData = new ArrayList<>();

        handleTopText();
        handleTextImage1();
        handleTextImage3();
        handleTextVideo1();
        handleAdImage1();
        handleAdImage1Download();
        handleMoreSplendid();
        initViewPagerOperator();
    }

    ToutiaoCommonBean bean = new ToutiaoCommonBean();
    ToutiaoCommonBean.TopText topText = new ToutiaoCommonBean.TopText();

    private void handleTopText() {
        topText.setTitle("让世界聆听我们的声音");
        topText.setText("Let the world hear us.");
        topText.setMediaSrc(TempDataOfToutiao.mediaSrcs[random.nextInt(TempDataOfToutiao.mediaSrcs.length)]);
        topText.setCommentTimes(random.nextInt(10000));
        topText.setTime("time-");
        bean.setType(ToutiaoAdapter.TYPE_TOP_TEXT);
        bean.setTopText(topText);
        mData.add(bean);
    }

    private void handleTextImage1() {
        for (int i = 0; i < random.nextInt(5) + 4; i++) {
            ToutiaoCommonBean bean = new ToutiaoCommonBean();
            ToutiaoCommonBean.TextImage1 textImage1 = new ToutiaoCommonBean.TextImage1();
            textImage1.setTitle("我是标题哦还有一张图片呢(" + i + ")我是标题哦还有一张图片呢");
            textImage1.setImageUrl(TempDataOfToutiao.picUrls[random.nextInt(TempDataOfToutiao.picUrls.length)]);
            textImage1.setMediaSrc(TempDataOfToutiao.mediaSrcs[random.nextInt(TempDataOfToutiao.mediaSrcs.length)]);
            textImage1.setCommentTimes(random.nextInt(10000));
            textImage1.setTime("time--");

            bean.setType(random.nextInt(50) % 2 == 0 ? ToutiaoAdapter.TYPE_TEXT_IMAGE1_RIGHT : ToutiaoAdapter.TYPE_TEXT_IMAGE1_BOTTOM);
            bean.setTextImage1(textImage1);
            mData.add(bean);
        }
    }

    private void handleTextImage3() {
        for (int i = 0; i < random.nextInt(5) + 4; i++) {
            ToutiaoCommonBean bean = new ToutiaoCommonBean();
            ToutiaoCommonBean.TextImage3 textImage3 = new ToutiaoCommonBean.TextImage3();
            textImage3.setTitle("我是标题哦还有一张图片呢(" + i + ")我是标题哦还有三张图片呢");
            textImage3.setUrl1(TempDataOfToutiao.picUrls[random.nextInt(TempDataOfToutiao.picUrls.length)]);
            textImage3.setUrl2(TempDataOfToutiao.picUrls[random.nextInt(TempDataOfToutiao.picUrls.length)]);
            textImage3.setUrl3(TempDataOfToutiao.picUrls[random.nextInt(TempDataOfToutiao.picUrls.length)]);
            textImage3.setMediaSrc(TempDataOfToutiao.mediaSrcs[random.nextInt(TempDataOfToutiao.mediaSrcs.length)]);
            textImage3.setCommentTimes(random.nextInt(10000));
            textImage3.setTime("time---");

            bean.setType(ToutiaoAdapter.TYPE_TEXT_IMAGE3);
            bean.setTextImage3(textImage3);
            mData.add(bean);
        }
    }

    private void handleTextVideo1() {
        for (int i = 0; i < random.nextInt(5) + 4; i++) {
            ToutiaoCommonBean bean = new ToutiaoCommonBean();
            ToutiaoCommonBean.TextVideo1 textVideo1 = new ToutiaoCommonBean.TextVideo1();
            textVideo1.setTitle("我是标题哦还有一个视频呢(" + i + ")我是标题哦还有一个视频呢");
            textVideo1.setUrl(TempDataOfToutiao.picUrls[random.nextInt(TempDataOfToutiao.picUrls.length)]);
            textVideo1.setMediaSrc(TempDataOfToutiao.mediaSrcs[random.nextInt(TempDataOfToutiao.mediaSrcs.length)]);
            textVideo1.setCommentTimes(random.nextInt(10000));
            textVideo1.setTime("time----");

            bean.setType(random.nextInt(50) % 2 == 0 ? ToutiaoAdapter.TYPE_TEXT_VIDEO_RIGHT : ToutiaoAdapter.TYPE_TEXT_VIDEO_BOTTOM);
            bean.setTextVideo1(textVideo1);
            mData.add(bean);
        }
    }

    private void handleAdImage1() {
        for (int i = 0; i < random.nextInt(5) + 4; i++) {
            ToutiaoCommonBean bean = new ToutiaoCommonBean();
            ToutiaoCommonBean.AdImage1Bottom adImage1Bottom = new ToutiaoCommonBean.AdImage1Bottom();
            adImage1Bottom.setTitle("我是标题哦还有一张广告图片呢(" + i + ")我是标题哦还有一张广告图片呢");
            adImage1Bottom.setImageUrl(TempDataOfToutiao.picUrls[random.nextInt(TempDataOfToutiao.picUrls.length)]);
            adImage1Bottom.setLabel(TempDataOfToutiao.adLabel[random.nextInt(TempDataOfToutiao.adLabel.length)]);
            adImage1Bottom.setTime("time--");

            bean.setType(ToutiaoAdapter.TYPE_AD_IMAGE1_BOTTOM);
            bean.setAdImage1Bottom(adImage1Bottom);
            mData.add(bean);
        }
    }

    private void handleAdImage1Download() {
        for (int i = 0; i < random.nextInt(5) + 4; i++) {
            ToutiaoCommonBean bean = new ToutiaoCommonBean();
            ToutiaoCommonBean.AdImage1Bottom adImage1Bottom = new ToutiaoCommonBean.AdImage1Bottom();
            adImage1Bottom.setTitle("我是标题哦还有一张广告图片呢(" + i + ")我是标题哦还有一张广告图片呢");
            adImage1Bottom.setImageUrl(TempDataOfToutiao.picUrls[random.nextInt(TempDataOfToutiao.picUrls.length)]);
            adImage1Bottom.setLabel(TempDataOfToutiao.adLabel[random.nextInt(TempDataOfToutiao.adLabel.length)]);
            adImage1Bottom.setTime("time--");

            bean.setType(ToutiaoAdapter.TYPE_AD_IMAGE1_BOTTOM_DOWNLOAD);
            bean.setAdImage1Bottom(adImage1Bottom);
            mData.add(bean);
        }
    }

    private void handleMoreSplendid() {
        for (int i = 0; i < random.nextInt(5) + 4; i++) {
            ToutiaoCommonBean bean = new ToutiaoCommonBean();
            ToutiaoCommonBean.MoreSplendid moreSplendid = new ToutiaoCommonBean.MoreSplendid();
            moreSplendid.setUrls(TempDataOfToutiao.picUrlsOfPhone);

            bean.setType(ToutiaoAdapter.TYPE_MORE_SPLENDID);
            bean.setMoreSplendid(moreSplendid);
            mData.add(bean);
        }
    }


    /** 设置Adapter */
    private void initViewPagerOperator() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        mBinding.rv.setLayoutManager(linearLayoutManager);
        mBinding.rv.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
        Collections.shuffle(mData.subList(1, mData.size()));
        ToutiaoAdapter adapter = new ToutiaoAdapter(mActivity, mData);
        mBinding.rv.setAdapter(adapter);
    }
}
