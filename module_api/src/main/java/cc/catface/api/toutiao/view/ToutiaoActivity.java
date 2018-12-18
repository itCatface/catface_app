package cc.catface.api.toutiao.view;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import cc.catface.api.R;
import cc.catface.api.toutiao.adapter.ToutiaoAdapter;
import cc.catface.api.toutiao.domain.TempDataOfToutiao;
import cc.catface.api.toutiao.domain.ToutiaoCommonBean;
import cc.catface.api.toutiao.presenter.ToutiaoPresenterImp;
import cc.catface.api.toutiao.toutiao_refresh.TodayNewsHeader;
import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.AbsAppCompatActivityID;
import cc.catface.base.utils.android.Timer.TTimer;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 * -
 * 说明：仿今日头条新闻首页，包含如下内容
 *
 * 1.多条目的RecyclerView使用
 */
@Route(path = "/api/toutiao")
@CreatePresenter(ToutiaoPresenterImp.class)
public class ToutiaoActivity extends AbsAppCompatActivityID<ToutiaoView, ToutiaoPresenterImp> implements ToutiaoView {
    @Override public int layoutId() {
        return R.layout.api_activity_toutiao;
    }

    private SmartRefreshLayout srl;
    private RecyclerView rv;
    private List<ToutiaoCommonBean> mData;

    @Override public void ids() {
        srl = (SmartRefreshLayout) findViewById(R.id.srl);
        rv = (RecyclerView) findViewById(R.id.rv);
    }

    @Override public void create() {
        initView();
        initData();
        initViewPagerOperator();
    }

    private void initView() {
        srl.setRefreshHeader(new TodayNewsHeader(this));
        srl.setOnRefreshListener(refreshLayout -> {
            srl.finishRefresh(2_000);
            TTimer.timeFinished(2_000, () -> {
                topText.setCommentTimes(random.nextInt(60_000));
                rv.getAdapter().notifyDataSetChanged();
            });
        });
    }


    /** 制造多条的数据 */
    Random random = new Random();

    private void initData() {
        mData = new ArrayList<>();

        handleTopText();
        handleTextImage1();
        handleTextImage3();
        handleTextVideo1();
        handleAdImage1();
        handleAdImage1Download();
        handleMoreSplendid();
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        Collections.shuffle(mData.subList(1, mData.size()));
        ToutiaoAdapter adapter = new ToutiaoAdapter(this, mData);
        rv.setAdapter(adapter);
    }
}
