package cc.catface.app.module.start.main.view.navigation.view;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cc.catface.app.R;
import cc.catface.app.module.mess.ApiFm;
import cc.catface.app.module.start.main.view.navigation.adapter.ImageTextAdapter;
import cc.catface.app.module.mess.ApisFm;
import cc.catface.app.module.start.main.view.navigation.presenter.MessPresenterImp;
import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.AbsFragment;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@CreatePresenter(MessPresenterImp.class)
public class MessFm extends AbsFragment<MessView, MessPresenterImp> implements MessView {
    @Override public int layoutId() {
        return R.layout.fm_main_tab_mess;
    }

    @BindView(R.id.tl_mess) TabLayout tl_mess;
    @BindView(R.id.vp_mess) ViewPager vp_mess;

    private String[] mTabTitles = {"api", "三方demo", "笑话(文)", "笑话(图)", "笑话(gif)", "GIF", "视频"};
    //    private String[] mTabTitles = {"GIF", "视频"};
    private List<Fragment> mFms;

    @Override public void viewCreated() {
        LinearLayout ll = (LinearLayout) tl_mess.getChildAt(0);
        ll.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        ll.setDividerPadding(40);
        ll.setDividerDrawable(ContextCompat.getDrawable(mActivity, R.drawable.showapi_shape_top_bar_line));

        mFms = new ArrayList<>();
        mFms.add(new ApiFm());
        mFms.add(new ApisFm());
//                        mFms.add(new YYJoke341_1Fm());
//                        mFms.add(new YYJoke341_2Fm());
//                        mFms.add(new YYJoke341_3Fm());

        vp_mess.setOffscreenPageLimit(mTabTitles.length);
        vp_mess.setAdapter(new ImageTextAdapter(getChildFragmentManager(), mTabTitles, mFms));
        tl_mess.setupWithViewPager(vp_mess);

        mListener.process("fragment处理结束");
    }


    /** fragment传参至activity */
    private FragmentListener mListener;

    public interface FragmentListener {
        void process(String process);
    }

    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof FragmentListener) mListener = (FragmentListener) activity;
        else throw new IllegalArgumentException("activity must implements BRVAH_AnimationListener...");

        //        if (null != mListener) mListener.process("onAttach...");
    }
    //
    //    @Override public void onStart() {
    //        super.onStart();
    //        if (null != mListener) mListener.process("onStart...");
    //    }
    //
    //    @Override public void onResume() {
    //        super.onResume();
    //        if (null != mListener) mListener.process("onResume...");
    //    }
    //
    //    @Override public void onPause() {
    //        super.onPause();
    //        if (null != mListener) mListener.process("onPause...");
    //    }
    //
    //    @Override public void onStop() {
    //        super.onStop();
    //        if (null != mListener) mListener.process("onStop...");
    //    }
    //
    //    @Override public void onDestroyView() {
    //        super.onDestroyView();
    //        if (null != mListener) mListener.process("onDestroyView...");
    //    }
    //
    //    @Override public void onDestroy() {
    //        super.onDestroy();
    //        if (null != mListener) mListener.process("onDestroy...");
    //    }

    @Override public void onDetach() {
        super.onDetach();
        if (null != mListener) {
            mListener.process("onDetach...");
            mListener = null;
        }
    }
}
