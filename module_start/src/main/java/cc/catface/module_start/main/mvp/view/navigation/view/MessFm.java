package cc.catface.module_start.main.mvp.view.navigation.view;

import android.app.Activity;
import androidx.core.content.ContextCompat;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.MvpFragment;
import cc.catface.module_start.R;
import cc.catface.module_start.databinding.StartFmMessBinding;
import cc.catface.module_start.main.mess.ApiFm;
import cc.catface.module_start.main.mess.ApisFm;
import cc.catface.module_start.main.mess.PjsFm;
import cc.catface.module_start.main.mvp.view.navigation.adapter.ImageTextAdapter;
import cc.catface.module_start.main.mvp.view.navigation.presenter.MessPresenterImp;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@CreatePresenter(MessPresenterImp.class)
public class MessFm extends MvpFragment<MessView, MessPresenterImp, StartFmMessBinding> implements MessView {
    @Override public int layoutId() {
        return R.layout.start_fm_mess;
    }

    private String[] mTabTitles = {"api", "apis", "pjs"};
    //    private String[] mTabTitles = {"GIF", "视频"};
    private List<Fragment> mFms;

    @Override public void viewCreated() {
        LinearLayout ll = (LinearLayout) mBinding.tlMess.getChildAt(0);
        ll.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        ll.setDividerPadding(40);
        ll.setDividerDrawable(ContextCompat.getDrawable(mActivity, R.drawable.showapi_shape_top_bar_line));

        mFms = new ArrayList<>();
        mFms.add(new ApiFm());
        mFms.add(new ApisFm());
        mFms.add(new PjsFm());

        mBinding.vpMess.setOffscreenPageLimit(mTabTitles.length);
        mBinding.vpMess.setAdapter(new ImageTextAdapter(getChildFragmentManager(), mTabTitles, mFms));
        mBinding.tlMess.setupWithViewPager(mBinding.vpMess);

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
    //    @Override public void onCreate() {
    //        super.onCreate();
    //        if (null != mListener) mListener.process("onCreate...");
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
