package cc.catface.module_start.main.mvp.view.navigation.view;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.AbsFragmentID;
import cc.catface.module_start.R;
import cc.catface.module_start.main.mess.ApiFm;
import cc.catface.module_start.main.mess.ApisFm;
import cc.catface.module_start.main.mess.PjsFm;
import cc.catface.module_start.main.mvp.view.navigation.adapter.ImageTextAdapter;
import cc.catface.module_start.main.mvp.view.navigation.presenter.MessPresenterImp;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@CreatePresenter(MessPresenterImp.class)
public class MessFm extends AbsFragmentID<MessView, MessPresenterImp> implements MessView {
    @Override public int layoutId() {
        return R.layout.start_fm_mess;
    }

    private TabLayout tl_mess;
    private ViewPager vp_mess;

    private String[] mTabTitles = {"api", "apis", "pjs"};
    //    private String[] mTabTitles = {"GIF", "视频"};
    private List<Fragment> mFms;

    @Override public void ids(View v) {
        tl_mess = (TabLayout) v.findViewById(R.id.tl_mess);
        vp_mess = (ViewPager) v.findViewById(R.id.vp_mess);
    }

    @Override public void viewCreated() {
        LinearLayout ll = (LinearLayout) tl_mess.getChildAt(0);
        ll.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        ll.setDividerPadding(40);
        ll.setDividerDrawable(ContextCompat.getDrawable(mActivity, R.drawable.showapi_shape_top_bar_line));

        mFms = new ArrayList<>();
        mFms.add(new ApiFm());
        mFms.add(new ApisFm());
        mFms.add(new PjsFm());

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
